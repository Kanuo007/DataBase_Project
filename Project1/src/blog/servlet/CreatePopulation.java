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
@WebServlet("/createpopulation")
public class CreatePopulation extends HttpServlet {
  protected AddressDao addressDao;
  protected PopulationDao populationDao;

  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
    this.populationDao = PopulationDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/CreatePopulation.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String addressId = req.getParameter("addressid");
    try {
      if ((addressId == null) || addressId.trim().isEmpty()
          || (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) == null)) {
        messages.put("success", "Invalid addressId");
      } else {
        // Create the Population.
        String total = req.getParameter("total");
        if ((total == null) || total.trim().isEmpty()) {
          messages.put("success", "Invalid total");
        } else {
          try {
            Population population =
                new Population(Long.parseLong(addressId), Integer.parseInt(total));
            population = this.populationDao.create(population);
            messages.put("success",
                "Successfully created population with populationId" + population.getPopulationId());
          } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
          }
        }
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    req.getRequestDispatcher("/CreatePopulation.jsp").forward(req, resp);
  }
}

