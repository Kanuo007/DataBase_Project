package blog.model;
/**
 * 
 * @author AndrewSong
 *
 */

public class GenderDistribution extends Population {
	protected int male;
	protected int female;

	public GenderDistribution(long addressId, int total, int male, int female) {
	    super(addressId, total);
	    this.male = male;
	    this.female = female;
	  }
	
	public GenderDistribution(long populationId, long addressId, int total, int male,
		      int female) {
		    super(populationId, addressId, total);
		    this.male = male;
		    this.female = female;
		  }

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}
}
