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
@WebServlet("/createaddress")
public class CreateAddress extends HttpServlet {

  protected AddressDao addressDao;

  @Override
  public void init() throws ServletException {
    this.addressDao = AddressDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/CreateAddress.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String addressId = req.getParameter("addressid");
    if ((addressId == null) || addressId.trim().isEmpty()) {
      messages.put("success", "Invalid addressId");
    } else {
      String state = req.getParameter("state");
      String county = req.getParameter("county");
      String tract = req.getParameter("tract");
      String landArea = req.getParameter("landarea");
      try {
        // Exercise: parse the input for StatusLevel.
        Address address = new Address(Long.parseLong(addressId), state, county,
            Integer.parseInt(tract), Double.parseDouble(landArea));
        address = this.addressDao.create(address);
        messages.put("success", "Successfully created " + addressId);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/CreateAddress.jsp").forward(req, resp);
  }
}
