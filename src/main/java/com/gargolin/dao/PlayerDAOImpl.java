package com.gargolin.dao;

import java.util.List;

import com.gargolin.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAOImpl implements PlayerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addPlayer(Player player) {
		System.out.println("32r2421211");
		System.out.println(player.getCurrentRating()+player.getFirstName()+player.getStartRating()+player.getSecondName());
		getCurrentSession().save(player);
	}

	public void updatePlayer(Player player) {
		Player playerToUpdate = getPlayer(player.getId());
		playerToUpdate.setFirstName(player.getFirstName());
		playerToUpdate.setSecondName(player.getSecondName());
		playerToUpdate.setStartRating(player.getStartRating());
		playerToUpdate.setCurrentRating(player.getStartRating());
		getCurrentSession().update(playerToUpdate);
		
	}

	public Player getPlayer(int id) {
		Player player = (Player) getCurrentSession().get(Player.class, id);
		return player;
	}

	public void deletePlayer(int id) {
		Player player = getPlayer(id);
		if (player != null)
			getCurrentSession().delete(player);
	}

	@SuppressWarnings("unchecked")
	public List<Player> getPlayers() {
		return getCurrentSession().createQuery("from players").list();
	}

}
