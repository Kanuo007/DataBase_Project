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



@WebServlet("/AreaDistributionCreate")
public class AreaDistributionCreate extends HttpServlet {
  
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
  //Just render the JSP.   
  req.getRequestDispatcher("/AreaDistributionCreate.jsp").forward(req, resp);
}
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      // Retrieve and validate name.
      String PopulationId = req.getParameter("PopulationId");
      if (PopulationId == null || PopulationId.trim().isEmpty()) {
          messages.put("success", "Duplicate AreaDistribtion for same populationId");
      } else {
          // Create the AreaDistribtion.
          long populationId = Long.valueOf(req.getParameter("PopulationId"));
          int urban = Integer.valueOf(req.getParameter("Urban"));
          int urbancluster = Integer.valueOf(req.getParameter("Urbancluster"));
          int rural = Integer.valueOf(req.getParameter("Rural"));
          
          try {
              AreaDistribution areaDistribution = new AreaDistribution(populationId, urban, urbancluster, rural);
              areaDistribution = areaDistributionDao.create(areaDistribution);
              messages.put("success", "Successfully created.");
          } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
          }
      }
      
      req.getRequestDispatcher("/AreaDistributionCreate.jsp").forward(req, resp);
  }
}


 

