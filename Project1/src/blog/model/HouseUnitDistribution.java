package restaurant.model;
//Yudong Wang
public class HouseUnitDistribution {

	protected int HouseHoldId;
	protected int TotalHouseUnit;
	protected int SingleUnit;
	protected int TwoToNineUnit;
	protected int TenMoreUnit;
	protected int MobileHome;

    public HouseUnitDistribution(int houseHoldId, int totalHouseUnit, int singleUnit, int twoToNineUnit, int tenMoreUnit, int mobileHome) {
        HouseHoldId = houseHoldId;
        TotalHouseUnit = totalHouseUnit;
        SingleUnit = singleUnit;
        TwoToNineUnit = twoToNineUnit;
        TenMoreUnit = tenMoreUnit;
        MobileHome = mobileHome;
    }

    public int getHouseHoldId() {
        return HouseHoldId;
    }

    public void setHouseHoldId(int houseHoldId) {
        HouseHoldId = houseHoldId;
    }

    public int getTotalHouseUnit() {
        return TotalHouseUnit;
    }

    public void setTotalHouseUnit(int totalHouseUnit) {
        TotalHouseUnit = totalHouseUnit;
    }

    public int getSingleUnit() {
        return SingleUnit;
    }

    public void setSingleUnit(int singleUnit) {
        SingleUnit = singleUnit;
    }

    public int getTwoToNineUnit() {
        return TwoToNineUnit;
    }

    public void setTwoToNineUnit(int twoToNineUnit) {
        TwoToNineUnit = twoToNineUnit;
    }

    public int getTenMoreUnit() {
        return TenMoreUnit;
    }

    public void setTenMoreUnit(int tenMoreUnit) {
        TenMoreUnit = tenMoreUnit;
    }

    public int getMobileHome() {
        return MobileHome;
    }

    public void setMobileHome(int mobileHome) {
        MobileHome = mobileHome;
    }

    @Override
    public String toString() {
        return "HouseUnitDistribution{" +
            "HouseHoldId=" + HouseHoldId +
            ", TotalHouseUnit=" + TotalHouseUnit +
            ", SingleUnit=" + SingleUnit +
            ", TwoToNineUnit=" + TwoToNineUnit +
            ", TenMoreUnit=" + TenMoreUnit +
            ", MobileHome=" + MobileHome +
            '}';
    }
}
