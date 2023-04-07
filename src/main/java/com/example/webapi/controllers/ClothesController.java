package com.example.webapi.controllers;

import com.example.webapi.models.Clothes;
import com.example.webapi.models.ClothesImage;
import com.example.webapi.models.ResponseObject;
import com.example.webapi.models.Style;
import com.example.webapi.repositories.ClothesRepository;
import com.example.webapi.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/clothes")
public class ClothesController {
    @Autowired
    private ClothesRepository repository;
    @Autowired
    private StyleRepository styleRepository;

    @GetMapping("/getAll")
    public List<Clothes> getAllClothes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Clothes> findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/{id}/getStyle")
    public Style getStyle(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            Clothes clothes = repository.findById(id).orElse(null);
            return clothes.getStyle();
        } else {
            return null;
        }
    }

    @GetMapping("/{id}/getModel3d")
    public String getImage(@PathVariable Long id) {
        Clothes clothes = repository.findById(id).orElse(null);
        if (clothes == null) {
            return "Không tim thấy trang phục";
        }
        return clothes.getModel3d();
    }

    @PostMapping("/{id}/setModel3d")
    ResponseEntity<ResponseObject> setImage3d(@RequestParam("model")String model, @PathVariable Long id) {
        Clothes clothes = repository.findById(id).orElse(null);
        if (clothes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find Clothes", "")
            );
        } else {
            clothes.setModel3d(model);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Set Clothes Successfully", repository.save(clothes))
            );
        }
    }

    @PostMapping("/insert/{styleId}")
    ResponseEntity<ResponseObject> insertClothes(@RequestBody Clothes newClothes, @PathVariable Long styleId) {
        List<Clothes> foundClothes = repository.findByName(newClothes.getName().trim());
        if (foundClothes.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Trang phuc da ton tai", "")
            );
        } else {
            newClothes.setStyle(styleRepository.findById(styleId).orElse(null));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert Clothes Successfully", repository.save(newClothes))
            );
        }
    }

    @GetMapping("/{id}/getAllImage")
    List<ClothesImage> images(@PathVariable Long id) {
        Clothes clothes = repository.findById(id).orElse(null);
        if (clothes == null) {
            return null;
        }else {
            return clothes.getOtherImage();
        }
    }
    @PostMapping("/{id}/insertImage")
    ResponseEntity<ResponseObject> insertImage(@PathVariable Long id, @RequestBody ClothesImage newImage) {
        Clothes clothes = repository.findById(id).orElse(null);
        if (clothes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find Clothes", "")
            );
        } else {
            newImage.setClothes(clothes);
            List<ClothesImage> listImg = new ArrayList<>();
            listImg.add(newImage);
            clothes.setOtherImage(listImg);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert Image Successfully", repository.save(clothes))
            );
        }
    }


    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateStyle(@RequestBody Clothes newclothes, @RequestParam Long clothesId, @RequestParam Long styleId) {
        if (!repository.existsById(clothesId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find Clothes", "")
            );
        }
        repository.findById(clothesId)
                .map(clothes -> {
                    clothes.setName(newclothes.getName());
                    clothes.setStyle(newclothes.getStyle());
                    clothes.setDescription(newclothes.getDescription());
                    clothes.setStyle(styleRepository.findById(styleId).orElse(null));
                    clothes.setModel3d(newclothes.getModel3d());
                    return repository.save(clothes);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Style Successfully", "")
        );
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteClothes(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Clothes Successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find Clothes", "")
            );
        }
    }
}
