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

import blog.dal.AddressDao;
import blog.dal.HouseholdDao;
import blog.model.Address;
import blog.model.Household;

@WebServlet("/houseHoldCreate")
public class HouseHoldCreate extends HttpServlet {

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
    // Just render the JSP.
    req.getRequestDispatcher("/HouseHoldCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    try {
      long addressId = Long.parseLong(req.getParameter("addressId"));
      long totalIncomeOfHouseholds = Long.parseLong(req.getParameter("TotalIncomeOfHouseholds"));
      int AVGIncomeOfHouseholds = Integer.parseInt(req.getParameter("AVGIncomeOfHouseholds"));
      int NumOfFamilyWithChildUnderSix =
          Integer.parseInt(req.getParameter("NumOfFamilyWithChildUnderSix"));
      int NumOfFamilyWithOneOrMoreUnder18 =
          Integer.parseInt(req.getParameter("NumOfFamilyWithOneOrMoreUnder18"));
      int totalPersonsInHouseholds = Integer.parseInt(req.getParameter("TotalPersonsInHouseholds"));
      int NumOfHouseholdsWithoutTeleService =
          Integer.parseInt(req.getParameter("NumOfHouseholdsWithoutTeleService"));
      int NumOfHouseholdsLackFacilities =
          Integer.parseInt(req.getParameter("NumOfHouseholdsLackFacilities"));
      int NumOfHouseholdsBuiltAfter2010 =
          Integer.parseInt(req.getParameter("NumOfHouseholdsBuiltAfter2010"));
      int medianHouseValue = Integer.parseInt(req.getParameter("MedianHouseValue"));
      long totalHouseValue = Long.parseLong(req.getParameter("totalHouseValue"));
      int NumOfAssistanceFamilies = Integer.parseInt(req.getParameter("NumOfAssistanceFamilies"));
      int totalNumOfHouseholds = Integer.parseInt(req.getParameter("TotalNumOfHouseholds"));
      int NumOfHouseholdsMovedInAfter2010 =
          Integer.parseInt(req.getParameter("NumOfHouseholdsMovedInAfter2010"));

      // Exercise: parse the input for StatusLevel.
      AddressDao addressDao = AddressDao.getInstance();
      Address address = addressDao.getAddressFromAddressId(addressId);
      Household household = new Household(address, totalIncomeOfHouseholds, AVGIncomeOfHouseholds,
          NumOfFamilyWithChildUnderSix, NumOfFamilyWithOneOrMoreUnder18, totalPersonsInHouseholds,
          NumOfHouseholdsWithoutTeleService, NumOfHouseholdsLackFacilities,
          NumOfHouseholdsBuiltAfter2010, medianHouseValue, totalHouseValue, NumOfAssistanceFamilies,
          totalNumOfHouseholds, NumOfHouseholdsMovedInAfter2010);

      household = this.householdDao.create(household);
      messages.put("success", "Successfully created " + household.getHouseholdId());
    } catch (NumberFormatException e) {
      messages.put("success", "Please enter valid household values.");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }


    req.getRequestDispatcher("/HouseHoldCreate.jsp").forward(req, resp);
  }

}
