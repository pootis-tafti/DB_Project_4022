package ir.ac.kntu.db_project_backend.controllers;

import ir.ac.kntu.db_project_backend.models.Advertisment;
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
    public ResponseEntity<Void> addAdvertisement(@RequestBody Advertisment advertisment) {
        advertisementService.addAdvertisement(advertisment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdvertisement(@PathVariable int id, @RequestBody Advertisment advertisment) {
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
    public ResponseEntity<List<Advertisment>> filterAdvertisements(@RequestParam int maxPrice, 
                                                                   @RequestParam int minPrice,
                                                                   @RequestParam boolean isNew, 
                                                                   @RequestParam String keyword) {
        List<Advertisment> advertisments = advertisementService.filter(maxPrice, minPrice, isNew, keyword);
        return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advertisment> findById(@PathVariable int id) {
        Advertisment advertisment = advertisementService.findById(id);
        return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<Advertisment> findByBusiness(@PathVariable int businessId) {
        Advertisment advertisment = advertisementService.findByBusiness(businessId);
        return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Advertisment> findByAccount(@PathVariable int accountId) {
        Advertisment advertisment = advertisementService.findByAccount(accountId);
        return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }
}

