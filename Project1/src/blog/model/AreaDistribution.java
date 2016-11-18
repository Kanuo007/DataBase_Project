package neighbor.model;

public class AreaDistribution {
  long PopulationId;
  int Urban;
  int Urbancluster;
  int Rural;
  
  public AreaDistribution(long PopulationId, int Urban, int Urbancluster, int Rural){
    this.PopulationId = PopulationId;
    this.Urban = Urban;
    this.Urbancluster = Urbancluster;
    this.Rural = Rural;    
  }

  public AreaDistribution(long PopulationId) {
    this.PopulationId = PopulationId;
  }

  public long getPopulationId() {
    return PopulationId;
  }

  public void setPopulationId(long populationId) {
    PopulationId = populationId;
  }

  public int getUrban() {
    return Urban;
  }

  public void setUrban(int urban) {
    Urban = urban;
  }

  public int getUrbancluster() {
    return Urbancluster;
  }

  public void setUrbancluster(int urbancluster) {
    Urbancluster = urbancluster;
  }

  public int getRural() {
    return Rural;
  }

  public void setRural(int rural) {
    Rural = rural;
  }
  
  
  
}
