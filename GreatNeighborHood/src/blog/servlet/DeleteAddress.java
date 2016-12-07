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
@WebServlet("/deleteaddress")

public class DeleteAddress extends HttpServlet {
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
    // Provide a title and render the JSP.
    messages.put("title", "Delete Address");
    req.getRequestDispatcher("/DeleteAddress.jsp").forward(req, resp);
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
      Address address = new Address(Long.parseLong(addressId));
      try {
        address = this.addressDao.delete(address);
        // Update the message.
        if (address == null) {
          messages.put("title", "Successfully deleted " + addressId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + addressId);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/DeleteAddress.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }
}
