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
import blog.model.Household;


@WebServlet("/houseHoldDelete")
public class HouseHoldDelete extends HttpServlet {
  protected HouseholdDao householdDao;

  @Override
  public void init() throws ServletException {
    this.householdDao = HouseholdDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Household");
    req.getRequestDispatcher("/HouseHoldDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    int householdId = Integer.parseInt(req.getParameter("householdId"));
    if (householdId <= 0) {
      messages.put("title", "Invalid household Id");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the BlogUser.
      Household household = new Household(householdId);
      try {
        household = this.householdDao.delete(household);
        // Update the message.
        if (household == null) {
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

    req.getRequestDispatcher("/HouseHoldDelete.jsp").forward(req, resp);
  }
}


