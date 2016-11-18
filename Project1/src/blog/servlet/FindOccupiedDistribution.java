package blog.servlet;

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

import blog.dal.OccupiedDistributionDao;
import blog.model.OccupiedDistribution;

@WebServlet("/findOccupiedDistribution")
public class FindOccupiedDistribution extends HttpServlet {
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

    List<OccupiedDistribution> occupiedDistributions = new ArrayList<>();

    String householdId = req.getParameter("householdId");
    String totalOccupiedUnitFrom = req.getParameter("totalOccupiedUnitFrom");
    String totalOccupiedUnitTo = req.getParameter("totalOccupiedUnitTo");

    if ((householdId == null) || householdId.trim().isEmpty()) {
      if (((totalOccupiedUnitFrom == null) || totalOccupiedUnitFrom.trim().isEmpty())
          && ((totalOccupiedUnitTo == null) || totalOccupiedUnitTo.trim().isEmpty())) {
        messages.put("success", "Please enter a valid household Id or totalOccupiedUnit values.");
      } else {
        int totalOccupiedUnitFromInt = 0;
        int totalOccupiedUnitToInt = -1;
        if ((totalOccupiedUnitFrom != null) && !totalOccupiedUnitFrom.trim().isEmpty()) {
          totalOccupiedUnitFromInt = Integer.parseInt(totalOccupiedUnitFrom);
        }
        if ((totalOccupiedUnitTo != null) && !totalOccupiedUnitTo.trim().isEmpty()) {
          totalOccupiedUnitToInt = Integer.parseInt(totalOccupiedUnitTo);
        }
        try {
          occupiedDistributions =
              this.occupiedDistributionDao.getOccupiedDistributionByTotalOccupiedUnit(
                  totalOccupiedUnitFromInt, totalOccupiedUnitToInt);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    } else {
      try {
        OccupiedDistribution occupiedDistribution = this.occupiedDistributionDao
            .getOccupiedDistributionFromHouseHoldId(Integer.parseInt(householdId));
        occupiedDistributions.add(occupiedDistribution);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for household " + householdId);
      messages.put("prevhouseholdId", householdId);
      messages.put("medianHouseValueFrom", totalOccupiedUnitFrom);
      messages.put("medianHouseValueTo", totalOccupiedUnitTo);
    }


    req.setAttribute("occupiedDistributions", occupiedDistributions);
    req.getRequestDispatcher("/FindOccupiedDistribution.jsp").forward(req, resp);
  }



  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<OccupiedDistribution> occupiedDistributions = new ArrayList<>();

    String householdId = req.getParameter("householdId");
    String totalOccupiedUnitFrom = req.getParameter("totalOccupiedUnitFrom");
    String totalOccupiedUnitTo = req.getParameter("totalOccupiedUnitTo");

    if ((householdId == null) || householdId.trim().isEmpty()) {
      if (((totalOccupiedUnitFrom == null) || totalOccupiedUnitFrom.trim().isEmpty())
          && ((totalOccupiedUnitTo == null) || totalOccupiedUnitTo.trim().isEmpty())) {
        messages.put("success", "Please enter a valid household Id or totalOccupiedUnit values.");
      } else {
        int totalOccupiedUnitFromInt = 0;
        int totalOccupiedUnitToInt = -1;
        if ((totalOccupiedUnitFrom != null) && !totalOccupiedUnitFrom.trim().isEmpty()) {
          totalOccupiedUnitFromInt = Integer.parseInt(totalOccupiedUnitFrom);
        }
        if ((totalOccupiedUnitTo != null) && !totalOccupiedUnitTo.trim().isEmpty()) {
          totalOccupiedUnitToInt = Integer.parseInt(totalOccupiedUnitTo);
        }
        try {
          occupiedDistributions =
              this.occupiedDistributionDao.getOccupiedDistributionByTotalOccupiedUnit(
                  totalOccupiedUnitFromInt, totalOccupiedUnitToInt);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    } else {
      try {
        OccupiedDistribution occupiedDistribution = this.occupiedDistributionDao
            .getOccupiedDistributionFromHouseHoldId(Integer.parseInt(householdId));
        occupiedDistributions.add(occupiedDistribution);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for household " + householdId);
      messages.put("prevhouseholdId", householdId);
      messages.put("totalOccupiedUnitFrom", totalOccupiedUnitFrom);
      messages.put("totalOccupiedUnitTo", totalOccupiedUnitTo);
    }


    req.setAttribute("occupiedDistributions", occupiedDistributions);
    req.getRequestDispatcher("/FindOccupiedDistribution.jsp").forward(req, resp);
  }

}
