package restaurant.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dal.AgeDistributionDao;
import restaurant.model.AgeDistribution;


/**
 * GetAgeDistributionByPopulationId is the primary entry point into the application.
 *
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 *
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running restaurant.restaurant.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/getAgeDistributionByPopulationId.
 *///getAgeDistributionByPopulationId
@WebServlet("/getAgeDistributionByPopulationId")
public class GetAgeDistributionByPopulationId extends HttpServlet {

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

        AgeDistribution ageDistribution = null;

        // Retrieve and validate name.
        // PopulationId is retrieved from the URL query string.
        String populationId = req.getParameter("populationId");
        if (populationId == null || populationId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid populationId.");
        } else {
        	// Retrieve AgeDistribution, and store as a message.
        	try {
            	ageDistribution = ageDistributionDao.getAgeDistributionByPopulationId(Long.valueOf(populationId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
            System.out.println("success!");
//                                <%--protected int LessThan5;--%>
//                    <%--protected int from5To17;--%>
//                    <%--protected int from18To24;--%>
//                    <%--protected int from25To44;--%>
//                    <%--protected int from45To64;--%>
//                    <%--protected int MoreThan65;--%>
            messages.put("success", "Displaying using doGet: " + ageDistribution.toString());
            messages.put("LessThan5", "yudong");
            messages.put("from5To17", String.valueOf(ageDistribution.getFrom5To17()));
            messages.put("from18To24", String.valueOf(ageDistribution.getFrom18To24()));
            messages.put("from25To44", String.valueOf(ageDistribution.getFrom25To44()));
            messages.put("from45To64", String.valueOf(ageDistribution.getFrom45To64()));
            messages.put("MoreThan65", String.valueOf(ageDistribution.getMoreThan65()));

           	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering GetAgeDistributionByPopulationId.jsp.
        	messages.put("previousPopulationId", populationId);

        }
        req.setAttribute("ageDistribution", ageDistribution);
        req.getRequestDispatcher("/GetAgeDistributionByPopulationId.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<AgeDistribution> ageDistribution = new ArrayList<>();

        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in GetAgeDistributionByPopulationId.jsp).
        String populationId = req.getParameter("populationId");
        if (populationId == null || populationId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid populationId.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        	    AgeDistribution insert = ageDistributionDao.getAgeDistributionByPopulationId(Long.valueOf(populationId));
                if (insert != null) ageDistribution.add(insert);

            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
//        	messages.put("success", "Displaying for Posts" + ageDistribution.toString());
//        	messages.put("test", "test String for Yudong123123123");
//            messages.put("populationId", String.valueOf(populationId));
//            messages.put("LessThan5", String.valueOf(ageDistribution.get(0).getLessThan5()));
//            messages.put("from5To17", String.valueOf(ageDistribution.get(0).getFrom5To17()));
//            messages.put("from18To24", String.valueOf(ageDistribution.get(0).getFrom18To24()));
//            messages.put("from25To44", String.valueOf(ageDistribution.get(0).getFrom25To44()));
//            messages.put("from45To64", String.valueOf(ageDistribution.get(0).getFrom45To64()));
//            messages.put("MoreThan65", String.valueOf(ageDistribution.get(0).getMoreThan65()));
        }
        req.setAttribute("ageDistribution", ageDistribution);

        req.getRequestDispatcher("/GetAgeDistributionByPopulationId.jsp").forward(req, resp);
    }
}
