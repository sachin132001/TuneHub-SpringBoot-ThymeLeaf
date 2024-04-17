package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tunehub.entities.Playlist;
import com.example.tunehub.entities.Song;
import com.example.tunehub.services.PlaylistService;
import com.example.tunehub.services.SongService;

@Controller
public class PlaylistController {
	@Autowired
	SongService sserv;
	@Autowired
	PlaylistService pserv;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		//Fetching the songs using song service
		List<Song> songslist=sserv.fetchAllSongs();
		//adding the songs in the model
		model.addAttribute("songslist",songslist);
		//sending createplaylist
		return "createplaylist";
	
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//adding playlist
		System.out.println(playlist);
		pserv.addPlaylist(playlist);
		
		//update song table
		List<Song> songsList=playlist.getSongs();
		for(Song song:songsList) {
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	
	@GetMapping("/viewplaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> plist= pserv.fetchPlaylists();
		model.addAttribute("plist", plist);
		return "viewplaylists";
	}
	

}
