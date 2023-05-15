//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.webapi.controllers;

import com.example.webapi.models.Clothes;
import com.example.webapi.models.ClothesImage;
import com.example.webapi.models.ResponseObject;
import com.example.webapi.models.Style;
import com.example.webapi.repositories.ClothesImageRepository;
import com.example.webapi.repositories.ClothesRepository;
import com.example.webapi.repositories.StyleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = {"/api/clothes"}
)
@CrossOrigin
public class ClothesController {
    @Autowired
    private ClothesRepository repository;
    @Autowired
    private StyleRepository styleRepository;
    @Autowired
    private ClothesImageRepository clothesImageRepository;

    public ClothesController() {
    }

    @GetMapping({"/getAll"})
    public List<Clothes> getAllClothes() {
        return this.repository.findAll();
    }

    @GetMapping({"/{id}"})
    Optional<Clothes> findById(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @GetMapping({"/{id}/getStyle"})
    public Style getStyle(@PathVariable Long id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            Clothes clothes = (Clothes)this.repository.findById(id).orElse(null);
            return clothes.getStyle();
        } else {
            return null;
        }
    }

    @GetMapping({"/{id}/getModel3d"})
    public String getImage(@PathVariable Long id) {
        Clothes clothes = (Clothes)this.repository.findById(id).orElse(null);
        return clothes == null ? "Không tim thấy trang phục" : clothes.getModel3d();
    }

    @PostMapping({"/{id}/setModel3d"})
    ResponseEntity<ResponseObject> setImage3d(@RequestParam("model") String model, @PathVariable Long id) {
        Clothes clothes = (Clothes)this.repository.findById(id).orElse(null);
        if (clothes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find Clothes", ""));
        } else {
            clothes.setModel3d(model);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Set Clothes Successfully", this.repository.save(clothes)));
        }
    }

    @PostMapping({"/insert/{styleId}"})
    ResponseEntity<ResponseObject> insertClothes(@RequestBody Clothes newClothes, @PathVariable Long styleId) {
        List<Clothes> foundClothes = this.repository.findByName(newClothes.getName().trim());
        if (foundClothes.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Trang phuc da ton tai", ""));
        } else {
            newClothes.setStyle((Style)this.styleRepository.findById(styleId).orElse(null));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Insert Clothes Successfully", this.repository.save(newClothes)));
        }
    }

    @GetMapping({"/{id}/getAllImage"})
    List<ClothesImage> images(@PathVariable Long id) {
        Clothes clothes = (Clothes)this.repository.findById(id).orElse(null);
        return clothes == null ? null : clothes.getImage();
    }

    @PostMapping({"/{id}/insertImage"})
    ResponseEntity<ResponseObject> insertImage(@PathVariable Long id, @RequestBody ClothesImage newImage) {
        Clothes clothes = (Clothes)this.repository.findById(id).orElse(null);
        if (clothes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find Clothes", ""));
        } else {
            newImage.setClothes(clothes);
            List<ClothesImage> listImg = new ArrayList();
            listImg.add(newImage);
            clothes.setImage(listImg);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Insert Image Successfully", this.repository.save(clothes)));
        }
    }

    @PutMapping({"/update"})
    ResponseEntity<ResponseObject> updateStyle(@RequestBody Clothes newclothes, @RequestParam Long clothesId, @RequestParam Long styleId) {
        if (!this.repository.existsById(clothesId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find Clothes", ""));
        } else {
            this.repository.findById(clothesId).map((clothes) -> {
                clothes.setName(newclothes.getName());
                clothes.setStyle(newclothes.getStyle());
                clothes.setDescription(newclothes.getDescription());
                clothes.setStyle((Style)this.styleRepository.findById(styleId).orElse(null));
                clothes.setModel3d(newclothes.getModel3d());
                List<ClothesImage> listImg = new ArrayList();
                if (newclothes.getImage().size() > 0) {
                    for(int i = 0; i < newclothes.getImage().size(); ++i) {
                        ((ClothesImage)newclothes.getImage().get(i)).setClothes(clothes);
                        listImg.add((ClothesImage)newclothes.getImage().get(i));
                        clothes.setImage(listImg);
                    }
                }

                return (Clothes)this.repository.save(clothes);
            });
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Update Style Successfully", ""));
        }
    }

    @DeleteMapping({"/delete/{id}"})
    ResponseEntity<ResponseObject> deleteClothes(@PathVariable Long id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Delete Clothes Successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find Clothes", ""));
        }
    }
}
