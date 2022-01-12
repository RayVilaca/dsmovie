package com.galatea.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galatea.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
