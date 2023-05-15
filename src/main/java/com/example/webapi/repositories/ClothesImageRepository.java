package com.example.webapi.repositories;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.example.webapi.exceptions.UserException;
import com.example.webapi.models.Clothes;
import com.example.webapi.models.ClothesImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public interface ClothesImageRepository extends JpaRepository<ClothesImage, Long> {
    List<Clothes> findAllByClothes(Clothes clothes);
}

