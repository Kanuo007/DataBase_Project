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
@WebServlet("/findeducationdistribution")

public class FindEducationDistribution extends HttpServlet {
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

    // Retrieve and validate name.
    // addressId is retrieved from the URL query string.
    String addressId = req.getParameter("addressId");
    EducationDistribution educationDistribution = null;
    try {
      if (!invalidAddressId(addressId)
          && (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) != null)) {
        // Retrieve address with addressId, and store as a message.
        long validAddressId = Long.parseLong(addressId);
        try {
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          long populationId = population.getPopulationId();
          educationDistribution =
              this.educationDistributionDao.getEducationDistributionFromPopulationId(populationId);
          if (educationDistribution != null) {
            messages.put("success", "Displaying results for addressId" + addressId);
          } else {
            messages.put("success",
                "Education Distribution doesn't exist with addressId" + addressId);
          }
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        // Save the previous search term, so it can be used as the default
        // in the input box when rendering FindUsers.jsp.
        messages.put("previousAddressId", addressId);
      } else {
        messages.put("success", "Please enter valid addressId.");
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    req.setAttribute("educationdistribution", educationDistribution);
    req.getRequestDispatcher("/FindEducationDistribution.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    // addressId is retrieved from the URL query string.
    String addressId = req.getParameter("addressid");
    EducationDistribution educationDistribution = null;
    try {
      if (!invalidAddressId(addressId)
          && (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) != null)) {
        // Retrieve address with addressId, and store as a message.
        long validAddressId = Long.parseLong(addressId);
        System.out.println(validAddressId);
        try {
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          if (population == null) {
            messages.put("success",
                "Education Distribution with addressId " + addressId + "doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            educationDistribution = this.educationDistributionDao
                .getEducationDistributionFromPopulationId(populationId);
            if (educationDistribution != null) {
              messages.put("success", "Displaying results for addressId" + addressId);
            } else {
              messages.put("success",
                  "Education Distribution doesn't exist with addressId" + addressId);
            }
          }
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        // Save the previous search term, so it can be used as the default
        // in the input box when rendering FindUsers.jsp.
        messages.put("previousAddressId", addressId);
      } else {
        messages.put("success", "Please enter valid addressId.");
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    req.setAttribute("educationdistribution", educationDistribution);
    req.getRequestDispatcher("/FindEducationDistribution.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }

}
