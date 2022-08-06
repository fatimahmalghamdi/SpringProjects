package com.fatimah.lookifyApp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.lookifyApp.models.Songs;
import com.fatimah.lookifyApp.services.SongService;

@Controller
public class SongController {
	private final SongService songService;
	public SongController(SongService songServ) {
		this.songService = songServ;
	}

	@RequestMapping("index")
	public String index() {
		return "index.jsp";
	}
	
	//display dashboard
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Songs> allSongs = songService.getAllSongs();
		model.addAttribute("allSongs", allSongs);
		return "dashboard.jsp";
	}
	
	//add page
	@RequestMapping(value="/addSong", method=RequestMethod.GET)
	public String addSongPage(Model model) {
		if (!model.containsAttribute("mysong")) {
			Songs newsong = new Songs();
			model.addAttribute("mysong", newsong);
		}
		
		return "addSong.jsp";
	}
	
	//save action
	@RequestMapping(value="/addSong", method=RequestMethod.POST)
	public String storeSong(@Valid @ModelAttribute("mysong") Songs thenewsong,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		//if there's validation errors --> add them to Flash attr then redirect to create page
		if (bindingResult.hasErrors()) {
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("mysong", thenewsong);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mysong", bindingResult);
			return "redirect:/addSong";
		}
		//no errors --> save and redirect to main page:
		songService.creatsong(thenewsong);
		
		redirectAttributes.addFlashAttribute("success", "The Song has been added successfully");
		return "redirect:/dashboard";
	}
	
	
	//get details about item:
	@RequestMapping("/display/{id}")
	public String displayDetails(@PathVariable("id") Long id, Model model) {
		Songs theSong = songService.getCurrentSong(id);
		model.addAttribute("theSong",theSong);
		return "displaySong.jsp";
	}
	
	
	//delete an item:
	@RequestMapping("/delete/{id}")
	public String destroy(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		songService.deleteSong(id);
		redirectAttributes.addFlashAttribute("success", "this record has beed deleted");
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/topSongs")
	public String topSongs() {
		return "topTenSongs.jsp";
	}
	
	//search by artist:
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam(value="key") String key, Model model,
			RedirectAttributes redirectAttributes) {
		if (!key.isEmpty()) {
			List<Songs> allSongs = songService.getSongs(key);
			if(allSongs != null) {
				model.addAttribute("allSongs", allSongs);
				model.addAttribute("artist", key);
				System.out.println("found the artist********************");
				return "searchByArtist.jsp";
			}
			else {
				redirectAttributes.addFlashAttribute("error", "There is no songs belong to "+ key);
				return "redirect:/dashboard";
			}
		}
		else {
			redirectAttributes.addFlashAttribute("error", "The search fiels shouldn't be Empty");
			return "redirect:/dashboard";
			
		}
	}
	
	@RequestMapping("/toptensongs")
	public String findTopSongs(Model model, RedirectAttributes redirectAttributes) {
		List<Songs> thetopSongs = songService.toptensongs();
		System.out.println("the list size is: **********************"+thetopSongs.size());
		if (!thetopSongs.isEmpty()) {
			model.addAttribute("thetopsongs",thetopSongs);
			
			return "topTenSongs.jsp";
		}
		redirectAttributes.addFlashAttribute("error","There are no top Ten songs");
		return "redirect:/dashboard";
	}
	
	
	
}
