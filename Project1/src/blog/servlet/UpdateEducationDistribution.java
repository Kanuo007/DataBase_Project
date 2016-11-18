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
import blog.dal.EducationDistributionDao;
import blog.dal.PopulationDao;
import blog.model.EducationDistribution;
import blog.model.Population;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/updateeducationdistribution")

public class UpdateEducationDistribution extends HttpServlet {
  protected EducationDistributionDao educationDistributionDao;
  protected AddressDao addressDao;
  protected PopulationDao populationDao;

  @Override
  public void init() throws ServletException {
    this.educationDistributionDao = EducationDistributionDao.getInstance();
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
          EducationDistribution educationDistribution = null;
          if (population == null) {
            messages.put("success",
                "Education Distribution with addressId " + addressId + "doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            educationDistribution = this.educationDistributionDao
                .getEducationDistributionFromPopulationId(populationId);
            if (educationDistribution == null) {
              messages.put("success",
                  "Education Distribution with addressId " + addressId + "doesn't exist");
            }
          }
          req.setAttribute("educationdistribution", educationDistribution);
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

    req.getRequestDispatcher("/UpdateEducationDistribution.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve address and validate.
    String addressId = req.getParameter("addressid");
    String notHighSchool = req.getParameter("nothighschool");
    String college = req.getParameter("college");

    try {
      if (invalidAddressId(addressId)
          || (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) == null)) {
        messages.put("success", "Please enter a valid addressId.");
      } else {
        try {
          long validAddressId = Long.parseLong(addressId);
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          EducationDistribution educationDistribution = null;
          if (population == null) {
            messages.put("success",
                "Education Distribution with addressId " + addressId + "doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            educationDistribution = this.educationDistributionDao
                .getEducationDistributionFromPopulationId(populationId);
            if (educationDistribution != null) {
              if (invalidNotHighSchool(notHighSchool) && invalidCollege(college)) {
                messages.put("success", "Please provide at least one valid parameter");
              } else {
                if (!invalidNotHighSchool(notHighSchool)) {
                  this.educationDistributionDao.updateNotHighSchool(educationDistribution,
                      Integer.parseInt(notHighSchool));
                }
                if (!invalidCollege(college)) {
                  this.educationDistributionDao.updateCollege(educationDistribution,
                      Integer.parseInt(college));
                }
                messages.put("success",
                    "Succeed updating education distribution with addressId" + addressId);
              }
            } else {
              messages.put("success",
                  "Education Distribution with addressId " + addressId + "doesn't exist");
            }
          }
          req.setAttribute("educationdistribution", educationDistribution);
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

    req.getRequestDispatcher("/UpdateEducationDistribution.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }

  public boolean invalidNotHighSchool(String notHighSchool) {
    return (notHighSchool == null) || notHighSchool.trim().isEmpty();
  }

  public boolean invalidCollege(String college) {
    return (college == null) || college.trim().isEmpty();
  }
}
