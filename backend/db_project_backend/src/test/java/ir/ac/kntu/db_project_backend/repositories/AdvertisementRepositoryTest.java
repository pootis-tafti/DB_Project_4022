package ir.ac.kntu.db_project_backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Advertisement ad1 = new Advertisement();
        ad1.setBussinessId(2);
        ad1.setAccountId(3);
        ad1.setDescription("First Test Description");
        ad1.setTitle("First Test Title");
        ad1.setPrice(200);
        ad1.setIsNew(false);

        Advertisement ad2 = new Advertisement();
        ad2.setBussinessId(3);
        ad2.setAccountId(1);
        ad2.setDescription("Second Test Description");
        ad2.setTitle("Second Test Title");
        ad2.setPrice(300);
        ad2.setIsNew(true);

        Advertisement ad3 = new Advertisement();
        ad3.setBussinessId(3);
        ad3.setAccountId(3);
        ad3.setDescription("Third Test Description");
        ad3.setTitle("Third Test Title");
        ad3.setPrice(400);
        ad3.setIsNew(false);

        Advertisement ad4 = new Advertisement();
        ad4.setBussinessId(2);
        ad4.setAccountId(2);
        ad4.setDescription("Fourth Test Description");
        ad4.setTitle("Fourth Test Title");
        ad4.setPrice(500);
        ad4.setIsNew(true);

        advertisementRepository.addAdvertisement(ad, addStatusRepository);
        advertisementRepository.addAdvertisement(ad1, addStatusRepository);
        advertisementRepository.addAdvertisement(ad2, addStatusRepository);
        advertisementRepository.addAdvertisement(ad3, addStatusRepository);
        advertisementRepository.addAdvertisement(ad4, addStatusRepository);
    }

    @Test
    public void deleteTest(){
        advertisementRepository.delete(11,addStatusRepository);
    }

    @Test
    public void selectTest(){
        assertEquals(300, advertisementRepository.findById(13).getPrice());
    }

    @Test
    public void filterTest1(){
        assertEquals("Third Test Title", advertisementRepository.filter(450, 350, false, "title").get(0).getTitle());
    }

    @Test
    public void filterTest2(){
        assertEquals("Fourth Test Description", advertisementRepository.filter(1000, 350, true, "title").get(0).getDescription());
    }

    @Test
    public void filterTest3(){
        assertEquals("First Test Description", advertisementRepository.filter(1000, 0, false, "first").get(0).getDescription());
    }
}