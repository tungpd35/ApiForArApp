package com.example.webapi.database;

import com.example.webapi.models.Clothes;
import com.example.webapi.models.Style;
import com.example.webapi.repositories.ClothesRepository;
import com.example.webapi.repositories.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(StyleRepository styleRepository, ClothesRepository clothesRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

            }
        };
    }
}
