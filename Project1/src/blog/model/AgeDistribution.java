package blog.model;

// Yudong Wang
public class AgeDistribution {

  protected Long PopulationId;
  protected int LessThan5;
  protected int from5To17;
  protected int from18To24;
  protected int from25To44;
  protected int from45To64;
  protected int MoreThan65;

  public AgeDistribution(Long populationId, int lessThan5, int from5To17, int from18To24,
      int from25To44, int from45To64, int moreThan65) {
    this.PopulationId = populationId;
    this.LessThan5 = lessThan5;
    this.from5To17 = from5To17;
    this.from18To24 = from18To24;
    this.from25To44 = from25To44;
    this.from45To64 = from45To64;
    this.MoreThan65 = moreThan65;
  }

  public Long getPopulationId() {
    return this.PopulationId;
  }

  public void setPopulationId(Long populationId) {
    this.PopulationId = populationId;
  }

  public int getLessThan5() {
    return this.LessThan5;
  }

  public void setLessThan5(int lessThan5) {
    this.LessThan5 = lessThan5;
  }

  public int getFrom5To17() {
    return this.from5To17;
  }

  public void setFrom5To17(int from5To17) {
    this.from5To17 = from5To17;
  }

  public int getFrom18To24() {
    return this.from18To24;
  }

  public void setFrom18To24(int from18To24) {
    this.from18To24 = from18To24;
  }

  public int getFrom25To44() {
    return this.from25To44;
  }

  public void setFrom25To44(int from25To44) {
    this.from25To44 = from25To44;
  }

  public int getFrom45To64() {
    return this.from45To64;
  }

  public void setFrom45To64(int from45To64) {
    this.from45To64 = from45To64;
  }

  public int getMoreThan65() {
    return this.MoreThan65;
  }

  public void setMoreThan65(int moreThan65) {
    this.MoreThan65 = moreThan65;
  }

  @Override
  public String toString() {
    return "AgeDistribution{" + "PopulationId=" + this.PopulationId + ", LessThan5="
        + this.LessThan5 + ", from5To17=" + this.from5To17 + ", from18To24=" + this.from18To24
        + ", from25To44=" + this.from25To44 + ", from45To64=" + this.from45To64 + ", MoreThan65="
        + this.MoreThan65 + '}';
  }

  public int getScore() {
    int sum = this.getLessThan5() + this.getFrom5To17() + this.getFrom18To24()
        + this.getFrom25To44() + this.getFrom45To64() + this.getMoreThan65();
    if (sum == 0) {
      return 0;
    }
    double score = (100 * (sum - this.getMoreThan65())) / sum;
    return (int) score;
  }
}
