package br.com.senai.repositories;

import br.com.senai.models.ChecklistActivities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckActivitiesRepository extends JpaRepository<ChecklistActivities,Long> {
}
