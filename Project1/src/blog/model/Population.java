package blog.model;

/**
 *
 * @author LiYang
 *
 */
public class Population {
  protected long populationId;
  protected long addressId;
  protected int total;

  public Population(long addressId, int total) {
    this.addressId = addressId;
    this.total = total;
  }

  public Population(long populationId, long addressId, int total) {
    this.populationId = populationId;
    this.addressId = addressId;
    this.total = total;
  }

  public long getPopulationId() {
    return this.populationId;
  }

  public void setPopulationId(long populationId) {
    this.populationId = populationId;
  }

  public long getAddressId() {
    return this.addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
