package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tunehub.entities.Playlist;
import com.example.tunehub.entities.Song;
import com.example.tunehub.entities.Users;
import com.example.tunehub.services.PlaylistService;
import com.example.tunehub.services.SongService;
import com.example.tunehub.services.UsersService;

import jakarta.persistence.GeneratedValue;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService userv;
	@Autowired
	SongService songserv;
	@Autowired
	PlaylistService pserv;
	
	
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute  Users user) {
		boolean userstatus=userv.emailExists(user.getEmail());
		if(userstatus==false) {
		   userv.addUser(user);
		   return "registersuccess";
		   
		}
		else {
			return "registerfail";
		}
	
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email , @RequestParam String password,HttpSession session ) {
		
		if((userv.validateUser(email, password)==true)) {
			session.setAttribute("email",email);
			
			if(userv.getRole(email).equals("admin")) {
				return "adminhome";
				
			}
			else {
				return "customerhome";
			}
				
		   
		   
		}
		
		else {
			return"loginfail";
		}
	    
	
	}
	
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session, Model model) {
		String email=(String) session.getAttribute("email");
		Users user=userv.getUser(email);
		boolean userStatus=user.isPremium();
		if(userStatus == true) {
			List<Song> songslist=songserv.fetchAllSongs();
			model.addAttribute("songslist",songslist);
			
			return "viewCustSongs";
		}
		else {
			return "makepayment";
		}
	}
	@GetMapping("/explorePlaylists")
	public String explorePlaylists(HttpSession session, Model model) {
		String email=(String) session.getAttribute("email");
		Users user=userv.getUser(email);
		boolean userStatus=user.isPremium();
		if(userStatus == true) {
			List<Playlist> plist= pserv.fetchPlaylists();
			model.addAttribute("plist", plist);
			return "viewplaylists";
		}
		else {
			return "makepayment";
		}
	}

}
