package ir.ac.kntu.db_project_backend.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ir.ac.kntu.db_project_backend.models.Advertisement;

@SpringBootTest
@ActiveProfiles("test") // Use a separate profile for test configurations
public class AdvertisementRepositoryTest {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AddStatusRepository addStatusRepository;

    @Test
    public void testCreateAdvertisement() {
        Advertisement ad = new Advertisement();
        ad.setBussinessId(1);
        ad.setAccountId(2);
        ad.setDescription("Test Description");
        ad.setTitle("Test Title");
        ad.setPrice(100);
        ad.setIsNew(true);

        advertisementRepository.addAdvertisement(ad, addStatusRepository); 
    }

    @Test
    public void deleteTest(){
        advertisementRepository.delete(10,addStatusRepository);
    }
}