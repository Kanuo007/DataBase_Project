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


@WebServlet("/householdupdate")
public class HouseHoldUpdate extends HttpServlet {
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
    Household household = null;
    // Retrieve user and validate.
    String householdId = req.getParameter("householdId");
    if ((householdId == null) || householdId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid householdId.");
    } else {
      try {
        household = this.householdDao.getHouseholdFromHouseholdId(Integer.parseInt(householdId));
        if (household == null) {
          messages.put("success", "householdId does not exist.");
        }
        req.setAttribute("household", household);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/HouseHoldUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String householdId = req.getParameter("householdId");
    String newMedianHouseValue = req.getParameter("MedianHouseValue");
    System.out.println(newMedianHouseValue);
    if ((householdId == null) || householdId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid householdId.");
    } else {
      try {
        Household household =
            this.householdDao.getHouseholdFromHouseholdId(Integer.parseInt(householdId));
        if (household == null) {
          messages.put("success", "householdId does not exist. No update to perform.");
        } else {

          if ((newMedianHouseValue == null) || newMedianHouseValue.trim().isEmpty()) {
            messages.put("success", "Please enter a valid newMedianHouseValue.");
          } else {
            household = this.householdDao.updateMedianHouseValue(household,
                Integer.parseInt(newMedianHouseValue));
            messages.put("success", "Successfully updated " + householdId);
          }
        }
        req.setAttribute("household", householdId);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/HouseHoldUpdate.jsp").forward(req, resp);
  }
}


