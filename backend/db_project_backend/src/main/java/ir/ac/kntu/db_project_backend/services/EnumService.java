package ir.ac.kntu.db_project_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.models.Province;
import ir.ac.kntu.db_project_backend.models.CommentType;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.repositories.ProvinceRepository;
import ir.ac.kntu.db_project_backend.repositories.CommentTypeRepository;
import ir.ac.kntu.db_project_backend.repositories.CityRepository;

@Service
public class EnumService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CommentTypeRepository commentTypeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Cacheable(value = "provinces")
    public List<Province> findAllProvinces() {
        return provinceRepository.findAll();
    }

    @Cacheable(value = "provinces", key = "#id")
    public Province findProvinceById(int id) {
        return provinceRepository.findById(id);
    }

    @Cacheable(value = "commentTypes")
    public List<CommentType> findAllCommentTypes() {
        return commentTypeRepository.findAll();
    }

    @Cacheable(value = "cities")
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Cacheable(value = "cities", key = "#provinceId")
    public List<City> findCitiesByProvince(int provinceId) {
        return cityRepository.findByProvince(provinceId);
    }

    @Cacheable(value = "cities", key = "#name")
    public List<City> findCitiesByName(String name) {
        return cityRepository.findByName(name);
    }

    @Cacheable(value = "cities", key = "#id")
    public City findCityById(int id) {
        return cityRepository.findById(id);
    }
}

