package com.galatea.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galatea.dsmovie.dto.MovieDTO;
import com.galatea.dsmovie.dto.ScoreDTO;
import com.galatea.dsmovie.entities.Movie;
import com.galatea.dsmovie.entities.Score;
import com.galatea.dsmovie.entities.User;
import com.galatea.dsmovie.repositories.MovieRepository;
import com.galatea.dsmovie.repositories.ScoreRepository;
import com.galatea.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		User user = userRepository.findByEmail(dto.getEmail());
		
		if(user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			
			user = userRepository.saveAndFlush(user);
		}
		
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for(Score s : movie.getScores()) {
			sum += s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
	
}
