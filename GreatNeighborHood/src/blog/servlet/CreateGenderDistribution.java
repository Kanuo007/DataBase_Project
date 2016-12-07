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

import blog.dal.*;
//import blog.dal.GenderDistributionDao;
import blog.model.GenderDistribution;

@WebServlet("/creategenderdistribution")
public class CreateGenderDistribution extends HttpServlet {
	
  protected GenderDistributionDao genderDistributionDao;
  protected AddressDao addressDao;

  @Override
  public void init() throws ServletException {
    this.genderDistributionDao = GenderDistributionDao.getInstance();
    this.addressDao = AddressDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/CreateGenderDistribution.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate.
    String addressId = req.getParameter("addressid");
    try {
      if ((addressId == null) || addressId.trim().isEmpty()
          || (this.addressDao.getAddressFromAddressId(Long.parseLong(addressId)) == null)) {
        messages.put("success", "Invalid addressId");
      } else {
        String total = req.getParameter("total");
        String male = req.getParameter("male");
        String female = req.getParameter("female");
        if ((total == null) || total.trim().isEmpty()) {
          messages.put("success", "Invalid total");
        } else {
          if ((male == null) || male.trim().isEmpty()) {
            messages.put("success", "Invalid input for male");
          } else {
            if ((female == null) || female.trim().isEmpty()) {
              messages.put("success", "Invalid input for female");
            } else {
              GenderDistribution genderDistribution =
                  new GenderDistribution(Long.parseLong(addressId), Integer.parseInt(total),
                      Integer.parseInt(male), Integer.parseInt(female));
              try {
                this.genderDistributionDao.create(genderDistribution);
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
              messages.put("success",
                  "Successfully created gender distribution with addressId" + addressId);
            }
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
    req.getRequestDispatcher("/CreateGenderDistribution.jsp").forward(req, resp);
  }
}
