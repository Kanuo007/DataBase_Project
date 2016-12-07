package blog.model;

/*
 * wen chen
 */
public class Diversity {
  private long PopulationId;
  private int hispanic;
  private int white;
  private int black;
  private int aian;
  private int asian;
  private int NHOPI;
  private int SOR;

  public Diversity(long PopulationId, int hispanic, int white, int black, int aian, int asian,
      int NHOPI, int SOR) {
    this.PopulationId = PopulationId;
    this.hispanic = hispanic;
    this.white = white;
    this.black = black;
    this.aian = aian;
    this.asian = asian;
    this.NHOPI = NHOPI;
    this.SOR = SOR;
  }

  public Diversity(long PopulationId) {
    this.PopulationId = PopulationId;
  }

  public long getPopulationId() {
    return this.PopulationId;
  }

  public void setPopulationId(long populationId) {
    this.PopulationId = populationId;
  }

  public int getHispanic() {
    return this.hispanic;
  }

  public void setHispanic(int hispanic) {
    this.hispanic = hispanic;
  }

  public int getWhite() {
    return this.white;
  }

  public void setWhite(int white) {
    this.white = white;
  }

  public int getBlack() {
    return this.black;
  }

  public void setBlack(int black) {
    this.black = black;
  }

  public int getAian() {
    return this.aian;
  }

  public void setAian(int aian) {
    this.aian = aian;
  }

  public int getAsian() {
    return this.asian;
  }

  public void setAsian(int asian) {
    this.asian = asian;
  }

  public int getNHOPI() {
    return this.NHOPI;
  }

  public void setNHOPI(int nHOPI) {
    this.NHOPI = nHOPI;
  }

  public int getSOR() {
    return this.SOR;
  }

  public void setSOR(int sOR) {
    this.SOR = sOR;
  }

  public int getScore() {
    int median = (this.getHispanic() + this.getAsian() + this.getBlack() + this.getAian()
        + this.getNHOPI() + this.getSOR() + this.getWhite()) / 7;
    double sum = Math.pow(this.getHispanic() - median, 2);
    sum += Math.pow(this.getAsian() - median, 2);
    sum += Math.pow(this.getBlack() - median, 2);
    sum += Math.pow(this.getAian() - median, 2);
    sum += Math.pow(this.getNHOPI() - median, 2);
    sum += Math.pow(this.getSOR() - median, 2);
    sum += Math.pow(this.getWhite() - median, 2);
    int sd = (int) Math.sqrt(sum / 7);
    return 100 - (((sd / 1000) + 1) * 3);
  }

  @Override
  public String toString() {
    return "Diversity [PopulationId=" + this.PopulationId + ", hispanic=" + this.hispanic
        + ", white=" + this.white + ", black=" + this.black + ", aian=" + this.aian + ", asian="
        + this.asian + ", NHOPI=" + this.NHOPI + ", SOR=" + this.SOR + "]";
  }


}
