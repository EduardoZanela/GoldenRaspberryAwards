package com.eduardozanela.repository;

import com.eduardozanela.entity.AwardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardsRepository extends JpaRepository<AwardsEntity, Integer> {

    List<AwardsEntity> findByWinner(String winner);
}
