package com.example.webapi.repositories;

import com.example.webapi.models.Clothes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    List<Clothes> findByName(String name);

    boolean existsByName(String name);
}
