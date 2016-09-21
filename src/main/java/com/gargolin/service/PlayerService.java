package com.gargolin.service;

import com.gargolin.model.Player;

import java.util.List;

public interface PlayerService {

    void addPlayer(Player player);

    void updatePlayer(Player player);

    Player getPlayer(int id);

    void deletePlayer(int id);

    List<Player> getPlayers();

}
