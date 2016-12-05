package blog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dal.HouseUnitDistributionDao;
import blog.dal.HouseholdDao;
import blog.dal.OccupiedDistributionDao;
import blog.model.HouseUnitDistribution;
import blog.model.Household;
import blog.model.OccupiedDistribution;

@WebServlet("/displayHouseHold")
public class DisplayHouseHold extends HttpServlet {
  protected HouseholdDao householdDao;
  protected OccupiedDistributionDao occupiedDistributionDao;
  protected HouseUnitDistributionDao houseUnitDistributionDao;

  @Override
  public void init() throws ServletException {
    this.householdDao = HouseholdDao.getInstance();
    this.occupiedDistributionDao = OccupiedDistributionDao.getInstance();
    this.houseUnitDistributionDao = HouseUnitDistributionDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    Household household = null;
    OccupiedDistribution occupiedDistribution = null;
    HouseUnitDistribution householdDistribution = null;

    String householdId = req.getParameter("householdId");

    if ((householdId != null) && !householdId.trim().isEmpty()) {

      try {
        household = this.householdDao.getHouseholdFromHouseholdId(Integer.parseInt(householdId));
        occupiedDistribution = this.occupiedDistributionDao
            .getOccupiedDistributionFromHouseHoldId(Integer.parseInt(householdId));
        householdDistribution = this.houseUnitDistributionDao
            .getHouseUnitDistributionByHouseHoldId(Integer.parseInt(householdId));

      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for household " + householdId);
      messages.put("prevhouseholdId", householdId);
      double[] score = new double[4];
      score = calculateScores(household.getAVGIncomeOfHouseholds(),
          household.getNumOfHouseholdsLackFacilities(), household.getMedianHouseValue(),
          household.getTotalPersonsInHouseholds(), household.getTotalNumOfHouseholds(),
          household.getNumOfHouseholdsBuiltAfter2010(), householdDistribution.getSingleUnit(),
          householdDistribution.getTwoToNineUnit(), householdDistribution.getTenMoreUnit(),
          householdDistribution.getMobileHome(), occupiedDistribution.getTotalOccupiedUnit(),
          occupiedDistribution.getTotalVacantUnit(), occupiedDistribution.getRenterOccupiedUnit(),
          occupiedDistribution.getOwnerOccupiedUnit());
      req.setAttribute("household", household);
      req.setAttribute("occupiedDistribution", occupiedDistribution);
      req.setAttribute("householdDistribution", householdDistribution);
      DecimalFormat formatter = new DecimalFormat("#0.00");
      messages.put("totalScore", formatter.format(score[0]));
      messages.put("houseHoldScore", formatter.format(score[1]));
      messages.put("distributionScore", formatter.format(score[2]));
      messages.put("occupiedScore", formatter.format(score[3]));

    } else {
      messages.put("success", "Please enter a valid householdId.");
    }
    req.getRequestDispatcher("/DisplayHouseHold.jsp").forward(req, resp);
  }


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    Household household = null;
    OccupiedDistribution occupiedDistribution = null;
    HouseUnitDistribution householdDistribution = null;
    String householdId = req.getParameter("householdId");

    if ((householdId != null) && !householdId.trim().isEmpty()) {

      try {
        household = this.householdDao.getHouseholdFromHouseholdId(Integer.parseInt(householdId));
        occupiedDistribution = this.occupiedDistributionDao
            .getOccupiedDistributionFromHouseHoldId(Integer.parseInt(householdId));
        householdDistribution = this.houseUnitDistributionDao
            .getHouseUnitDistributionByHouseHoldId(Integer.parseInt(householdId));

      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for household " + householdId);
      messages.put("prevhouseholdId", householdId);

      double[] score = new double[4];
      score = calculateScores(household.getAVGIncomeOfHouseholds(),
          household.getNumOfHouseholdsLackFacilities(), household.getMedianHouseValue(),
          household.getTotalPersonsInHouseholds(), household.getTotalNumOfHouseholds(),
          household.getNumOfHouseholdsBuiltAfter2010(), householdDistribution.getSingleUnit(),
          householdDistribution.getTwoToNineUnit(), householdDistribution.getTenMoreUnit(),
          householdDistribution.getMobileHome(), occupiedDistribution.getTotalOccupiedUnit(),
          occupiedDistribution.getTotalVacantUnit(), occupiedDistribution.getRenterOccupiedUnit(),
          occupiedDistribution.getOwnerOccupiedUnit());

      req.setAttribute("household", household);
      req.setAttribute("occupiedDistribution", occupiedDistribution);
      req.setAttribute("household", household);
      req.setAttribute("occupiedDistribution", occupiedDistribution);
      req.setAttribute("householdDistribution", householdDistribution);
      DecimalFormat formatter = new DecimalFormat("#0.00");
      messages.put("totalScore", formatter.format(score[0]));
      messages.put("houseHoldScore", formatter.format(score[1]));
      messages.put("distributionScore", formatter.format(score[2]));
      messages.put("occupiedScore", formatter.format(score[3]));
    } else {
      messages.put("success", "Please enter a valid householdId.");
    }

    req.getRequestDispatcher("/DisplayHouseHold.jsp").forward(req, resp);
  }


  /**
   * Calculate the HouseHold's score based on different factors. The total score is 100 based. Each
   * factor contributes part of this total score.
   *
   * @param avgIncomeOfHouseholds
   * @param numOfHouseholdsLackFacilities
   * @param medianHouseValue
   * @param totalPersonsInHouseholds
   * @param totalNumOfHouseholds
   * @param builtAfter2010
   * @param singleUnit
   * @param twoToNineUnits
   * @param moreThanTenUnits
   * @param mobileHome
   * @param totalOccupiedUnit
   * @param totalVacantUnit
   * @param renterOccupiedUnit
   * @param ownerOccupiedUnit
   * @return the score in 100 based
   */
  private double[] calculateScores(int avgIncomeOfHouseholds, int numOfHouseholdsLackFacilities,
      int medianHouseValue, int totalPersonsInHouseholds, int totalNumOfHouseholds,
      int builtAfter2010, int singleUnit, int twoToNineUnits, int moreThanTenUnits, int mobileHome,
      int totalOccupiedUnit, int totalVacantUnit, int renterOccupiedUnit, int ownerOccupiedUnit) {
    double x, factor;

    // house value vs household income
    // factor: 30/100
    // the best score is when medium house value/average household income == 3
    double incomeHouseValueScore = 0.0;
    if (avgIncomeOfHouseholds > 0) {
      x = (medianHouseValue * 1.0) / avgIncomeOfHouseholds;

      factor = 0;
      if (x > 0.0) {
        factor = Math.log10(x * 3.33333);
        if (x > 3) {
          if (factor > 2) {
            factor = 0;
          } else {
            factor = 2 - factor;
          }
        }
      }

      incomeHouseValueScore = 30.0 * factor;
    }

    // people density in household
    // factor: 10/100
    // the best score is when average persons in a HouseHold is 3
    double densityScore = 0.0;
    if (totalNumOfHouseholds > 0) {
      x = ((totalPersonsInHouseholds * 1.0) / totalNumOfHouseholds) * 100;
      factor = 1 - (Math.abs(3.0 - x) / 3.0);
      if (factor < 0) {
        factor = 0;
      }
      densityScore = 10.0 * factor;
    }

    // new house vs total house
    // factor: 20/100
    // the best score is when total new houses/total houses >= 25%
    double newHouseScore = 0.0;
    if (totalNumOfHouseholds > 0) {
      x = (builtAfter2010 * 1.0) / totalNumOfHouseholds;
      factor = x / 0.25;
      if (factor > 1) {
        factor = 1;
      }
      newHouseScore = 20.0 * factor;
    }

    // HouseHold lacking facility percentage
    // factor: 10/100
    // the best score is when no HouseHold lacks facility
    double facilityScore = 0.0;
    if (totalNumOfHouseholds > 0) {
      x = (numOfHouseholdsLackFacilities * 1.0) / totalNumOfHouseholds;
      facilityScore = 10.0 * (1 - x);
    }

    // TwoToNine HouseHold percentage
    // factor: 10/100
    // the best score is when TwoToNine HouseHold/total occupied HouseHold >= 80%
    double twoToNineScore = 0.0;
    int totalDistUnit = singleUnit + twoToNineUnits + moreThanTenUnits + mobileHome;
    if (totalDistUnit > 0) {
      x = (twoToNineUnits * 1.0) / totalDistUnit;
      if (x > 0.8) {
        factor = 1;
      } else {
        factor = x / 0.8;
      }
      twoToNineScore = 10.0 * factor;
    }

    int totalUnits = totalOccupiedUnit + totalVacantUnit;

    // renter occupied rate
    // factor: 10/100
    // the best score is the higher renter rate, the worse the score
    double renterScore = 0.0;
    if (totalUnits > 0) {
      renterScore = 10.0 * (1.0 - ((renterOccupiedUnit * 1.0) / totalUnits));
    }

    // owner occupied rate
    // factor: 10/100
    // the best score is the higher owner rate, the better the score
    double ownerScore = 0.0;
    if (totalUnits > 0) {
      ownerScore = (10.0 * renterOccupiedUnit) / totalUnits;
    }

    double totalScore = incomeHouseValueScore + densityScore + newHouseScore + facilityScore
        + twoToNineScore + renterScore + ownerScore;

    double houseHoldScore =
        ((incomeHouseValueScore + densityScore + newHouseScore + facilityScore) / 70.0) * 100;
    double distributionScore = (twoToNineScore / 10.0) * 100;
    double occupiedScore = ((renterScore + ownerScore) / 20.0) * 100;

    double[] score = new double[4];
    score[0] = totalScore;
    score[1] = houseHoldScore;
    score[2] = distributionScore;
    score[3] = occupiedScore;

    return score;

  }
}
