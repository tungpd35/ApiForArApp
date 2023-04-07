package com.example.webapi.repositories;

import com.example.webapi.models.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Long> {
    List<Style> findByStyleName(String name);
}
