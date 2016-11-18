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

import blog.dal.HouseholdDao;
import blog.model.Household;


@WebServlet("/findHouseHold")
public class FindHouseHold extends HttpServlet {

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

    List<Household> households = new ArrayList<>();

    String householdId = req.getParameter("householdId");
    String medianHouseValueFrom = req.getParameter("medianHouseValueFrom");
    String medianHouseValueTo = req.getParameter("medianHouseValueTo");

    if ((householdId == null) || householdId.trim().isEmpty()) {
      if (((medianHouseValueFrom != null) && !medianHouseValueFrom.trim().isEmpty())
          || ((medianHouseValueTo != null) && !medianHouseValueTo.trim().isEmpty())) {
        int medianHouseValueFromInt = 0;
        int medianHouseValueToInt = -1;
        if ((medianHouseValueFrom != null) && !medianHouseValueFrom.trim().isEmpty()) {
          medianHouseValueFromInt = Integer.parseInt(medianHouseValueFrom);
        }
        if ((medianHouseValueTo != null) && !medianHouseValueTo.trim().isEmpty()) {
          medianHouseValueToInt = Integer.parseInt(medianHouseValueTo);
        }
        try {
          households = this.householdDao.getHouseholdByMedianHouseValue(medianHouseValueFromInt,
              medianHouseValueToInt);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    } else {
      try {
        Household household =
            this.householdDao.getHouseholdFromHouseholdId(Integer.parseInt(householdId));
        households.add(household);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for household " + householdId);
      messages.put("prevhouseholdId", householdId);
      messages.put("medianHouseValueFrom", medianHouseValueFrom);
      messages.put("medianHouseValueTo", medianHouseValueTo);
    }

    req.setAttribute("households", households);
    req.getRequestDispatcher("/FindHouseHold.jsp").forward(req, resp);
  }


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<Household> households = new ArrayList<>();

    String householdId = req.getParameter("householdId");
    String medianHouseValueFrom = req.getParameter("medianHouseValueFrom");
    String medianHouseValueTo = req.getParameter("medianHouseValueTo");

    if ((householdId == null) || householdId.trim().isEmpty()) {
      if (((medianHouseValueFrom == null) || medianHouseValueFrom.trim().isEmpty())
          && ((medianHouseValueTo == null) || medianHouseValueTo.trim().isEmpty())) {
        messages.put("success", "Please enter a valid household Id or medianHouse values.");
      } else {
        int medianHouseValueFromInt = 0;
        int medianHouseValueToInt = -1;
        if ((medianHouseValueFrom != null) && !medianHouseValueFrom.trim().isEmpty()) {
          medianHouseValueFromInt = Integer.parseInt(medianHouseValueFrom);
        }
        if ((medianHouseValueTo != null) && !medianHouseValueTo.trim().isEmpty()) {
          medianHouseValueToInt = Integer.parseInt(medianHouseValueTo);
        }
        try {
          households = this.householdDao.getHouseholdByMedianHouseValue(medianHouseValueFromInt,
              medianHouseValueToInt);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    } else {
      try {
        Household household =
            this.householdDao.getHouseholdFromHouseholdId(Integer.parseInt(householdId));
        households.add(household);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for household " + householdId);
      messages.put("prevhouseholdId", householdId);
      messages.put("medianHouseValueFrom", medianHouseValueFrom);
      messages.put("medianHouseValueTo", medianHouseValueTo);
    }

    req.setAttribute("households", households);
    req.getRequestDispatcher("/FindHouseHold.jsp").forward(req, resp);
  }



}
