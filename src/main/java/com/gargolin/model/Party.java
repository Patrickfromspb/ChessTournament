package com.gargolin.model;

import javax.persistence.*;
import java.util.Random;

import com.sun.istack.internal.Nullable;
import org.apache.commons.lang.builder.*;
/**
 * Created by User on 13.09.2016.
 */
@Entity
@Table(name="parties")
public class Party {
    @Id
    @GeneratedValue
    @Column(name = "PARTY_ID")
    private Integer id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "FIRSTPLAYER_ID",referencedColumnName = "PLAYER_ID")
    private Player firstPlayer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "SECONDPLAYER_ID",referencedColumnName = "PLAYER_ID")
    @Nullable
    private Player secondPlayer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;
    private int result;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public Party(){}
    public Party(Player firstPlayer, Player secondPlayer, Tournament tournament){
        this.firstPlayer=firstPlayer;
        this.secondPlayer=secondPlayer;
        this.tournament=tournament;
        result=-1000;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)  return true;
        if (obj == null)  return false;
        boolean result=false;
        if (obj instanceof Party) {
           result= (Player)((Party) obj).getFirstPlayer()==this.firstPlayer&&
                    (Player)((Party) obj).getSecondPlayer()==this.secondPlayer&&
                    (Tournament)((Party) obj).getTournament()==this.tournament;
        }
            return result;

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(firstPlayer).
                append(secondPlayer).
                append(tournament).
                toHashCode();
    }
}
