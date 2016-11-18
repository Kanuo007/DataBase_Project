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


@WebServlet("/ageDistributionUpdate")
public class AgeDistributionUpdate extends HttpServlet {

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

        // Retrieve user and validate.
        String populationId = req.getParameter("populationId");
        if (populationId == null || populationId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid populationId.");
        } else {
        	try {
        		AgeDistribution ageDistribution = ageDistributionDao.getAgeDistributionByPopulationId(Long.valueOf(populationId));
        		if(ageDistribution == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("ageDistribution", ageDistribution);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/AgeDistributionUpdate.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String populationId = req.getParameter("populationId");
        if (populationId == null || populationId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid populationId.");
        } else {
        	try {
        		AgeDistribution ageDistribution = ageDistributionDao.getAgeDistributionByPopulationId(Long.valueOf(populationId));
        		AgeDistribution dummy = ageDistribution;
				ageDistributionDao.delete(ageDistribution.getPopulationId());
				dummy.setLessThan5(Integer.valueOf(req.getParameter("newLessThan5")));
				ageDistributionDao.create(dummy);
				messages.put("success", "Successfully updated " + req.getParameter("newLessThan5"));

//				if(ageDistribution == null) {
//        			messages.put("success", "UserName does not exist. No update to perform.");
//        		} else {
//        			String newLessThan5 = req.getParameter("newLessThan5");
//        			if (newLessThan5 == null || newLessThan5.trim().isEmpty()) {
//        	            messages.put("success", "Please enter a valid newLessThan5 number.");
//        	        } else {
////						ageDistribution = ageDistributionDao.updateLessThan5(ageDistribution, Integer.valueOf(newLessThan5));
//        	        	messages.put("success", "Successfully updated " + newLessThan5);
//        	        }
//        		}
        		req.setAttribute("ageDistribution", dummy);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/AgeDistributionUpdate.jsp").forward(req, resp);
    }
}
