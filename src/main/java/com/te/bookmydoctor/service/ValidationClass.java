/*
 * package com.te.movies.service;
 * 
 * import org.springframework.stereotype.Component;
 * 
 * import com.te.movies.dto.MovieDto; import
 * com.te.movies.exception.MovieValidationException;
 * 
 * @Component public class ValidationClass { public void
 * validateMovieDto(MovieDto movieDto) { if (movieDto == null) { throw new
 * IllegalArgumentException("MovieDto cannot be null"); }
 * 
 * String title = movieDto.getTitle(); if (title == null ||
 * title.trim().isEmpty()) { throw new
 * MovieValidationException("Title is required"); }
 * 
 * String industry = movieDto.getIndustry(); if (industry == null ||
 * industry.trim().isEmpty()) { throw new
 * MovieValidationException("Industry is Required"); }
 * 
 * if (movieDto.getReleaseYear() == null || movieDto.getReleaseYear() < 1900 ||
 * movieDto.getReleaseYear() > 2100) { throw new
 * IllegalArgumentException("Invalid release year"); }
 * 
 * if (movieDto.getImdbRating() != null && (movieDto.getImdbRating() <= 0.0 ||
 * movieDto.getImdbRating() > 10.0)) { throw new
 * IllegalArgumentException("Invalid IMDb rating"); } } }
 */