package com.galatea.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galatea.dsmovie.entities.Score;
import com.galatea.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
