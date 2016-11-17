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
import blog.model.Address;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/updateaddress")

public class UpdateAddress extends HttpServlet {

  protected AddressDao addressDao;

  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve address and validate.
    String addressId = req.getParameter("addressId");
    if (invalidAddressId(addressId)) {
      messages.put("success", "Please enter a valid addressId.");
    } else {
      try {
        Address address = this.addressDao.getAddressFromAddressId(Long.parseLong(addressId));
        if (address == null) {
          messages.put("success", "AddressId does not exist.");
        }
        req.setAttribute("address", address);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UpdateAddress.jsp").forward(req, resp);
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
        Address address = this.addressDao.getAddressFromAddressId(Long.parseLong(addressId));
        if (address == null) {
          messages.put("success", "AddressId does not exist.");
        } else {
          String newLandArea = req.getParameter("landarea");
          if (invalidLandArea(newLandArea)) {
            messages.put("success", "Please enter a valid land area.");
          } else {
            address = this.addressDao.updateLandArea(address, Double.parseDouble(newLandArea));
            messages.put("success", "Successfully updated " + addressId);
          }
        }
        req.setAttribute("address", address);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/UpdateAddress.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }

  public boolean invalidLandArea(String landArea) {
    return (landArea == null) || landArea.trim().isEmpty();
  }
}
