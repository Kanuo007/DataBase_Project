package restaurant.model;
//Yudong Wang
public class AgeDistribution {

    protected Long PopulationId;
    protected int LessThan5;
    protected int from5To17;
    protected int from18To24;
    protected int from25To44;
    protected int from45To64;
    protected int MoreThan65;

    public AgeDistribution(Long populationId, int lessThan5, int from5To17, int from18To24, int from25To44, int from45To64, int moreThan65) {
        PopulationId = populationId;
        LessThan5 = lessThan5;
        this.from5To17 = from5To17;
        this.from18To24 = from18To24;
        this.from25To44 = from25To44;
        this.from45To64 = from45To64;
        MoreThan65 = moreThan65;
    }

    public Long getPopulationId() {
        return PopulationId;
    }

    public void setPopulationId(Long populationId) {
        PopulationId = populationId;
    }

    public int getLessThan5() {
        return LessThan5;
    }

    public void setLessThan5(int lessThan5) {
        LessThan5 = lessThan5;
    }

    public int getFrom5To17() {
        return from5To17;
    }

    public void setFrom5To17(int from5To17) {
        this.from5To17 = from5To17;
    }

    public int getFrom18To24() {
        return from18To24;
    }

    public void setFrom18To24(int from18To24) {
        this.from18To24 = from18To24;
    }

    public int getFrom25To44() {
        return from25To44;
    }

    public void setFrom25To44(int from25To44) {
        this.from25To44 = from25To44;
    }

    public int getFrom45To64() {
        return from45To64;
    }

    public void setFrom45To64(int from45To64) {
        this.from45To64 = from45To64;
    }

    public int getMoreThan65() {
        return MoreThan65;
    }

    public void setMoreThan65(int moreThan65) {
        MoreThan65 = moreThan65;
    }

    @Override
    public String toString() {
        return "AgeDistribution{" +
            "PopulationId=" + PopulationId +
            ", LessThan5=" + LessThan5 +
            ", from5To17=" + from5To17 +
            ", from18To24=" + from18To24 +
            ", from25To44=" + from25To44 +
            ", from45To64=" + from45To64 +
            ", MoreThan65=" + MoreThan65 +
            '}';
    }
}
