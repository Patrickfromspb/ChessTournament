package com.gargolin.service;

import com.gargolin.dao.TournamentDAO;
import com.gargolin.model.ChangesView;
import com.gargolin.model.Player;
import com.gargolin.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 08.09.2016.
 */
@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private TournamentDAO tournamentDAO;


    @Override
    public void addTournament(Tournament tournament, List<String> players) {
        tournamentDAO.addTournament(tournament, players);
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournamentDAO.getTournaments();
    }

    @Override
    public Tournament getTournament(int id) {
        return tournamentDAO.getTournament(id);
    }

    @Override
    public List<Player> getPlayers() {
        return tournamentDAO.getPlayers();
    }

    @Override
    public void updateGame(String firstPlayer, String secondPlayer, String tournament) {
        tournamentDAO.updateGame(firstPlayer, secondPlayer, tournament);
    }

    @Override
    public List<ChangesView> getChangesView(int id) {
        return tournamentDAO.getChangesView(id);
    }
}
