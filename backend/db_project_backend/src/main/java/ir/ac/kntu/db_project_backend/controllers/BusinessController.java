package ir.ac.kntu.db_project_backend.controllers;

import ir.ac.kntu.db_project_backend.models.Business;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping
    public ResponseEntity<Void> addBusiness(@RequestBody Business business) {
        businessService.addBusiness(business);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBusiness(@PathVariable int id, @RequestBody Business business) {
        business.setId(id);
        businessService.updateBusiness(business);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable int id) {
        businessService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Business>> findAll() {
        List<Business> businesses = businessService.findAll();
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Business>> findByOwnerId(@PathVariable int ownerId) {
        List<Business> businesses = businessService.findByOwnerId(ownerId);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<Business>> findByCity(@RequestBody City city) {
        List<Business> businesses = businessService.findByCity(city);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<Business>> findByKeyword(@PathVariable String keyword) {
        List<Business> businesses = businessService.findByKeyword(keyword);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }
}
