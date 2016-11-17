package blog.tools;

import java.sql.SQLException;
import java.util.List;

import blog.dal.AddressDao;
import blog.dal.EducationDistributionDao;
import blog.dal.PopulationDao;
import blog.model.Address;
import blog.model.EducationDistribution;
import blog.model.Population;


public class Inserter {
  public static void main(String[] args) throws SQLException {
    // DAO instances.
    AddressDao addressDao = AddressDao.getInstance();
    PopulationDao populationDao = PopulationDao.getInstance();
    EducationDistributionDao educationDistributionDao = EducationDistributionDao.getInstance();

    // INSERT objects
    // insert address
    Address a1 = new Address(6001400100L, "California", "Alameda County", 400100, 2.657);
    Address a2 = new Address(6001400200L, "California", "Alameda County", 400200, 0.23);
    Address a3 = new Address(6007000102L, "California", "Butte County", 102, 1.026);
    Address a4 = new Address(53001950100L, "Washington", "Adams County", 950100, 623.721);
    Address a5 = new Address(48507950302L, "Texas", "Zavala County", 950302, 7.518);
    addressDao.create(a1);
    addressDao.create(a2);
    addressDao.create(a3);
    addressDao.create(a4);
    addressDao.create(a5);

    // insert population
    Population p1 = new Population(6001400100L, 3385);
    Population p2 = new Population(6001400200L, 1939);
    Population p3 = new Population(6007000102L, 1111);
    populationDao.create(p1);
    populationDao.create(p2);
    populationDao.create(p3);

    // insert education distribution
    EducationDistribution e1 = new EducationDistribution(6007000102L, 10, 5, 5);
    EducationDistribution e2 = new EducationDistribution(53001950100L, 20, 10, 10);
    EducationDistribution e3 = new EducationDistribution(48507950302L, 30, 15, 15);
    educationDistributionDao.create(e1);
    educationDistributionDao.create(e2);
    educationDistributionDao.create(e3);

    // GET objects
    // get address
    Address addressRead1 = addressDao.getAddressFromAddressId(6001400100L);
    System.out.format(
        "Read address with addressId=6001400100L: addressId:%d, state:%s, county:%s, tract:%d, landArea:%f\n",
        addressRead1.getAddressId(), addressRead1.getState(), addressRead1.getCounty(),
        addressRead1.getTract(), addressRead1.getLandArea());

    List<Address> listOfAddresses1 = addressDao.getAddressesFromState("California");
    for (Address a : listOfAddresses1) {
      System.out.format(
          "Read address with state=California: addressId:%d, state:%s, county:%s, tract:%d, landArea:%f\n",
          a.getAddressId(), a.getState(), a.getCounty(), a.getTract(), a.getLandArea());
    }

    List<Address> listOfAddresses2 =
        addressDao.getAddressesFromStateAndCounty("California", "Alameda County");
    for (Address a : listOfAddresses2) {
      System.out.format(
          "Read address with state=California and county=Alameda County: addressId:%d, state:%s, county:%s, tract:%d, landArea:%f\n",
          a.getAddressId(), a.getState(), a.getCounty(), a.getTract(), a.getLandArea());
    }

    // get population
    Population populationRead1 = populationDao.getPopulationFromAddressId(6007000102L);
    System.out.format(
        "Read population with addressId 6007000102: populationId:%d, addressId:%d, total:%d\n",
        populationRead1.getPopulationId(), populationRead1.getAddressId(),
        populationRead1.getTotal());
    Population populationRead2 = populationDao.getPopulationFromPopulationId(2);
    System.out.format(
        "Read population with populationId 2: populationId:%d, addressId:%d, total:%d\n",
        populationRead2.getPopulationId(), populationRead2.getAddressId(),
        populationRead2.getTotal());

    // get education distribution
    EducationDistribution educationDistributionRead1 =
        educationDistributionDao.getEducationDistributionFromPopulationId(4);
    System.out.format(
        "Read population with populationId 4: populationId:%d, addressId:%d, total:%d, notHighSchool:%d, college:%d\n",
        educationDistributionRead1.getPopulationId(), educationDistributionRead1.getAddressId(),
        educationDistributionRead1.getTotal(), educationDistributionRead1.getNotHighSchool(),
        educationDistributionRead1.getCollege());

    // UPDATE objects
    // update address
    Address addressUpdate1 = addressDao.updateLandArea(a1, 3.675);
    System.out.format("After update: addressId:%d, state:%s, county:%s, tract:%d, landArea:%f\n",
        addressUpdate1.getAddressId(), addressUpdate1.getState(), addressUpdate1.getCounty(),
        addressUpdate1.getTract(), addressUpdate1.getLandArea());

    // update population
    Population populationUpdate1 = populationDao.updateTotal(p1, 22222);
    System.out.format("After update: populationId:%d, addressId:%d, total:%d\n",
        populationUpdate1.getPopulationId(), populationUpdate1.getAddressId(),
        populationUpdate1.getTotal());

    // update education distribution
    educationDistributionDao.updateNotHighSchool(e1, 222);
    educationDistributionDao.updateCollege(e1, 222);

    // DELETE objects
    addressDao.delete(a5);
    populationDao.delete(p3);
    educationDistributionDao.delete(e3);
  }
}
