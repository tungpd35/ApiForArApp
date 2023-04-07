package com.example.webapi.repositories;

import com.example.webapi.models.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    List<Clothes> findByName(String name);

    boolean existsByName(String name);

}