package com.gargolin.dao;

import com.gargolin.model.Player;

import java.util.List;

public interface PlayerDAO {

    public void addPlayer(Player player);
    public void updatePlayer(Player player);
    public Player getPlayer(int id);
    public void deletePlayer(int id);
    public List<Player> getPlayers();

}
