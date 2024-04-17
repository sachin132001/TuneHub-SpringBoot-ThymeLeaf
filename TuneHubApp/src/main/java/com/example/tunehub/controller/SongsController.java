package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tunehub.entities.Song;
import com.example.tunehub.services.SongService;

@Controller
public class SongsController {
	@Autowired
	SongService songserv;
	@PostMapping("/addSongs")
	public String addSongs(@ModelAttribute Song song) {
		
		boolean songstatus=songserv.songExists(song.getName());
		if(songstatus==false) {
			songserv.addSongs(song);
			return "songsuccess";
			
		}
		else {
			return "songfail";
		}
		
		
	}
	@GetMapping("/map-viewsongs")
	public String viewSongs(Model model) {
		List<Song> songslist=songserv.fetchAllSongs();
		model.addAttribute("songslist",songslist);
		
		return "displaysongs";
	}
	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model) {
		boolean primeCustomerStatus=true;
		if(primeCustomerStatus==true) {
			List<Song> songslist=songserv.fetchAllSongs();
			model.addAttribute("songslist",songslist);
			
			return "viewCustSongs";
			
		}
		else {
			return "makepayment";
		}
	}
	
	

}
