package com.gargolin.controller;

import com.gargolin.model.Game;
import com.gargolin.model.Player;
import com.gargolin.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addPlayerPage() {
		ModelAndView modelAndView = new ModelAndView("add-player-form");
		modelAndView.addObject("player", new Player());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addingPlayer(@ModelAttribute Player player) {
		ModelAndView modelAndView = new ModelAndView("add-player-form");
		if (player.getFirstName().length() < 2 || player.getSecondName().length() < 2) {
			modelAndView.addObject("message", "Player was not added, because you didn't fill out the form");
			return modelAndView;
		}
		playerService.addPlayer(player);
		String message = "Player" + player.toString() + "was successfully added.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listOPlayers() {
		ModelAndView modelAndView = new ModelAndView("list-of-players");
		List<Player> players = playerService.getPlayers();
		modelAndView.addObject("players", players);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editPlayerPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-player-form");
		Player player = playerService.getPlayer(id);
		Set<Game> gameSet = player.getGame();
		for (Iterator<Game> iterator = gameSet.iterator(); iterator.hasNext(); ) {
			Game game = iterator.next();
			if (game.getResult() < 0 || game.getResult() > 2) {
				iterator.remove();
			}
		}
		player.setGame(gameSet);
		modelAndView.addObject("player", player);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editingPlayer(@ModelAttribute Player player, @PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		player.setCurrentRating(player.getStartRating());
		playerService.updatePlayer(player);
		String message = "Player was successfully edited.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
