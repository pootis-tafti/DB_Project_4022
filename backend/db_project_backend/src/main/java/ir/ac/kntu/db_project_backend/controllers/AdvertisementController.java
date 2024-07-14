package ir.ac.kntu.db_project_backend.controllers;

import ir.ac.kntu.db_project_backend.models.Advertisement;
import ir.ac.kntu.db_project_backend.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping
    public ResponseEntity<Void> addAdvertisement(@RequestBody Advertisement advertisment) {
        advertisementService.addAdvertisement(advertisment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdvertisement(@PathVariable int id, @RequestBody Advertisement advertisment) {
        advertisment.setId(id);
        advertisementService.updateAdvertisement(advertisment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable int id) {
        advertisementService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Advertisement>> filterAdvertisements(@RequestParam int maxPrice, 
                                                                   @RequestParam int minPrice,
                                                                   @RequestParam boolean isNew, 
                                                                   @RequestParam String keyword) {
        List<Advertisement> advertisments = advertisementService.filter(maxPrice, minPrice, isNew, keyword);
        return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advertisement> findById(@PathVariable int id) {
        Advertisement advertisment = advertisementService.findById(id);
        return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<Advertisement> findByBusiness(@PathVariable int businessId) {
        Advertisement advertisment = advertisementService.findByBusiness(businessId);
        return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Advertisement> findByAccount(@PathVariable int accountId) {
        Advertisement advertisment = advertisementService.findByAccount(accountId);
        return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }
}

