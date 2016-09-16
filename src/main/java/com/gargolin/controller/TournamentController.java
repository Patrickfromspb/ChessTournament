package com.gargolin.controller;

import com.gargolin.model.Party;
import com.gargolin.model.Player;
import com.gargolin.model.Tournament;
import com.gargolin.model.TournamentDetail;
import com.gargolin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by User on 04.09.2016.
 */
@Controller

@RequestMapping(value="/tournament")
public class TournamentController {

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
        ArrayList<Player> players=new ArrayList<Player>();
        for (TournamentDetail tournamentDetail :tournament.getTournamentDetail()){
            players.add(tournamentDetail.getPlayer());
            System.out.println(tournamentDetail.getPlayer().getId());
        }
        modelAndView.addObject("tournament",tournament);
        modelAndView.addObject("player",players);
        modelAndView.addObject("size",players.size());
        Set<Party> partySet=tournament.getParty();
        Map<Party,Integer> partyMap=new HashMap<Party, Integer>();
        for(Party party:partySet) partyMap.put(party,party.getResult());
        ArrayList<Integer> results=new ArrayList<Integer>();
        for (int i=0;i<players.size();i++)
            for (int j=0;j<players.size();j++){
               System.out.println(results.add(partyMap.get(new Party(players.get(i),players.get(j),tournament))));
                System.out.println(players.get(j).getId());
            }
        modelAndView.addObject("results",results);
        return modelAndView;
    }
    @RequestMapping(value="/ajax.htm",method=RequestMethod.POST)
    public  @ResponseBody void  Change(@RequestBody String string, HttpServletRequest request) {
        string=string.replace("\"","");
        System.out.println(string);
        String[] party=string.split("a");
        System.out.println(party[0]+party[1]+party[2]);
        tournamentService.updateParty(party[0],party[1],party[2]);
    }

}
