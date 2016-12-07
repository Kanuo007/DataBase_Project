package blog.model;

/**
 *
 * @author LiYang
 *
 */
public class EducationDistribution extends Population {
  protected int notHighSchool;
  protected int highSchool;
  protected int college;

  public EducationDistribution(long addressId, int total, int notHighSchool, int highSchool,
      int college) {
    super(addressId, total);
    this.notHighSchool = notHighSchool;
    this.highSchool = highSchool;
    this.college = college;
  }

  public EducationDistribution(long populationId, long addressId, int total, int notHighSchool,
      int highSchool, int college) {
    super(populationId, addressId, total);
    this.notHighSchool = notHighSchool;
    this.highSchool = highSchool;
    this.college = college;
  }

  public int getNotHighSchool() {
    return this.notHighSchool;
  }



  public void setNotHighSchool(int notHighSchool) {
    this.notHighSchool = notHighSchool;
  }


  public int getHighSchool() {
    return this.highSchool;
  }

  public void setHighSchool(int highSchool) {
    this.highSchool = highSchool;
  }

  public int getCollege() {
    return this.college;
  }

  public void setCollege(int college) {
    this.college = college;
  }

  public int getScore() {
    int total = this.getNotHighSchool() + this.getHighSchool() + this.getCollege();
    if (total == 0) {
      return 0;
    }
    return (100 * (this.getCollege() + this.getHighSchool())) / total;
  }
}
