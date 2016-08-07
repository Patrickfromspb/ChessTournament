package com.gargolin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by User on 04.08.2016.
 */
@Entity
@Table(name="chessparty")
public class ChessParty {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer player;
    private Integer tournament;



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getTournament() {
        return tournament;
    }

    public void setTournament(Integer tournament) {
        this.tournament = tournament;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }
}
