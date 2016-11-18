package neighbor.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neighbor.dao.AreaDistributionDao;
import neighbor.model.AreaDistribution;



@WebServlet("/AreaDistributionUpdateUrban")
public class AreaDistributionUpdateUrban extends HttpServlet {

	protected AreaDistributionDao areaDistributionDao;

	@Override
	public void init() throws ServletException {
		areaDistributionDao = AreaDistributionDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String populationId = req.getParameter("PopulationId");
        System.out.println("111");
        if (populationId == null || populationId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid populationId.");
        } else {
        	try {
        	  AreaDistribution areaDistribution = areaDistributionDao.getAreaDistributionByPopulationId(Long.valueOf(populationId));
        		if(areaDistribution == null) {
        			messages.put("success", "PopulationId does not exist.");
        		}
        		req.setAttribute("AreaDistribution", areaDistribution);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/AreaDistributionUpdateUrban.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String populationId = req.getParameter("PopulationId");
        if (populationId == null || populationId.trim().isEmpty()) {
           System.out.println("fail");
            messages.put("success", "Please enter a valid populationId.");
        } else {
          System.out.println("success");
          try {
            AreaDistribution area = areaDistributionDao.getAreaDistributionByPopulationId(Long.valueOf(populationId));
            if(area == null) {
                messages.put("success", "PopulaitonId does not exist. No update to perform.");
            } else {
                String newUrabn = req.getParameter("Urban");
                if (newUrabn == null || newUrabn.trim().isEmpty()) {
                    messages.put("success", "Please enter a valid Urabn Amount.");
                } else {
                    area = areaDistributionDao.updateUrbanAmount(area, Integer.valueOf(newUrabn));
                    messages.put("success", "Successfully updated ");
                }
            }
            req.setAttribute("AreaDistribution", area);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

        req.getRequestDispatcher("/AreaDistributionUpdateUrban.jsp").forward(req, resp);
    }
}
