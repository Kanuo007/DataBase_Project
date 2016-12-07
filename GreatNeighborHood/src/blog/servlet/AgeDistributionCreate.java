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


@WebServlet("/ageDistributionCreate")
public class AgeDistributionCreate extends HttpServlet {

    protected AgeDistributionDao ageDistributionDao;

    @Override
    public void init() throws ServletException {
        ageDistributionDao = AgeDistributionDao.getInstance().getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/CreateAgeDistribution.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String PopulationId = req.getParameter("PopulationId");
//        if (PopulationId == null || PopulationId.trim().isEmpty()) {
//            messages.put("success", "Invalid PopulationId");
//        } else {
            // Create the AgeDistribution.
            int LessThan5 = Integer.valueOf(req.getParameter("LessThan5"));
            int from5To17 = Integer.valueOf(req.getParameter("from5To17"));
            int from18To24 = Integer.valueOf(req.getParameter("from18To24"));
            int from25To44 = Integer.valueOf(req.getParameter("from25To44"));
            int from45To64 = Integer.valueOf(req.getParameter("from45To64"));
            int MoreThan65 = Integer.valueOf(req.getParameter("MoreThan65"));
            try {
                AgeDistribution ageDistribution = new AgeDistribution(Long.valueOf(PopulationId),
                    LessThan5, from5To17, from18To24, from25To44, from45To64, MoreThan65);
                ageDistribution = ageDistributionDao.create(ageDistribution);
                messages.put("success", "Successfully created " + ageDistribution.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
//        }

        req.getRequestDispatcher("/CreateAgeDistribution.jsp").forward(req, resp);
    }
}
