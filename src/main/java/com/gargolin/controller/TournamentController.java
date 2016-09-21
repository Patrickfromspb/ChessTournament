package com.gargolin.controller;

import com.gargolin.model.*;
import com.gargolin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by User on 04.09.2016.
 */
@Controller

@RequestMapping(value = "/tournament")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTournamentPage() {
        ModelAndView modelAndView = new ModelAndView("add-tournament-form");
        modelAndView.addObject("tournament", new Tournament());
        List<Player> players = tournamentService.getPlayers();
        modelAndView.addObject("player", players);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addingTournament(@ModelAttribute Tournament tournament, @RequestParam(value = "Players") String[] selected) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<String> players = Arrays.asList(selected);
        if (players.size() < 3) {
            String message = "Not Created. Tournament must contain at least three players";
            modelAndView.addObject("message", message);
            return modelAndView;
        }
        tournamentService.addTournament(tournament, players);
        String message = "Tournament was successfully added.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public ModelAndView listOTournaments() {
        ModelAndView modelAndView = new ModelAndView("list-of-tournaments");
        List<Tournament> tournaments = tournamentService.getTournaments();
        modelAndView.addObject("tournaments", tournaments);
        return modelAndView;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView editTournamentPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("tournament-table");
        Tournament tournament = tournamentService.getTournament(id);
        ArrayList<Player> players = new ArrayList<Player>();
        for (TournamentDetail tournamentDetail : tournament.getTournamentDetail()) {
            players.add(tournamentDetail.getPlayer());
            System.out.println(tournamentDetail.getPlayer().getId());
        }
        modelAndView.addObject("tournament", tournament);
        modelAndView.addObject("player", players);
        modelAndView.addObject("size", players.size());
        Set<Game> gameSet = tournament.getGame();
        Map<Game, Integer> gameMap = new HashMap<Game, Integer>();
        for (Game game : gameSet) gameMap.put(game, game.getResult());
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < players.size(); i++)
            for (Player player : players) {
                results.add(gameMap.get(new Game(players.get(i), player, tournament)));
            }
        modelAndView.addObject("results", results);
        return modelAndView;
    }

    @RequestMapping(value = "/ajax.htm", method = RequestMethod.POST)
    public
    @ResponseBody
    void Change(@RequestBody String string) {
        string = string.replace("\"", "");
        String[] game = string.split("a");
        tournamentService.updategame(game[0], game[1], game[2]);
    }

    @RequestMapping(value = "/changes/{id}", method = RequestMethod.GET)
    public ModelAndView showChanges(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("changes");
        List<ChangesView> changes = tournamentService.getChangesView(id);
        modelAndView.addObject("changes", changes);
        return modelAndView;
    }
}
