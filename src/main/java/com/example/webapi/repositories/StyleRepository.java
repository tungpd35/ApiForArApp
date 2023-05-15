package com.example.webapi.repositories;

import com.example.webapi.models.Style;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Long> {
    List<Style> findByStyleName(String name);
}
