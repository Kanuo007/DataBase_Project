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

import blog.dal.AgeDistributionDao;
import blog.dal.DiversityDao;
import blog.dal.EducationDistributionDao;
import blog.dal.PopulationDao;
import blog.model.AgeDistribution;
import blog.model.Diversity;
import blog.model.EducationDistribution;
import blog.model.Population;

/**
 *
 * @author LiYang
 *
 */
@WebServlet("/displaypopulation")
public class DisplayPopulation extends HttpServlet {
  protected PopulationDao populationDao;
  protected AgeDistributionDao ageDistributionDao;
  protected DiversityDao diversityDistributionDao;
  protected EducationDistributionDao educationDistributionDao;


  @Override
  public void init() throws ServletException {
    this.populationDao = PopulationDao.getInstance();
    this.ageDistributionDao = AgeDistributionDao.getInstance();
    this.diversityDistributionDao = DiversityDao.getInstance();
    this.educationDistributionDao = EducationDistributionDao.getInstance();
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
    Diversity diversity = null;
    AgeDistribution ageDistribution = null;
    EducationDistribution educationDistribution = null;
    String populationId = req.getParameter("populationId");
    if (!invalidPopulationId(populationId)) {
      // Retrieve address with addressId, and store as a message.
      long validPopulationId = Long.parseLong(populationId);
      try {
        population = this.populationDao.getPopulationFromPopulationId(validPopulationId);
        diversity = this.diversityDistributionDao.getDiversityByPopulationId(validPopulationId);
        ageDistribution =
            this.ageDistributionDao.getAgeDistributionByPopulationId(validPopulationId);
        educationDistribution = this.educationDistributionDao
            .getEducationDistributionFromPopulationId(validPopulationId);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying population with populationId" + validPopulationId);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousPopulationId", populationId);
    } else {
      messages.put("success", "Please enter valid parameter.");
    }
    req.setAttribute("population", population);
    req.setAttribute("diversity", diversity);
    req.setAttribute("agedistribution", ageDistribution);
    req.setAttribute("educationdistribution", educationDistribution);
    req.getRequestDispatcher("/DisplayPopulation.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Population population = null;
    Diversity diversity = null;
    AgeDistribution ageDistribution = null;
    EducationDistribution educationDistribution = null;
    String populationId = req.getParameter("populationId");
    try {
      if (!invalidPopulationId(populationId)) {
        // Retrieve address with addressId, and store as a message.
        long validPopulationId = Long.parseLong(populationId);
        try {
          population = this.populationDao.getPopulationFromPopulationId(validPopulationId);
          diversity = this.diversityDistributionDao.getDiversityByPopulationId(validPopulationId);
          ageDistribution =
              this.ageDistributionDao.getAgeDistributionByPopulationId(validPopulationId);
          educationDistribution = this.educationDistributionDao
              .getEducationDistributionFromPopulationId(validPopulationId);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying population with populationId" + validPopulationId);
        // Save the previous search term, so it can be used as the default
        // in the input box when rendering FindUsers.jsp.
        messages.put("previousPopulationId", populationId);
      } else {
        messages.put("success", "Please enter valid parameter.");
      }
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    req.setAttribute("population", population);
    req.setAttribute("diversity", diversity);
    req.setAttribute("agedistribution", ageDistribution);
    req.setAttribute("educationdistribution", educationDistribution);
    req.getRequestDispatcher("/DisplayPopulation.jsp").forward(req, resp);
  }

  public boolean invalidPopulationId(String populationId) {
    return (populationId == null) || populationId.trim().isEmpty();
  }

  /**
   * Given valid ageDistribution, diversity and educationDistribution, return the calculated score
   */
  public double score(AgeDistribution ageDistribution, Diversity diversity,
      EducationDistribution educationDistribution) {
    return (0.3 * ageDistribution.getScore()) + (0.3 * diversity.getScore())
        + (0.4 * educationDistribution.getScore());
  }

}
