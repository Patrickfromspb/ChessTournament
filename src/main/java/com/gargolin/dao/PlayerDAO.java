package com.gargolin.dao;

import com.gargolin.model.Player;

import java.util.List;

public interface PlayerDAO {

    void addPlayer(Player player);

    void updatePlayer(Player player);

    Player getPlayer(int id);

    void deletePlayer(int id);

    List<Player> getPlayers();

}
