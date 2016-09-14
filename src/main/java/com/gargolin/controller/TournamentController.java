package com.gargolin.controller;

import com.gargolin.model.Player;
import com.gargolin.model.Tournament;
import com.gargolin.model.TournamentDetail;
import com.gargolin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 04.09.2016.
 */
@Controller

@RequestMapping(value="/tournament")
public class TournamentController {
    public static ArrayList<Integer> results=null;
    @Autowired
    TournamentService tournamentService;
    @RequestMapping(value="/add", method= RequestMethod.GET)
    public ModelAndView addTournamentPage() {
        ModelAndView modelAndView = new ModelAndView("add-tournament-form");
        modelAndView.addObject("tournament", new Tournament());
        List<Player> players =tournamentService.getPlayers();
        modelAndView.addObject("player",players);
        modelAndView.addObject("size",players.size());
        return modelAndView;
    }
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingTournament(@ModelAttribute Tournament tournament, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("home");
        String[] selected = request.getParameterValues("Players");
        List<String> players = Arrays.asList(selected);
             tournamentService.addTournament(tournament, players);


        String message = "Tournament was successfully added.";
        modelAndView.addObject("Tournament", message);

        return modelAndView;
    }
    @RequestMapping(value="/list")
    public ModelAndView listOTournaments() {
        ModelAndView modelAndView = new ModelAndView("list-of-tournaments");

        List<Tournament> tournaments= tournamentService.getTournaments();
        modelAndView.addObject("tournaments", tournaments);

        return modelAndView;
    }
    @RequestMapping(value="/show/{id}", method=RequestMethod.GET)
    public ModelAndView editTournamentPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("tournament-table");
        Tournament tournament = tournamentService.getTournament(id);
        List<Player> players=new ArrayList<Player>();
        for (TournamentDetail tournamentDetail :tournament.getTournamentDetails()){
            players.add(tournamentDetail.getPlayer());
            System.out.println(tournamentDetail.getPlayer().getId());
        }
        modelAndView.addObject("tournament",tournament);
        modelAndView.addObject("player",players);
        modelAndView.addObject("size",players.size());
         results =new ArrayList<>();
        for(int i=0;i<players.size()*players.size()*players.size();i++) results.add(i);
        return modelAndView;
    }

 /*   @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingTournament(@ModelAttribute Tournament tournament, @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("home");

        tournamentService.updateTournament(tournament);

        String message = "Team was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    } */
}
