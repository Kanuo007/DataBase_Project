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

@WebServlet("/occupiedDistributioncreate")
public class OccupiedDistributionCreate extends HttpServlet {

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
    // Just render the JSP.
    req.getRequestDispatcher("/OccupiedDistributionCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);


    int householdId = Integer.parseInt(req.getParameter("HouseHoldId"));
    int totalOccupiedUnit = Integer.parseInt(req.getParameter("TotalOccupiedUnit"));
    int totalVacantUnit = Integer.parseInt(req.getParameter("TotalVacantUnit"));
    int renterOccupiedUnit = Integer.parseInt(req.getParameter("RenterOccupiedUnit"));
    int ownerOccupiedUnit = Integer.parseInt(req.getParameter("OwnerOccupiedUnit"));
    try {
      HouseholdDao householdDao = HouseholdDao.getInstance();
      Household household = householdDao.getHouseholdFromHouseholdId(householdId);


      OccupiedDistribution occupiedDistribution = new OccupiedDistribution(totalOccupiedUnit,
          totalVacantUnit, renterOccupiedUnit, ownerOccupiedUnit, household);
      occupiedDistribution = this.occupiedDistributionDao.create(occupiedDistribution);
      messages.put("success", "Successfully created " + household.getHouseholdId());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("/OccupiedDistributionCreate.jsp").forward(req, resp);
  }


}
