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
@WebServlet("/updatepopulation")

public class UpdatePopulation extends HttpServlet {

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

    // Retrieve address and validate.
    String addressId = req.getParameter("addressid");
    if (invalidAddressId(addressId)) {
      messages.put("success", "Please enter a valid addressId.");
    } else {
      try {
        Population population =
            this.populationDao.getPopulationFromAddressId(Long.parseLong(addressId));
        if (population == null) {
          messages.put("success", "Population does not exist.");
        }
        req.setAttribute("population", population);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UpdatePopulation.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve address and validate.
    String addressId = req.getParameter("addressid");
    if (invalidAddressId(addressId)) {
      messages.put("success", "Please enter a valid addressId.");
    } else {
      try {
        Population population =
            this.populationDao.getPopulationFromAddressId(Long.parseLong(addressId));
        if (population == null) {
          messages.put("success", "Population does not exist.");
        } else {
          String newTotal = req.getParameter("total");
          if ((newTotal == null) || newTotal.trim().isEmpty()) {
            messages.put("success", "Invalid total");
          } else {
            population = this.populationDao.updateTotal(population, Integer.parseInt(newTotal));
            messages.put("success",
                "Successfully updated population with populationid" + population.getPopulationId());
          }
        }
        req.setAttribute("population", population);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/UpdatePopulation.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }

}

