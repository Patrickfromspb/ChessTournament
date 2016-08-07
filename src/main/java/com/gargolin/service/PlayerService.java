package com.gargolin.service;

import com.gargolin.model.Player;

import java.util.List;

public interface PlayerService {
	
	public void addPlayer(Player player);
	public void updatePlayer(Player player);
	public Player getPlayer(int id);
	public void deletePlayer(int id);
	public List<Player> getPlayers();

}
