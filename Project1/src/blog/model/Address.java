package blog.model;

/**
 *
 * @author LiYang
 *
 */
public class Address {
  protected long addressId;
  protected String state;
  protected String county;
  protected int tract;
  protected double landArea;


  public Address(long addressId) {
    super();
    this.addressId = addressId;
  }


  public Address(long addressId, String state, String county, int tract, double landArea) {
    this.addressId = addressId;
    this.state = state;
    this.county = county;
    this.tract = tract;
    this.landArea = landArea;
  }


  public long getAddressId() {
    return this.addressId;
  }


  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }


  public String getState() {
    return this.state;
  }


  public void setState(String state) {
    this.state = state;
  }


  public String getCounty() {
    return this.county;
  }


  public void setCounty(String county) {
    this.county = county;
  }


  public int getTract() {
    return this.tract;
  }


  public void setTract(int tract) {
    this.tract = tract;
  }


  public double getLandArea() {
    return this.landArea;
  }


  public void setLandArea(double landArea) {
    this.landArea = landArea;
  }

}
