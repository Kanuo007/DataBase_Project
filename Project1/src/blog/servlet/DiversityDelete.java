package blog.servlet;
/*
 * wen chen
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dal.AreaDistributionDao;
import blog.dal.DiversityDao;
import blog.model.AreaDistribution;
import blog.model.Diversity;



@WebServlet("/DiversityDelete")
public class DiversityDelete extends HttpServlet {
  
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
  // Provide a title and render the JSP.
  messages.put("title", "Delete Diversity");        
  req.getRequestDispatcher("/DiversityDelete.jsp").forward(req, resp);
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
    Diversity diversity = new Diversity(Long.parseLong(populationId));
      try {
        diversity = diversityDao.delete(diversity);
          // Update the message.
          if (diversity == null) {
              messages.put("title", "Successfully deleted DiversityDelete for populationId:" + populationId);
              messages.put("disableSubmit", "true");
          } else {
              messages.put("title", "Failed to delete DiversityDelete for populationId:" + populationId);
              messages.put("disableSubmit", "false");
          }
      } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
      }
  }
  
  req.getRequestDispatcher("/DiversityDelete.jsp").forward(req, resp);
}
}
