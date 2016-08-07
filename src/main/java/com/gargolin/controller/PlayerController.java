package com.gargolin.controller;

import java.util.List;

import com.gargolin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gargolin.service.PlayerService;

@Controller
@RequestMapping(value="/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addPlayerPage() {
		ModelAndView modelAndView = new ModelAndView("add-player-form");
		modelAndView.addObject("player", new Player());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingPlayer(@ModelAttribute Player player) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		System.out.println("211");
		playerService.addPlayer(player);
		
		String message = "Player was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOPlayers() {
		ModelAndView modelAndView = new ModelAndView("list-of-player");
		
		List<Player> players = playerService.getPlayers();
		modelAndView.addObject("player", players);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editPlayerPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-team-form");
		Player player = playerService.getPlayer(id);
		modelAndView.addObject("team",player);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editingPlayer(@ModelAttribute Player player, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		playerService.updatePlayer(player);
		
		String message = "Team was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deletePlayer(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		playerService.deletePlayer(id);
		String message = "Team was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
