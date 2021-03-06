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

@WebServlet("/DiversityCreate")
public class DiversityCreate extends HttpServlet {
  
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
  //Just render the JSP.   
  req.getRequestDispatcher("/DiversityCreate.jsp").forward(req, resp);
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
          messages.put("success", "Duplicate Diversity for same populationId");
      } else {
          // Create the AreaDistribtion.
          long populationId = Long.valueOf(req.getParameter("PopulationId"));
          int hispanic = Integer.valueOf(req.getParameter("hispanic"));
          int white = Integer.valueOf(req.getParameter("white"));
          int black = Integer.valueOf(req.getParameter("black"));
          int aian = Integer.valueOf(req.getParameter("aian"));
          int asian = Integer.valueOf(req.getParameter("asian"));
          int NHOPI = Integer.valueOf(req.getParameter("NHOPI"));
          int SOR = Integer.valueOf(req.getParameter("SOR"));

          try {
              Diversity diversity = new Diversity(populationId, hispanic, white, black, aian, asian, NHOPI, SOR);
              diversity = diversityDao.create(diversity);
              messages.put("success", "Successfully created.");
          } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
          }
      }
      
      req.getRequestDispatcher("/DiversityCreate.jsp").forward(req, resp);
  }
}
