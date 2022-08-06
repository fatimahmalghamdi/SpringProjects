package com.fatimah.lookifyApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatimah.lookifyApp.models.Songs;
import com.fatimah.lookifyApp.repositories.SongRepository;

@Service
public class SongService {
	private final SongRepository songRepository;

	public SongService(SongRepository songRepo) {
		this.songRepository = songRepo;
	}

	// get all items method:
	public List<Songs> getAllSongs() {
		List<Songs> allSongs = songRepository.findAll();
		return allSongs;
	}
	
	// get item by Id method:
	public Songs getCurrentSong(Long id) {
		Optional<Songs> optionalSong = songRepository.findById(id);
		if (optionalSong.isPresent()) {
			return optionalSong.get();
		}
		return null;
	}
	
	//get item by string method:
	public List<Songs> getSongs(String key){
		List<Songs> Songs = songRepository.findByArtistContaining(key); 
		return Songs;
	}
		
	// delete item method:
	public void deleteSong(Long id) {
		Songs song = getCurrentSong(id);
		songRepository.delete(song);
	}
	
	

	// save method:
	public Songs creatsong(Songs thesong) {
		Songs new_song = songRepository.save(thesong);
		return new_song;
	}

	//get top 10 songs:
	public List<Songs> toptensongs(){
		return songRepository.findTop10ByOrderByRatingDesc();
	}

}
