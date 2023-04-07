package com.example.webapi.controllers;

import com.example.webapi.models.Clothes;
import com.example.webapi.models.ResponseObject;
import com.example.webapi.models.Style;
import com.example.webapi.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/style")
public class StyleController {
    @Autowired
    private StyleRepository repository;

    @GetMapping("/getAll")
    public List<Style> getAllStyles() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Style> findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/{id}/getClothes")
    Set<Clothes> getClothes(@PathVariable Long id) {
        Style style = repository.findById(id).orElse(null);
        if(style == null) {
            return null;
        }else {
            return style.getClothes();
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertStyle(@RequestBody Style newStyle) {
        List<Style> foundStyles = repository.findByStyleName(newStyle.getStyleName().trim());
        if (foundStyles.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Style da ton tai", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Style Successfully", repository.save(newStyle))
        );
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateStyle(@RequestBody Style newstyle, @PathVariable Long id) {
        Style updateStyle = repository.findById(id)
                .map(style -> {
                    style.setStyleName(newstyle.getStyleName());
                    style.setStyleDescription(newstyle.getStyleDescription());
                    return repository.save(style);
                }).orElseGet(() -> {
                    newstyle.setStyleId(id);
                    return repository.save(newstyle);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Style Successfully", "")
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteStyle(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Style Successfully", "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find Style", "")
            );
        }
    }
}
