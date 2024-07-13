package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.models.Advertisment;
import ir.ac.kntu.db_project_backend.repositories.AddStatusRepository;
import ir.ac.kntu.db_project_backend.repositories.AdvertisementRepository;

import java.util.List;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AddStatusRepository addStatusRepository; // Assuming this repository exists

    @CacheEvict(value = "advertisementsCache", key = "#advertisment.id")
    public void updateAdvertisement(Advertisment advertisment) {
        advertisementRepository.updateAdvertisement(advertisment,addStatusRepository);
    }

    @CacheEvict(value = "advertisementsCache", key = "#result.id")
    public void addAdvertisement(Advertisment advertisment) {
        advertisementRepository.addAdvertisement(advertisment,addStatusRepository);
    }

    @Cacheable(value = "advertisementsCache", key = "#maxPrice + '_' + #minPrice + '_' + #isNew + '_' + #keyword")
    public List<Advertisment> filter(int maxPrice, int minPrice, boolean isNew, String keyword) {
        return advertisementRepository.filter(maxPrice, minPrice, isNew, keyword);
    }

    @Cacheable(value = "advertisementsCache", key = "#id")
    public Advertisment findById(int id) {
        return advertisementRepository.findById(id);
    }

    @Cacheable(value = "advertisementsCache", key = "#id")
    public Advertisment findByBusiness(int id) {
        return advertisementRepository.findByBusiness(id);
    }

    @Cacheable(value = "advertisementsCache", key = "#id")
    public Advertisment findByAccount(int id) {
        return advertisementRepository.findByAccount(id);
    }

    @CacheEvict(value = "advertisementsCache", key = "#ADDID")
    public void delete(int ADDID) {
        advertisementRepository.delete(ADDID);
    }
}

