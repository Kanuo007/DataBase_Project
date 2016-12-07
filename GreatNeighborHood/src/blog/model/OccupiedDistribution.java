package blog.model;

public class OccupiedDistribution {
  protected int totalOccupiedUnit;
  protected int totalVacantUnit;
  protected int renterOccupiedUnit;
  protected int ownerOccupiedUnit;
  protected Household housedhold;

  public OccupiedDistribution(int totalOccupiedUnit, int totalVacantUnit, int renterOccupiedUnit,
      int ownerOccupiedUnit, Household household) {
    this.totalOccupiedUnit = totalOccupiedUnit;
    this.totalVacantUnit = totalVacantUnit;
    this.renterOccupiedUnit = renterOccupiedUnit;
    this.ownerOccupiedUnit = ownerOccupiedUnit;
    this.housedhold = household;
  }

  public OccupiedDistribution(Household household) {
    this.housedhold = household;
  }

  public int getTotalOccupiedUnit() {
    return this.totalOccupiedUnit;
  }

  public void setTotalOccupiedUnit(int totalOccupiedUnit) {
    this.totalOccupiedUnit = totalOccupiedUnit;
  }

  public int getTotalVacantUnit() {
    return this.totalVacantUnit;
  }

  public void setTotalVacantUnit(int totalVacantUnit) {
    this.totalVacantUnit = totalVacantUnit;
  }

  public int getRenterOccupiedUnit() {
    return this.renterOccupiedUnit;
  }

  public void setRenterOccupiedUnit(int renterOccupiedUnit) {
    this.renterOccupiedUnit = renterOccupiedUnit;
  }

  public int getOwnerOccupiedUnit() {
    return this.ownerOccupiedUnit;
  }

  public void setOwnerOccupiedUnit(int ownerOccupiedUnit) {
    this.ownerOccupiedUnit = ownerOccupiedUnit;
  }

  public Household getHousedhold() {
    return this.housedhold;
  }

  public void setHousedhold(Household housedhold) {
    this.housedhold = housedhold;
  }

}
