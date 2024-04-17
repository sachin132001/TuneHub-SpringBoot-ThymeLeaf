package com.example.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entities.Song;
import com.example.tunehub.repositories.SongRepository;
@Service
public class SongServiceImplementation implements SongService{
	@Autowired
	SongRepository srepo;

	@Override
	public String addSongs(Song song) {
		// TODO Auto-generated method stub
		srepo.save(song);
		return "Song is added";
	}

	@Override
	public boolean songExists(String name) {
		// TODO Auto-generated method stub
		if (srepo.findByName(name)==null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
		List<Song> songslist=srepo.findAll();
		return songslist;
		
	}

	@Override
	public void updateSong(Song song) {
		// TODO Auto-generated method stub
		srepo.save(song);
	}

}
