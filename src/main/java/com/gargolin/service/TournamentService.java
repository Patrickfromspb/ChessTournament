package com.gargolin.service;


import com.gargolin.model.Player;
import com.gargolin.model.Tournament;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by User on 08.09.2016.
 */
public interface TournamentService {
    public void addTournament(Tournament tournament, List<String> players);
    public List<Tournament> getTournaments();
    public Tournament getTournament(int id);
    public List<Player> getPlayers();
    public void updateParty(String firstPlayer, String secondPlayer, String tournament);
}
