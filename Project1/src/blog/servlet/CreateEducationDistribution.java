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
import blog.dal.EducationDistributionDao;
import blog.model.EducationDistribution;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/createeducationdistribution")
public class CreateEducationDistribution extends HttpServlet {

  protected EducationDistributionDao educationDistributionDao;
  protected AddressDao addressDao;

  @Override
  public void init() throws ServletException {
    this.educationDistributionDao = EducationDistributionDao.getInstance();
    this.addressDao = AddressDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/CreateEducationDistribution.jsp").forward(req, resp);
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
        String notHighSchool = req.getParameter("nothighschool");
        String highSchool = req.getParameter("highschool");
        String college = req.getParameter("college");
        if ((total == null) || total.trim().isEmpty()) {
          messages.put("success", "Invalid total");
        } else {
          if ((notHighSchool == null) || notHighSchool.trim().isEmpty()) {
            messages.put("success", "Invalid input for notHighSchool");
          } else {
            if ((college == null) || college.trim().isEmpty()) {
              messages.put("success", "Invalid input for college");
            } else {
              EducationDistribution educationDistribution =
                  new EducationDistribution(Long.parseLong(addressId), Integer.parseInt(total),
                      Integer.parseInt(notHighSchool), Integer.parseInt(highSchool),
                      Integer.parseInt(college));
              try {
                this.educationDistributionDao.create(educationDistribution);
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
              messages.put("success",
                  "Successfully created education distribution with addressId" + addressId);
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
    req.getRequestDispatcher("/CreateEducationDistribution.jsp").forward(req, resp);
  }
}
