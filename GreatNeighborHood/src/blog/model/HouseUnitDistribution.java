package blog.model;

// Yudong Wang
public class HouseUnitDistribution {

  protected int HouseHoldId;
  protected int SingleUnit;
  protected int TwoToNineUnit;
  protected int TenMoreUnit;
  protected int MobileHome;

  public HouseUnitDistribution(int houseHoldId, int singleUnit, int twoToNineUnit, int tenMoreUnit,
      int mobileHome) {
    this.HouseHoldId = houseHoldId;
    this.SingleUnit = singleUnit;
    this.TwoToNineUnit = twoToNineUnit;
    this.TenMoreUnit = tenMoreUnit;
    this.MobileHome = mobileHome;
  }

  public int getHouseHoldId() {
    return this.HouseHoldId;
  }

  public void setHouseHoldId(int houseHoldId) {
    this.HouseHoldId = houseHoldId;
  }


  public int getSingleUnit() {
    return this.SingleUnit;
  }

  public void setSingleUnit(int singleUnit) {
    this.SingleUnit = singleUnit;
  }

  public int getTwoToNineUnit() {
    return this.TwoToNineUnit;
  }

  public void setTwoToNineUnit(int twoToNineUnit) {
    this.TwoToNineUnit = twoToNineUnit;
  }

  public int getTenMoreUnit() {
    return this.TenMoreUnit;
  }

  public void setTenMoreUnit(int tenMoreUnit) {
    this.TenMoreUnit = tenMoreUnit;
  }

  public int getMobileHome() {
    return this.MobileHome;
  }

  public void setMobileHome(int mobileHome) {
    this.MobileHome = mobileHome;
  }

  @Override
  public String toString() {
    return "HouseUnitDistribution{" + "HouseHoldId=" + this.HouseHoldId + ", SingleUnit="
        + this.SingleUnit + ", TwoToNineUnit=" + this.TwoToNineUnit + ", TenMoreUnit="
        + this.TenMoreUnit + ", MobileHome=" + this.MobileHome + '}';
  }
}
