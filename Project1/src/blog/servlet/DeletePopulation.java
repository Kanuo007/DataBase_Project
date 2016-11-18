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
import blog.dal.PopulationDao;
import blog.model.Population;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/deletepopulation")

public class DeletePopulation extends HttpServlet {
  protected PopulationDao populationDao;
  protected AddressDao addressDao;

  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
    this.populationDao = PopulationDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Address");
    req.getRequestDispatcher("/DeletePopulation.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate address Id.
    String addressId = req.getParameter("addressid");
    if (invalidAddressId(addressId)) {
      messages.put("title", "Invalid AddressId");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the Address.
      Population population = null;
      try {
        population = this.populationDao.getPopulationFromAddressId(Long.parseLong(addressId));
      } catch (NumberFormatException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      try {
        population = this.populationDao.delete(population);
        // Update the message.
        if (population == null) {
          messages.put("title", "Successfully deleted population");
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + population.getPopulationId());
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/DeletePopulation.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }
}
