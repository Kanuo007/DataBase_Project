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
import neighbor.dao.DiversityDao;
import neighbor.model.AreaDistribution;
import neighbor.model.Diversity;

@WebServlet("/getDiversityByPopulationId")
public class getDiversityByPopulationId extends HttpServlet{

  protected DiversityDao diversityDao;
  
  @Override
  public void init() throws ServletException {
    diversityDao = DiversityDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);
      
      Diversity diversity = null;
      
      // Retrieve and validate name.
      String populationId = req.getParameter("PopulationId");
      if (populationId == null || populationId.trim().isEmpty()) {
          messages.put("success", "Please enter a valid populationId.");
      } else {
          
          // Retrieve BlogUsers, and store as a message.
          try {
            diversity = diversityDao.getDiversityByPopulationId(Long.parseLong(populationId));
          } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
          }
          messages.put("success", "Displaying results for " + populationId);
          // Save the previous search term, so it can be used as the default
          // in the input box when rendering FindUsers.jsp.
          messages.put("previousPopulationId", populationId);
      }
      req.setAttribute("Diversity", diversity);
      
      req.getRequestDispatcher("/getDiversityByPopulationId.jsp").forward(req, resp);
  }
  
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      List<Diversity> diversityList = new ArrayList<>();
      
      // Retrieve and validate areaDistribution.
      // populationId is retrieved from the form POST submission. By default, it
      // is populated by the URL query string (getAreaDistribution.jsp).
      String populationId = req.getParameter("PopulationId");
      if (populationId == null || populationId.trim().isEmpty()) {
          messages.put("success", "Please enter a valid name.");
      } else {
          try {
            Diversity insert = diversityDao.getDiversityByPopulationId(Long.parseLong(populationId));
            if(insert != null) diversityList.add(insert);
            
          } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
          }
          messages.put("success", "Displaying results for " + populationId);
      }
      req.setAttribute("Diversity", diversityList);
      
      req.getRequestDispatcher("/getDiversityByPopulationId.jsp").forward(req, resp);
  }
}

  
  


