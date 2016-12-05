package blog.servlet;

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

import blog.dal.HouseUnitDistributionDao;
import blog.model.HouseUnitDistribution;


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
 * 2. Insert test data. You can do this by running blog.blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/getAgeDistributionByPopulationId.
 *///getAgeDistributionByPopulationId
@WebServlet("/getHouseUnitDistributionByHouseHoldId")
public class GetHouseUnitDistributionByHouseHoldId extends HttpServlet {

	protected HouseUnitDistributionDao houseUnitDistributionDao;

	@Override
	public void init() throws ServletException {
        houseUnitDistributionDao = HouseUnitDistributionDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        HouseUnitDistribution houseUnitDistribution = null;

        // Retrieve and validate name.
        // HouseHoldId is retrieved from the URL query string.
        String HouseHoldId = req.getParameter("HouseHoldId");
        System.out.println("get the householdid = " + HouseHoldId);
        if (HouseHoldId == null || HouseHoldId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HouseHoldId.");
        } else {
        	// Retrieve HouseUnitDistribution, and store as a message.
        	try {
            	houseUnitDistribution = houseUnitDistributionDao.getHouseUnitDistributionByHouseHoldId(Integer.valueOf(HouseHoldId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
            System.out.println("success!");

            messages.put("success", "Displaying using doGet: " + houseUnitDistribution.toString());
            messages.put("SingleUnit", String.valueOf(houseUnitDistribution.getSingleUnit()));
            messages.put("TwoToNineUnit", String.valueOf(houseUnitDistribution.getTwoToNineUnit()));
            messages.put("TenMoreUnit", String.valueOf(houseUnitDistribution.getTenMoreUnit()));
            messages.put("MobileHome", String.valueOf(houseUnitDistribution.getMobileHome()));

           	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering GetAgeDistributionByPopulationId.jsp.
        	messages.put("previousHouseHoldId", HouseHoldId);

        }
        req.setAttribute("houseUnitDistribution", houseUnitDistribution);
        req.getRequestDispatcher("/GetHouseUnitDistributionByHouseHoldId.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<HouseUnitDistribution> houseUnitDistribution = new ArrayList<>();

        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in GetAgeDistributionByPopulationId.jsp).
        String HouseHoldId = req.getParameter("HouseHoldId");
        System.out.println("inside the doPost, we get the householdId = " + HouseHoldId);
        if (HouseHoldId == null || HouseHoldId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HouseHoldId.");
        } else {
        	try {
        	    HouseUnitDistribution insert = houseUnitDistributionDao.getHouseUnitDistributionByHouseHoldId(Integer.valueOf(HouseHoldId));
                if (insert != null) houseUnitDistribution.add(insert);

            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("houseUnitDistribution", houseUnitDistribution);

        req.getRequestDispatcher("/GetHouseUnitDistributionByHouseHoldId.jsp").forward(req, resp);
    }
}
