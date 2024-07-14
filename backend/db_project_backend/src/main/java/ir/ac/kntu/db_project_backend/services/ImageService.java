package ir.ac.kntu.db_project_backend.services;

import ir.ac.kntu.db_project_backend.models.Image;
import ir.ac.kntu.db_project_backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    
    public List<Image> findById(int id) {
        return imageRepository.findById(id);
    }
}

