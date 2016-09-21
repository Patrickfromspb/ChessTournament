package com.gargolin.dao;

import com.gargolin.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 08.09.2016.
 */

@Repository
public class TournamentDAOImpl implements TournamentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addTournament(Tournament tournament, List<String> players) {
        ArrayList<Player> list = new ArrayList<>();
        for (String s : players) {
            list.add((Player) getCurrentSession().get(Player.class, Integer.getInteger(s)));
        }
        getCurrentSession().save(tournament);
        for (Player player : list)
            getCurrentSession().save(new TournamentDetail(tournament, player));
        for (Player firstPlayer : list)
            for (Player secondPlayer : list) getCurrentSession().save(new Game(firstPlayer, secondPlayer, tournament));
    }

    @Override
    public List<Tournament> getTournaments() {
        return getCurrentSession().createQuery("from Tournament").list();
    }

    @Override
    public List<Player> getPlayers() {
        return getCurrentSession().createQuery("from Player").list();
    }

    @Override
    public void updateGame(String firstPlayer, String secondPlayer, String tournament) {
        Game game = (Game) getCurrentSession().createQuery("from Game where firstPlayer.id=" + firstPlayer + " and secondPlayer.id=" + secondPlayer + " and tournament.id=" + tournament).list().get(0);
        int oldResult = game.getResult();
        final int newResult;
        final int reverse;
        switch (oldResult) {
            case 0:
                newResult = 1;
                reverse = 1;
                break;
            case 1:
                newResult = 2;
                reverse = 0;
                break;
            case 2:
                newResult = -1000;
                reverse = -1000;
                break;
            default:
                newResult = 0;
                reverse = 2;
        }
        game.setResult(newResult);
        getCurrentSession().update(game);
        getCurrentSession().save(new Change(game, newResult));
        game = (Game) getCurrentSession().createQuery("from Game where firstPlayer.id=" + secondPlayer + " and secondPlayer.id=" + firstPlayer + " and tournament.id=" + tournament).list().get(0);
        game.setResult(reverse);
        getCurrentSession().update(game);
        getCurrentSession().save(new Change(game, reverse));
    }

    @Override
    public List<ChangesView> getChangesView(int id) {
        return getCurrentSession()
                    .createQuery("Select new com.gargolin.model.ChangesView(g.firstPlayer, g.secondPlayer, c.result, c.timestamp) from Change c join c.game g where g.tournament.id=" + id + " order by c.timestamp")
                    .list();
    }

    public Tournament getTournament(int id) {
        return (Tournament) getCurrentSession().get(Tournament.class, id);
    }

}
