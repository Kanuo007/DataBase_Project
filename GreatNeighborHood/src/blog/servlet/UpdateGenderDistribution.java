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
import blog.dal.GenderDistributionDao;
import blog.dal.PopulationDao;
import blog.model.GenderDistribution;
import blog.model.Population;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/updategenderdistribution")

public class UpdateGenderDistribution extends HttpServlet {
  protected GenderDistributionDao genderDistributionDao;
  protected AddressDao addressDao;
  protected PopulationDao populationDao;

  @Override
  public void init() throws ServletException {
    this.genderDistributionDao = GenderDistributionDao.getInstance();
    this.addressDao = AddressDao.getInstance();
    this.populationDao = PopulationDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve address and validate.
    String addressId = req.getParameter("addressid");
    try {
      if (invalidAddressId(addressId)
          || (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) == null)) {
        messages.put("success", "Please enter a valid addressId.");
      } else {
        try {
          long validAddressId = Long.parseLong(addressId);
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          GenderDistribution genderDistribution = null;
          if (population == null) {
            messages.put("success",
                "Gender Distribution with addressId " + addressId + "doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            genderDistribution = this.genderDistributionDao
                .getGenderDistributionFromPopulationId(populationId);
            if (genderDistribution == null) {
              messages.put("success",
                  "Gender Distribution with addressId " + addressId + "doesn't exist");
            }
          }
          req.setAttribute("genderdistribution", genderDistribution);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    req.getRequestDispatcher("/UpdateGenderDistribution.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve address and validate.
    String addressId = req.getParameter("addressid");
    String male = req.getParameter("male");
    String female = req.getParameter("female");

    try {
      if (invalidAddressId(addressId)
          || (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) == null)) {
        messages.put("success", "Please enter a valid addressId.");
      } else {
        try {
          long validAddressId = Long.parseLong(addressId);
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          GenderDistribution genderDistribution = null;
          if (population == null) {
            messages.put("success",
                "Gender Distribution with addressId " + addressId + "doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            genderDistribution = this.genderDistributionDao
                .getGenderDistributionFromPopulationId(populationId);
            if (genderDistribution != null) {
              if (invalidMale(male) && invalidCollege(female)) {
                messages.put("success", "Please provide at least one valid parameter");
              } else {
                if (!invalidMale(male)) {
                  this.genderDistributionDao.updateMale(genderDistribution,
                      Integer.parseInt(male));
                }
                if (!invalidCollege(female)) {
                  this.genderDistributionDao.updateFemale(genderDistribution,
                      Integer.parseInt(female));
                }
                messages.put("success",
                    "Succeed updating gender distribution with addressId" + addressId);
              }
            } else {
              messages.put("success",
                  "Gender Distribution with addressId " + addressId + "doesn't exist");
            }
          }
          req.setAttribute("genderdistribution", genderDistribution);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    req.getRequestDispatcher("/UpdateGenderDistribution.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }

  public boolean invalidMale(String male) {
    return (male == null) || male.trim().isEmpty();
  }

  public boolean invalidCollege(String female) {
    return (female == null) || female.trim().isEmpty();
  }
}
