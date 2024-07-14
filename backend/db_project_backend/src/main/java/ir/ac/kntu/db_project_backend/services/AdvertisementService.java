package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.models.Advertisement;
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
    public void updateAdvertisement(Advertisement advertisment) {
        advertisementRepository.updateAdvertisement(advertisment,addStatusRepository);
    }

    @CacheEvict(value = "advertisementsCache", key = "#result.id")
    public void addAdvertisement(Advertisement advertisment) {
        advertisementRepository.addAdvertisement(advertisment,addStatusRepository);
    }

    @Cacheable(value = "advertisementsCache", key = "#maxPrice + '_' + #minPrice + '_' + #isNew + '_' + #keyword")
    public List<Advertisement> filter(int maxPrice, int minPrice, boolean isNew, String keyword) {
        return advertisementRepository.filter(maxPrice, minPrice, isNew, keyword);
    }

    @Cacheable(value = "advertisementsCache", key = "#id")
    public Advertisement findById(int id) {
        return advertisementRepository.findById(id);
    }

    @Cacheable(value = "advertisementsCache", key = "#id")
    public Advertisement findByBusiness(int id) {
        return advertisementRepository.findByBusiness(id);
    }

    @Cacheable(value = "advertisementsCache", key = "#id")
    public Advertisement findByAccount(int id) {
        return advertisementRepository.findByAccount(id);
    }

    @CacheEvict(value = "advertisementsCache", key = "#ADDID")
    public void delete(int ADDID) {
        advertisementRepository.delete(ADDID, addStatusRepository);
    }
}

