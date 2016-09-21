package com.gargolin.service;

import com.gargolin.dao.PlayerDAO;
import com.gargolin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    public void addPlayer(Player player) {
        playerDAO.addPlayer(player);
    }

    public void updatePlayer(Player player) {
        playerDAO.updatePlayer(player);
    }

    public Player getPlayer(int id) {
        return playerDAO.getPlayer(id);
    }

    public void deletePlayer(int id) {
        playerDAO.deletePlayer(id);
    }

    public List<Player> getPlayers() {
        return playerDAO.getPlayers();
    }

}
