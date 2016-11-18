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

import blog.dal.OccupiedDistributionDao;
import blog.model.OccupiedDistribution;


@WebServlet("/occupiedDistributionUpdate")
public class OccupiedDistributionUpdate extends HttpServlet {

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

    // Retrieve user and validate.
    String householdId = req.getParameter("householdId");
    System.out.println(householdId);
    if ((householdId == null) || householdId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid householdId.");
    } else {
      try {
        OccupiedDistribution occupiedDistribution = this.occupiedDistributionDao
            .getOccupiedDistributionFromHouseHoldId(Integer.parseInt(householdId));
        if (occupiedDistribution == null) {
          messages.put("success", "occupiedDistribution does not exist.");
        }
        req.setAttribute("occupiedDistribution ", occupiedDistribution);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/OccupiedDistributionUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    String householdId = req.getParameter("householdId");
    if ((householdId == null) || householdId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid householdId.");
    } else {
      try {
        OccupiedDistribution occupiedDistribution = this.occupiedDistributionDao
            .getOccupiedDistributionFromHouseHoldId(Integer.parseInt(householdId));
        if (occupiedDistribution == null) {
          messages.put("success", "UserName does not exist. No update to perform.");
        } else {
          String newrenterOccupiedUnit = req.getParameter("RenterOccupiedUnit");
          if ((newrenterOccupiedUnit == null) || newrenterOccupiedUnit.trim().isEmpty()) {
            messages.put("success", "Please enter a valid renterOccupiedUnit.");
          } else {
            int newrenterOccupiedUnitVal = Integer.parseInt(newrenterOccupiedUnit);
            occupiedDistribution = this.occupiedDistributionDao
                .updateTotalOccupiedUnit(occupiedDistribution, newrenterOccupiedUnitVal);
            messages.put("success", "Successfully updated " + householdId);
          }
        }
        req.setAttribute("occupiedDistribution", occupiedDistribution);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/OccupiedDistributionUpdate.jsp").forward(req, resp);
  }
}
