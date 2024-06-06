package br.com.senai.repositories;

import br.com.senai.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    List<Revenue> findByNameContainingIgnoreCase(String palavra);
}
