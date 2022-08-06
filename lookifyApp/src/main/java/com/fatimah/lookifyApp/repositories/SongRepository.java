package com.fatimah.lookifyApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatimah.lookifyApp.models.Songs;

@Repository
public interface SongRepository extends CrudRepository<Songs, Long>{
	List<Songs> findAll();
	List<Songs> findByArtist(String artist);
	List<Songs> findByArtistContaining(String artist);
	List<Songs> findByArtistNotContaining(String artist);
	List<Songs> findTop10ByOrderByRatingDesc();
	

	
	
}
