package com.gargolin.service;

import com.gargolin.model.Party;
import com.gargolin.model.Player;
import com.gargolin.model.Tournament;
import com.gargolin.model.TournamentDetail;
import org.hibernate.HibernateException;
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
        for ( Player firstPlayer: list)
            for ( Player secondPlayer: list) getCurrentSession().save( new Party(firstPlayer,secondPlayer,tournament));
    }
    public List<Tournament> getTournaments() {
        return getCurrentSession().createQuery("from Tournament").list();
    }
    public List<Player> getPlayers() {
        return getCurrentSession().createQuery("from Player").list();
    }

    @Override
    public void updateParty(String firstPlayer, String secondPlayer, String tournament) {
        Party party=(Party)getCurrentSession().createQuery("from Party where firstPlayer.id="+firstPlayer+" and secondPlayer.id="+secondPlayer+" and tournament.id="+ tournament).list().get(0);
      int oldResult=party.getResult();
      final int newResult;
      final int reverse;
      switch (oldResult){
          case 0: newResult=1; reverse=1;break;
          case 1: newResult=2; reverse=0;break;
          case 2: newResult=-1000; reverse=-1000;break;
          default:newResult=0;reverse=2;
      }
         party.setResult(newResult);
         getCurrentSession().update(party);
         party=(Party)getCurrentSession().createQuery("from Party where firstPlayer.id="+secondPlayer+" and secondPlayer.id="+firstPlayer+" and tournament.id="+ tournament).list().get(0);
         party.setResult(reverse);
         getCurrentSession().update(party);

    }


    public Tournament getTournament(int id) {
        Tournament tournament = (Tournament) getCurrentSession().get(Tournament.class, id);
        return tournament;
    }

}
