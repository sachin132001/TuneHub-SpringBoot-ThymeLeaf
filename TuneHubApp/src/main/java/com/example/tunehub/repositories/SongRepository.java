package com.example.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tunehub.entities.Song;
import com.example.tunehub.entities.Users;
import java.util.List;


public interface SongRepository extends JpaRepository<Song, Integer>{
	public Song findByName(String name);

}
