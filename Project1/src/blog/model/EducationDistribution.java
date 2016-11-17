package blog.model;

/**
 *
 * @author LiYang
 *
 */
public class EducationDistribution extends Population {
  protected int notHighSchool;
  protected int college;

  public EducationDistribution(long addressId, int total, int notHighSchool, int college) {
    super(addressId, total);
    this.notHighSchool = notHighSchool;
    this.college = college;
  }

  public EducationDistribution(long populationId, long addressId, int total, int notHighSchool,
      int college) {
    super(populationId, addressId, total);
    this.notHighSchool = notHighSchool;
    this.college = college;
  }

  public int getNotHighSchool() {
    return this.notHighSchool;
  }

  public void setNotHighSchool(int notHighSchool) {
    this.notHighSchool = notHighSchool;
  }

  public int getCollege() {
    return this.college;
  }

  public void setCollege(int college) {
    this.college = college;
  }
}
