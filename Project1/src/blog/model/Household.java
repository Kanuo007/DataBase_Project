package blog.model;

public class Household {
  protected int householdId;
  protected Address address;
  protected long totalIncomeOfHouseholds;
  protected int AVGIncomeOfHouseholds;
  protected int NumOfFamilyWithChildUnderSix;
  protected int NumOfFamilyWithOneOrMoreUnder18;
  protected int totalPersonsInHouseholds;
  protected int NumOfHouseholdsWithoutTeleService;
  protected int NumOfHouseholdsLackFacilities;
  protected int NumOfHouseholdsBuiltAfter2010;
  protected int medianHouseValue;
  protected long totalHouseValue;
  protected int NumOfAssistanceFamilies;
  protected int totalNumOfHouseholds;
  protected int NumOfHouseholdsMovedInAfter2010;


  public Household(int householdId) {
    this.householdId = householdId;
  }

  public Household(int householdId, Address address, long totalIncomeOfHouseholds,
      int AVGIncomeOfHouseholds, int NumOfFamilyWithChildUnderSix,
      int NumOfFamilyWithOneOrMoreUnder18, int totalPersonsInHouseholds,
      int NumOfHouseholdsWithoutTeleService, int NumOfHouseholdsLackFacilities,
      int NumOfHouseholdsBuiltAfter2010, int medianHouseValue, long totalHouseValue,
      int NumOfAssistanceFamilies, int totalNumOfHouseholds, int NumOfHouseholdsMovedInAfter2010) {
    this.householdId = householdId;
    this.address = address;
    this.totalIncomeOfHouseholds = totalIncomeOfHouseholds;
    this.AVGIncomeOfHouseholds = AVGIncomeOfHouseholds;
    this.NumOfFamilyWithChildUnderSix = NumOfFamilyWithChildUnderSix;
    this.NumOfFamilyWithOneOrMoreUnder18 = NumOfFamilyWithOneOrMoreUnder18;
    this.totalPersonsInHouseholds = totalPersonsInHouseholds;
    this.NumOfHouseholdsWithoutTeleService = NumOfHouseholdsWithoutTeleService;
    this.NumOfHouseholdsLackFacilities = NumOfHouseholdsLackFacilities;
    this.NumOfHouseholdsBuiltAfter2010 = NumOfHouseholdsBuiltAfter2010;
    this.medianHouseValue = medianHouseValue;
    this.totalHouseValue = totalHouseValue;
    this.NumOfAssistanceFamilies = NumOfAssistanceFamilies;
    this.totalNumOfHouseholds = totalNumOfHouseholds;
    this.NumOfHouseholdsMovedInAfter2010 = NumOfHouseholdsMovedInAfter2010;
  }


  public Household(Address address, long totalIncomeOfHouseholds, int AVGIncomeOfHouseholds,
      int NumOfFamilyWithChildUnderSix, int NumOfFamilyWithOneOrMoreUnder18,
      int totalPersonsInHouseholds, int NumOfHouseholdsWithoutTeleService,
      int NumOfHouseholdsLackFacilities, int NumOfHouseholdsBuiltAfter2010, int medianHouseValue,
      long totalHouseValue, int NumOfAssistanceFamilies, int totalNumOfHouseholds,
      int NumOfHouseholdsMovedInAfter2010) {
    this.address = address;
    this.totalIncomeOfHouseholds = totalIncomeOfHouseholds;
    this.AVGIncomeOfHouseholds = AVGIncomeOfHouseholds;
    this.NumOfFamilyWithChildUnderSix = NumOfFamilyWithChildUnderSix;
    this.NumOfFamilyWithOneOrMoreUnder18 = NumOfFamilyWithOneOrMoreUnder18;
    this.totalPersonsInHouseholds = totalPersonsInHouseholds;
    this.NumOfHouseholdsWithoutTeleService = NumOfHouseholdsWithoutTeleService;
    this.NumOfHouseholdsLackFacilities = NumOfHouseholdsLackFacilities;
    this.NumOfHouseholdsBuiltAfter2010 = NumOfHouseholdsBuiltAfter2010;
    this.medianHouseValue = medianHouseValue;
    this.totalHouseValue = totalHouseValue;
    this.NumOfAssistanceFamilies = NumOfAssistanceFamilies;
    this.totalNumOfHouseholds = totalNumOfHouseholds;
    this.NumOfHouseholdsMovedInAfter2010 = NumOfHouseholdsMovedInAfter2010;
  }

  public int getHouseholdId() {
    return this.householdId;
  }

  public void setHouseholdId(int householdId) {
    this.householdId = householdId;
  }

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public long getTotalIncomeOfHouseholds() {
    return this.totalIncomeOfHouseholds;
  }

  public void setTotalIncomeOfHouseholds(long totalIncomeOfHouseholds) {
    this.totalIncomeOfHouseholds = totalIncomeOfHouseholds;
  }

  public int getAVGIncomeOfHouseholds() {
    return this.AVGIncomeOfHouseholds;
  }

  public void setAVGIncomeOfHouseholds(int aVGIncomeOfHouseholds) {
    this.AVGIncomeOfHouseholds = aVGIncomeOfHouseholds;
  }

  public int getNumOfFamilyWithChildUnderSix() {
    return this.NumOfFamilyWithChildUnderSix;
  }

  public void setNumOfFamilyWithChildUnderSix(int numOfFamilyWithChildUnderSix) {
    this.NumOfFamilyWithChildUnderSix = numOfFamilyWithChildUnderSix;
  }

  public int getNumOfFamilyWithOneOrMoreUnder18() {
    return this.NumOfFamilyWithOneOrMoreUnder18;
  }

  public void setNumOfFamilyWithOneOrMoreUnder18(int numOfFamilyWithOneOrMoreUnder18) {
    this.NumOfFamilyWithOneOrMoreUnder18 = numOfFamilyWithOneOrMoreUnder18;
  }

  public int getTotalPersonsInHouseholds() {
    return this.totalPersonsInHouseholds;
  }

  public void setTotalPersonsInHouseholds(int totalPersonsInHouseholds) {
    this.totalPersonsInHouseholds = totalPersonsInHouseholds;
  }

  public int getNumOfHouseholdsWithoutTeleService() {
    return this.NumOfHouseholdsWithoutTeleService;
  }

  public void setNumOfHouseholdsWithoutTeleService(int numOfHouseholdsWithoutTeleService) {
    this.NumOfHouseholdsWithoutTeleService = numOfHouseholdsWithoutTeleService;
  }

  public int getNumOfHouseholdsLackFacilities() {
    return this.NumOfHouseholdsLackFacilities;
  }

  public void setNumOfHouseholdsLackFacilities(int numOfHouseholdsLackFacilities) {
    this.NumOfHouseholdsLackFacilities = numOfHouseholdsLackFacilities;
  }

  public int getNumOfHouseholdsBuiltAfter2010() {
    return this.NumOfHouseholdsBuiltAfter2010;
  }

  public void setNumOfHouseholdsBuiltAfter2010(int numOfHouseholdsBuiltAfter2010) {
    this.NumOfHouseholdsBuiltAfter2010 = numOfHouseholdsBuiltAfter2010;
  }

  public int getMedianHouseValue() {
    return this.medianHouseValue;
  }

  public void setMedianHouseValue(int medianHouseValue) {
    this.medianHouseValue = medianHouseValue;
  }

  public long getTotalHouseValue() {
    return this.totalHouseValue;
  }

  public void setTotalHouseValue(long totalHouseValue) {
    this.totalHouseValue = totalHouseValue;
  }

  public int getNumOfAssistanceFamilies() {
    return this.NumOfAssistanceFamilies;
  }

  public void setNumOfAssistanceFamilies(int numOfAssistanceFamilies) {
    this.NumOfAssistanceFamilies = numOfAssistanceFamilies;
  }

  public int getTotalNumOfHouseholds() {
    return this.totalNumOfHouseholds;
  }

  public void setTotalNumOfHouseholds(int totalNumOfHouseholds) {
    this.totalNumOfHouseholds = totalNumOfHouseholds;
  }

  public int getNumOfHouseholdsMovedInAfter2010() {
    return this.NumOfHouseholdsMovedInAfter2010;
  }

  public void setNumOfHouseholdsMovedInAfter2010(int numOfHouseholdsMovedInAfter2010) {
    this.NumOfHouseholdsMovedInAfter2010 = numOfHouseholdsMovedInAfter2010;
  }

}
