package com.gargolin.dao;


import com.gargolin.model.ChangesView;
import com.gargolin.model.Player;
import com.gargolin.model.Tournament;

import java.util.List;

/**
 * Created by User on 08.09.2016.
 */
public interface TournamentDAO {

    void addTournament(Tournament tournament, List<String> players);

    List<Tournament> getTournaments();

    Tournament getTournament(int id);

    List<Player> getPlayers();

    void updategame(String firstPlayer, String secondPlayer, String tournament);

    List<ChangesView> getChangesView(int id);

}
