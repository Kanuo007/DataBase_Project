package neighbor.servlet;

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

import neighbor.dao.AreaDistributionDao;
import neighbor.model.AreaDistribution;


@WebServlet("/getAreaDistributionByPopulationId")
public class getAreaDistributionByPopulationId extends HttpServlet{
  
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
      
      AreaDistribution area = null;
      
      // Retrieve and validate name.
      String populationId = req.getParameter("PopulationId");
      if (populationId == null || populationId.trim().isEmpty()) {
          messages.put("success", "Please enter a valid populationId.");
      } else {
          
          // Retrieve BlogUsers, and store as a message.
          try {
            area = areaDistributionDao.getAreaDistributionByPopulationId(Long.parseLong(populationId));
          } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
          }
          messages.put("success", "Displaying results for " + populationId);
          messages.put("success", "Displaying using doGet: " + area.toString());
          messages.put("Urban", String.valueOf(area.getUrban()));
          messages.put("Urbancluster", String.valueOf(area.getUrbancluster()));
          messages.put("Rural", String.valueOf(area.getRural()));
 }
      req.setAttribute("AreaDistribution", area);
      
      req.getRequestDispatcher("/getAreaDistributionByPopulationId.jsp").forward(req, resp);
  }
  
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      List<AreaDistribution> areaList = new ArrayList<>();
      
      // Retrieve and validate areaDistribution.
      // populationId is retrieved from the form POST submission. By default, it
      // is populated by the URL query string (getAreaDistribution.jsp).
      String populationId = req.getParameter("PopulationId");
      if (populationId == null || populationId.trim().isEmpty()) {
          messages.put("success", "Please enter a valid name.");
      } else {
        try {
          AreaDistribution insert = areaDistributionDao.getAreaDistributionByPopulationId(Long.valueOf(populationId));
          if (insert != null) areaList.add(insert);
          

      } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
      }
      }
      req.setAttribute("AreaDistribution", areaList);
      
      req.getRequestDispatcher("/getAreaDistributionByPopulationId.jsp").forward(req, resp);
  }
}

  
  

