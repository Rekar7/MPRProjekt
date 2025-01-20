package org.example.mprprojekt.repositories;

import org.example.mprprojekt.models.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
    Developer findByName(String name);
}
