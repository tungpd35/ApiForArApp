//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.webapi.controllers;

import com.example.webapi.models.Clothes;
import com.example.webapi.models.ResponseObject;
import com.example.webapi.models.Style;
import com.example.webapi.repositories.StyleRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = {"api/style"}
)
@CrossOrigin
public class StyleController {
    @Autowired
    private StyleRepository repository;

    public StyleController() {
    }

    @GetMapping({"/getAll"})
    public List<Style> getAllStyles() {
        return this.repository.findAll();
    }

    @GetMapping({"/{id}"})
    Optional<Style> findById(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @GetMapping({"/{id}/getClothes"})
    Set<Clothes> getClothes(@PathVariable Long id) {
        Style style = (Style)this.repository.findById(id).orElse(null);
        return style == null ? null : style.getClothes();
    }

    @PostMapping({"/insert"})
    ResponseEntity<ResponseObject> insertStyle(@RequestBody Style newStyle) {
        List<Style> foundStyles = this.repository.findByStyleName(newStyle.getStyleName().trim());
        return foundStyles.size() > 0 ? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Style da ton tai", "")) : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Insert Style Successfully", this.repository.save(newStyle)));
    }

    @PutMapping({"/update/{id}"})
    ResponseEntity<ResponseObject> updateStyle(@RequestBody Style newstyle, @PathVariable Long id) {
        Style updateStyle = (Style)this.repository.findById(id).map((style) -> {
            style.setStyleName(newstyle.getStyleName());
            style.setStyleDescription(newstyle.getStyleDescription());
            style.setImage(newstyle.getImage());
            return (Style)this.repository.save(style);
        }).orElseGet(() -> {
            newstyle.setStyleId(id);
            return (Style)this.repository.save(newstyle);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Update Style Successfully", updateStyle));
    }

    @DeleteMapping({"/delete/{id}"})
    ResponseEntity<ResponseObject> deleteStyle(@PathVariable Long id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Delete Style Successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot find Style", ""));
        }
    }
}
