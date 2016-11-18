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

import blog.dal.HouseholdDao;
import blog.dal.OccupiedDistributionDao;
import blog.model.Household;
import blog.model.OccupiedDistribution;

@WebServlet("/occupiedDistributiondelete")
public class OccupiedDistributionDelete extends HttpServlet {

  protected OccupiedDistributionDao occupiedDistributionDao;

  @Override
  public void init() throws ServletException {
    this.occupiedDistributionDao = OccupiedDistributionDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete OccupiedDistribution");
    req.getRequestDispatcher("/OccupiedDistributionDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String householdId = req.getParameter("householdId");
    if ((householdId == null) || householdId.trim().isEmpty()) {
      messages.put("title", "Invalid householdId");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the BlogUser.
      int householdid = Integer.parseInt(householdId);
      HouseholdDao householddao = HouseholdDao.getInstance();
      Household household = null;
      try {
        household = householddao.getHouseholdFromHouseholdId(householdid);
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      OccupiedDistribution occupiedDistribution = new OccupiedDistribution(household);
      try {
        occupiedDistribution = this.occupiedDistributionDao.delete(occupiedDistribution);
        // Update the message.
        if (occupiedDistribution == null) {
          messages.put("title", "Successfully deleted " + householdId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + householdId);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/OccupiedDistributionDelete.jsp").forward(req, resp);
  }
}
