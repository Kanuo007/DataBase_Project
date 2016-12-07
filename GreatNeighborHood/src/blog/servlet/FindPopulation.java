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

import blog.dal.PopulationDao;
import blog.model.Population;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/findpopulation")
public class FindPopulation extends HttpServlet {
  protected PopulationDao populationDao;

  @Override
  public void init() throws ServletException {
    this.populationDao = PopulationDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate.
    // addressId is retrieved from the URL query string.
    Population population = null;
    String addressId = req.getParameter("addressId");
    if (!invalidAddressId(addressId)) {
      // Retrieve address with addressId, and store as a message.
      long validAddressId = Long.parseLong(addressId);
      try {
        population = this.populationDao.getPopulationFromAddressId(validAddressId);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying population with addressId" + validAddressId);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousAddressId", addressId);
    } else {
      messages.put("success", "Please enter valid parameter.");
    }
    req.setAttribute("population", population);
    req.getRequestDispatcher("/FindPopulation.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Population population = null;
    String addressId = req.getParameter("addressId");
    try {
      if (!invalidAddressId(addressId)
          && (this.populationDao.getPopulationFromAddressId(Long.parseLong(addressId)) != null)) {
        // Retrieve address with addressId, and store as a message.
        long validAddressId = Long.parseLong(addressId);
        try {
          population = this.populationDao.getPopulationFromAddressId(validAddressId);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying population with addressId" + validAddressId);
        // Save the previous search term, so it can be used as the default
        // in the input box when rendering FindUsers.jsp.
        messages.put("previousAddressId", addressId);
      } else {
        messages.put("success", "Please enter valid parameter.");
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    req.setAttribute("population", population);
    req.getRequestDispatcher("/FindPopulation.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }
}
