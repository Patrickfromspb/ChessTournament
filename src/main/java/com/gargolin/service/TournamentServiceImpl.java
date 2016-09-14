package com.gargolin.service;

import com.gargolin.model.Player;
import com.gargolin.model.Tournament;
import com.gargolin.model.TournamentDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by User on 08.09.2016.
 */
@Service
@Transactional
@Repository
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public void addTournament(Tournament tournament, List<String> players) {
        ArrayList<Player> list=new ArrayList<>();
        for (String s: players){
            list.add((Player)getCurrentSession().createQuery("from Player where id="+s).list().get(0));
        }
        getCurrentSession().save(tournament);
        for( Player player: list)
            getCurrentSession().save( new TournamentDetail(tournament,player));


    }
    public List<Tournament> getTournaments() {
        System.out.println("GHJJ");
        return getCurrentSession().createQuery("from Tournament").list();
    }
    public List<Player> getPlayers() {
        return getCurrentSession().createQuery("from Player").list();
    }

    public Tournament getTournament(int id) {
        Tournament tournament = (Tournament) getCurrentSession().get(Tournament.class, id);
        return tournament;
    }

}
