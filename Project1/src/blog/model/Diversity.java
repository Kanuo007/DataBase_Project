package neighbor.model;

public class Diversity {
  private long PopulationId;
  private int hispanic;
  private int white;
  private int black;
  private int aian;
  private int asian;
  private int NHOPI;
  private int SOR;
  
  public Diversity(long PopulationId, int hispanic, int white, int black, int aian, 
      int asian, int NHOPI, int SOR){
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
    return PopulationId;
  }

  public void setPopulationId(long populationId) {
    PopulationId = populationId;
  }

  public int getHispanic() {
    return hispanic;
  }

  public void setHispanic(int hispanic) {
    this.hispanic = hispanic;
  }

  public int getWhite() {
    return white;
  }

  public void setWhite(int white) {
    this.white = white;
  }

  public int getBlack() {
    return black;
  }

  public void setBlack(int black) {
    this.black = black;
  }

  public int getAian() {
    return aian;
  }

  public void setAian(int aian) {
    this.aian = aian;
  }

  public int getAsian() {
    return asian;
  }

  public void setAsian(int asian) {
    this.asian = asian;
  }

  public int getNHOPI() {
    return NHOPI;
  }

  public void setNHOPI(int nHOPI) {
    NHOPI = nHOPI;
  }

  public int getSOR() {
    return SOR;
  }

  public void setSOR(int sOR) {
    SOR = sOR;
  }
  
 
}
