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
@WebServlet("/deleteeducationdistribution")
public class DeleteEducationDistribution extends HttpServlet {
  protected PopulationDao populationDao;
  protected AddressDao addressDao;
  protected EducationDistributionDao educationDistributionDao;

  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
    this.populationDao = PopulationDao.getInstance();
    this.educationDistributionDao = EducationDistributionDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Education Distribution");
    req.getRequestDispatcher("/DeleteEducationDistribution.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate address Id.
    String addressId = req.getParameter("addressid");
    System.out.println(addressId);
    EducationDistribution educationDistribution = null;

    try {
      if (!invalidAddressId(addressId)
          && (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) != null)) {
        // Retrieve address with addressId, and store as a message.
        long validAddressId = Long.parseLong(addressId);
        try {
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          if (population == null) {
            messages.put("success",
                "Education Distribution with addressId " + addressId + "doesn't exist");
            System.out.println("popu with addressId doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            educationDistribution = this.educationDistributionDao
                .getEducationDistributionFromPopulationId(populationId);
            if (educationDistribution != null) {
              System.out.println("education distri exist, now to delete");
              educationDistribution = this.educationDistributionDao.delete(educationDistribution);
              if (educationDistribution == null) {
                messages.put("title", "Successfully deleted education distribution");
                messages.put("disableSubmit", "true");
              } else {
                messages.put("title", "Failed to delete education distribution with addressId"
                    + educationDistribution.getAddressId());
                messages.put("disableSubmit", "false");
              }
            } else {
              System.out.println("education distri with addressId doesn't exist");

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

    req.getRequestDispatcher("/DeleteEducationDistribution.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }
}
