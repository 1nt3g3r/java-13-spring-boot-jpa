package com.goit.java13.mvc.feature.chocholate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChocolateRepository extends JpaRepository<Chocolate, String> {
}
