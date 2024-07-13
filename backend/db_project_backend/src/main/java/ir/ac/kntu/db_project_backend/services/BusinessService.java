package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.models.Business;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.repositories.AddressRepository;
import ir.ac.kntu.db_project_backend.repositories.BusinessRepository;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Cacheable(value = "businessesCache", key = "#root.methodName")
    public List<Business> findAll() {
        return businessRepository.findAll();
    }

    @Cacheable(value = "businessesCache", key = "#id")
    public List<Business> findByOwnerId(int id) {
        return businessRepository.findByOwnerId(id);
    }

    @Cacheable(value = "businessesCache", key = "#city.getCityId()")
    public List<Business> findByCity(City city) {
        return businessRepository.findByCity(city);
    }

    @Cacheable(value = "businessesCache", key = "#name")
    public List<Business> findByName(String name) {
        return businessRepository.findByKeyword(name);
    }

    @CachePut(value = "businessesCache", key = "#business.id")
    public void updateBusiness(Business business) {
        businessRepository.updateBusiness(business, addressRepository);
    }

    @CacheEvict(value = "businessesCache", key = "#business.id")
    public void addBusiness(Business business) {
        businessRepository.addBusiness(business, addressRepository);
    }

    @CacheEvict(value = "businessesCache", key = "#BID")
    public void deleteBusiness(int BID) {
        businessRepository.delete(BID);
    }
}
