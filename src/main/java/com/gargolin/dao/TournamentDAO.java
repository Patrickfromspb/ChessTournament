package com.gargolin.dao;


import com.gargolin.model.Player;
import com.gargolin.model.Tournament;

import java.util.List;

/**
 * Created by User on 08.09.2016.
 */
public interface TournamentDAO {

    public void addTournament(Tournament tournament, List<String> players);
    public List<Tournament> getTournaments();
    public Tournament getTournament(int id);
    public List<Player> getPlayers();
    public void updategame(String firstPlayer, String secondPlayer, String tournament);
}
