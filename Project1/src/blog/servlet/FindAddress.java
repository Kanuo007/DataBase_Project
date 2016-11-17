package blog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@WebServlet("/findaddresses")
public class FindAddress extends HttpServlet {
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

    List<Address> addresses = new ArrayList<Address>();
    // Retrieve and validate name.
    // addressId is retrieved from the URL query string.
    String addressId = req.getParameter("addressId");
    String state = req.getParameter("state");
    String county = req.getParameter("County");
    if (!invalidAddressId(addressId)) {
      // Retrieve address with addressId, and store as a message.
      long validAddressId = Long.parseLong(addressId);
      try {
        Address address = this.addressDao.getAddressFromAddressId(validAddressId);
        addresses.add(address);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for addressId" + validAddressId);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousAddressId", addressId);
    } else if (!invalidState(state) && invalidCounty(county)) {
      // Retrieve address with state
      try {
        addresses = this.addressDao.getAddressesFromState(state);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for state" + state);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousState", state);
    } else if (!invalidState(state) && !invalidCounty(county)) {
      // Retrieve address with state and county
      try {
        addresses = this.addressDao.getAddressesFromStateAndCounty(state, county);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for state:" + state + "and county:" + county);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousState, previousCounty", state + county);
    } else {
      messages.put("success", "Please enter valid parameter.");
    }
    req.setAttribute("addresses", addresses);
    req.getRequestDispatcher("/FindAddresses.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Address> addresses = new ArrayList<Address>();
    // Retrieve and validate name.
    // addressId is retrieved from the URL query string.
    String addressId = req.getParameter("addressId");
    String state = req.getParameter("state");
    String county = req.getParameter("county");
    if (!invalidAddressId(addressId)) {
      // Retrieve address with addressId, and store as a message.
      long validAddressId = Long.parseLong(addressId);
      try {
        Address address = this.addressDao.getAddressFromAddressId(validAddressId);
        addresses.add(address);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + validAddressId);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousAddressId", addressId);
    } else if (!invalidState(state) && invalidCounty(county)) {
      // Retrieve address with state
      try {
        addresses = this.addressDao.getAddressesFromState(state);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    } else if (!invalidState(state) && !invalidCounty(county)) {
      // Retrieve address with state and county
      try {
        addresses = this.addressDao.getAddressesFromStateAndCounty(state, county);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    } else {
      messages.put("success", "Please enter valid parameter.");
    }
    req.setAttribute("addresses", addresses);
    req.getRequestDispatcher("/FindAddresses.jsp").forward(req, resp);
  }

  public boolean invalidAddressId(String addressId) {
    return (addressId == null) || addressId.trim().isEmpty();
  }

  public boolean invalidState(String state) {
    return ((state == null) || state.trim().isEmpty());
  }

  public boolean invalidCounty(String county) {
    return ((county == null) || county.trim().isEmpty());
  }
}
