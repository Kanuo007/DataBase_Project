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
import blog.dal.HouseUnitDistributionDao;
import blog.dal.HouseholdDao;
import blog.dal.OccupiedDistributionDao;
import blog.dal.PopulationDao;
import blog.model.HouseUnitDistribution;
import blog.model.Household;
import blog.model.OccupiedDistribution;
import blog.model.Population;


@WebServlet(urlPatterns = {"/Address"})
public class FinalScoreGenerator extends HttpServlet {
  protected AddressDao addressDao;
  protected PopulationDao populationDao;
  protected HouseholdDao householdDao;
  protected OccupiedDistributionDao occupiedDistributionDao;
  protected HouseUnitDistributionDao houseUnitDistributionDao;


  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
    this.populationDao = PopulationDao.getInstance();
    this.householdDao = HouseholdDao.getInstance();
    // hospitalDao = HospitalDao.getInstance();
    this.occupiedDistributionDao = OccupiedDistributionDao.getInstance();
    this.houseUnitDistributionDao = HouseUnitDistributionDao.getInstance();
  }

  // using addressid parsed by url, get the populationID, householdId
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    String addressId = req.getParameter("AddressId");
    req.setAttribute("AddressId", addressId);
    Population population = null;
    Household household = null;
    HouseUnitDistribution houseunit = null;
    OccupiedDistribution occHosue = null;
    double populationScore = 0;
    double hospitalScore = 0;
    double householdScore = 0;
    DisplayHouseHold displayHousehold = new DisplayHouseHold();

    try {
      if (!invalidAddressId(addressId)
          && (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) != null)) {
        // hospitalScore = hospitalDao.score(AddressId);
        try {
          population = this.populationDao.getPopulationFromAddressId(Long.valueOf(addressId));
          if (population == null) {
            messages.put("success", "  Population doesn't exist with addressId" + addressId + "  ");
          } else {
            populationScore = 0;
            // population.
            // (population.getPopulationId());
          }
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }

        try {
          household = this.householdDao.getHouseholdFromAddressId(Long.valueOf(addressId));
          houseunit = this.houseUnitDistributionDao
              .getHouseUnitDistributionByHouseHoldId(household.getHouseholdId());
          occHosue = this.occupiedDistributionDao
              .getOccupiedDistributionFromHouseHoldId(household.getHouseholdId());
          // System.out.println(household.getHouseholdId());
          // System.out.println(houseunit.getMobileHome());
          // System.out.println(occHosue.getRenterOccupiedUnit());

          if ((household == null) || (houseunit == null) || (occHosue == null)) {
            messages.put("success", "Household doesn't exist with addressId" + addressId);
          } else {
            double[] res = displayHousehold.calculateScores(household.getAVGIncomeOfHouseholds(),
                household.getNumOfHouseholdsLackFacilities(), household.getMedianHouseValue(),
                household.getTotalPersonsInHouseholds(), household.getTotalNumOfHouseholds(),
                household.getNumOfHouseholdsBuiltAfter2010(), houseunit.getSingleUnit(),
                houseunit.getTenMoreUnit(), houseunit.getTenMoreUnit(), houseunit.getMobileHome(),
                occHosue.getTotalOccupiedUnit(), occHosue.getTotalVacantUnit(),
                occHosue.getRenterOccupiedUnit(), occHosue.getOwnerOccupiedUnit());
            householdScore = Double.parseDouble(String.format("%.2f", res[0]));
            // householdScore = householdDao.score(household.getHouseholdId());
          }
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
      } else {
        messages.put("success",
            "  Address is not in NY, CA, TX or WA. Please enter a valid address.  ");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    double TotalScore = (0.4 * populationScore) + (0.4 * householdScore);
    // + 0.2*hospitalScore;

    req.setAttribute("populationScore", populationScore);
    req.setAttribute("householdScore", householdScore);
    // req.setAttribute("hospitalScore", hospitalScore);
    req.setAttribute("TotalScore", TotalScore);
    if (population == null) {
      req.setAttribute("populationId", -1);
    } else {
      req.setAttribute("populationId", population.getPopulationId());
    }

    if (population == null) {
      req.setAttribute("householdId", -1);
    } else {
      req.setAttribute("householdId", household.getHouseholdId());
    }
    req.getRequestDispatcher("/TotalScore.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }
}


