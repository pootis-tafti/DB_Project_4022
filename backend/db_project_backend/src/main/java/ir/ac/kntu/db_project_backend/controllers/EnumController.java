package ir.ac.kntu.db_project_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.ac.kntu.db_project_backend.models.Province;
import ir.ac.kntu.db_project_backend.models.CommentType;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.services.EnumService;

@RestController
@RequestMapping("/api/public/enums")
public class EnumController {

    @Autowired
    private EnumService enumService;

    @GetMapping("/provinces")
    public List<Province> getAllProvinces() {
        return enumService.findAllProvinces();
    }

    @GetMapping("/provinces/{id}")
    public Province getProvinceById(@PathVariable int id) {
        return enumService.findProvinceById(id);
    }

    @GetMapping("/comment-types")
    public List<CommentType> getAllCommentTypes() {
        return enumService.findAllCommentTypes();
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return enumService.findAllCities();
    }

    @GetMapping("/cities/province/{provinceId}")
    public List<City> getCitiesByProvince(@PathVariable int provinceId) {
        return enumService.findCitiesByProvince(provinceId);
    }

    @GetMapping("/cities/name/{name}")
    public List<City> getCitiesByName(@PathVariable String name) {
        return enumService.findCitiesByName(name);
    }

    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable int id) {
        return enumService.findCityById(id);
    }
}
