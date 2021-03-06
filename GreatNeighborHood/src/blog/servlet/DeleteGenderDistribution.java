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
 * @author AndrewSong
 *
 */
@WebServlet("/deletegenderistribution")
public class DeleteGenderDistribution extends HttpServlet {
  protected PopulationDao populationDao;
  protected AddressDao addressDao;
  protected GenderDistributionDao genderDistributionDao;

  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
    this.populationDao = PopulationDao.getInstance();
    this.genderDistributionDao = GenderDistributionDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Gender Distribution");
    req.getRequestDispatcher("/DeleteGenderDistribution.jsp").forward(req, resp);
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
    GenderDistribution genderDistribution = null;

    try {
      if (!invalidAddressId(addressId)
          && (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) != null)) {
        // Retrieve address with addressId, and store as a message.
        long validAddressId = Long.parseLong(addressId);
        try {
          Population population = this.populationDao.getPopulationFromAddressId(validAddressId);
          if (population == null) {
            messages.put("success",
                "Gender Distribution with addressId " + addressId + "doesn't exist");
            System.out.println("popu with addressId doesn't exist");
          } else {
            long populationId = population.getPopulationId();
            genderDistribution = this.genderDistributionDao
                .getGenderDistributionFromPopulationId(populationId);
            if (genderDistribution != null) {
              System.out.println("gender distri exist, now to delete");
              genderDistribution = this.genderDistributionDao.delete(genderDistribution);
              if (genderDistribution == null) {
                messages.put("title", "Successfully deleted gender distribution");
                messages.put("disableSubmit", "true");
              } else {
                messages.put("title", "Failed to delete gender distribution with addressId"
                    + genderDistribution.getAddressId());
                messages.put("disableSubmit", "false");
              }
            } else {
              System.out.println("gender distri with addressId doesn't exist");

              messages.put("success",
                  "Gender Distribution doesn't exist with addressId" + addressId);
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

    req.getRequestDispatcher("/DeleteGenderDistribution.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }
}
