package blog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dal.AgeDistributionDao;
import blog.model.AgeDistribution;


@WebServlet("/ageDistributionDelete")
public class AgeDistributionDelete extends HttpServlet {

    protected AgeDistributionDao ageDistributionDao;

    @Override
    public void init() throws ServletException {
        ageDistributionDao = AgeDistributionDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete BlogUser");
        req.getRequestDispatcher("/AgeDistributionDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String populationId = req.getParameter("populationId");
        if (populationId == null || populationId.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            // we can short here later, there is a lot of extra work here. But it worked.
            AgeDistribution ageDistribution = null;
            try {
                ageDistribution = ageDistributionDao.getAgeDistributionByPopulationId(
                    Long.valueOf(populationId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ageDistribution = ageDistributionDao.delete(Long.valueOf(populationId));
                // Update the message.
                if (ageDistribution == null) {
                    messages.put("title", "Successfully deleted " + populationId);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + populationId);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/AgeDistributionDelete.jsp").forward(req, resp);
    }
}
