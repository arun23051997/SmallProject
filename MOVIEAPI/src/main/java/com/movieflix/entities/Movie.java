package com.movieflix.entities;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Please provide movie's title!")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's director!")
    private String director;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's studio!")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's poster!")
    private String poster;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(Integer movieId, @NotBlank(message = "Please provide movie's title!") String title,
			@NotBlank(message = "Please provide movie's director!") String director,
			@NotBlank(message = "Please provide movie's studio!") String studio, Set<String> movieCast,
			Integer releaseYear, @NotBlank(message = "Please provide movie's poster!") String poster) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.director = director;
		this.studio = studio;
		this.movieCast = movieCast;
		this.releaseYear = releaseYear;
		this.poster = poster;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public Set<String> getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(Set<String> movieCast) {
		this.movieCast = movieCast;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
    
    
}

