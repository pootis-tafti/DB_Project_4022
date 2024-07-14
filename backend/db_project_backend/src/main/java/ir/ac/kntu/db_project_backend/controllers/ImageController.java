package ir.ac.kntu.db_project_backend.controllers;

import ir.ac.kntu.db_project_backend.models.Image;
import ir.ac.kntu.db_project_backend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Image>> getImagesById(@PathVariable int id) {
        List<Image> images = imageService.findById(id);
        if (images.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(images);
    }
}

