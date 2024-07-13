package ir.ac.kntu.db_project_backend.services;

import ir.ac.kntu.db_project_backend.models.Business;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.repositories.BusinessRepository;
import ir.ac.kntu.db_project_backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Cacheable(value = "businesses", key = "#id")
    public List<Business> findByOwnerId(int id) {
        return businessRepository.findByOwnerId(id);
    }

    @Cacheable(value = "businessesByCity", key = "#city")
    public List<Business> findByCity(City city) {
        return businessRepository.findByCity(city);
    }

    @Cacheable(value = "businessesByKeyword", key = "#keyword")
    public List<Business> findByKeyword(String keyword) {
        return businessRepository.findByKeyword(keyword);
    }

    @Cacheable(value = "businesses")
    public List<Business> findAll() {
        return businessRepository.findAll();
    }

    @Caching(evict = {
        @CacheEvict(value = "businesses", allEntries = true),
        @CacheEvict(value = "businessesByCity", allEntries = true),
        @CacheEvict(value = "businessesByKeyword", allEntries = true)
    })
    public void addBusiness(Business business) {
        businessRepository.addBusiness(business, addressRepository);
    }

    @Caching(evict = {
        @CacheEvict(value = "businesses", key = "#business.id"),
        @CacheEvict(value = "businessesByCity", allEntries = true),
        @CacheEvict(value = "businessesByKeyword", allEntries = true)
    })
    public void updateBusiness(Business business) {
        businessRepository.updateBusiness(business, addressRepository);
    }

    @Caching(evict = {
        @CacheEvict(value = "businesses", key = "#id"),
        @CacheEvict(value = "businessesByCity", allEntries = true),
        @CacheEvict(value = "businessesByKeyword", allEntries = true)
    })
    public void delete(int id) {
        businessRepository.delete(id);
    }
}
