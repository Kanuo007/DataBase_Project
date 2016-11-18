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


@WebServlet("/AreaDistributionDelete")
public class AreaDistributionDelete extends HttpServlet {
  
  protected AreaDistributionDao  areaDistributionDao;
  
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
  // Provide a title and render the JSP.
  messages.put("title", "Delete AreaDistribution");        
  req.getRequestDispatcher("/AreaDistributionDelete.jsp").forward(req, resp);
}
  
  

  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
  // Map for storing messages.
  Map<String, String> messages = new HashMap<String, String>();
  req.setAttribute("messages", messages);

  // Retrieve and validate name.
  String populationId = req.getParameter("PopulationId");
  if (populationId == null || populationId.trim().isEmpty()) {
      messages.put("title", "Invalid input");
      messages.put("disableSubmit", "true");
  } else {
      // Delete 
    AreaDistribution area = new AreaDistribution(Long.parseLong(populationId));
      try {
        area = areaDistributionDao.delete(area);
          // Update the message.
          if (area == null) {
              messages.put("title", "Successfully deleted areaDistribution for populationId:" + populationId);
              messages.put("disableSubmit", "true");
          } else {
              messages.put("title", "Failed to delete areaDistribution for populationId:" + populationId);
              messages.put("disableSubmit", "false");
          }
      } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
      }
  }
  
  req.getRequestDispatcher("/AreaDistributionDelete.jsp").forward(req, resp);
}
}

