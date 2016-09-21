package com.gargolin.model;

import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by User on 13.09.2016.
 */
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue
    @Column(name = "game_ID")
    private Integer id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "FIRSTPLAYER_ID", referencedColumnName = "PLAYER_ID")
    private Player firstPlayer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "SECONDPLAYER_ID", referencedColumnName = "PLAYER_ID")
    private Player secondPlayer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;
    private int result;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game", cascade = CascadeType.REMOVE)
    private Set<Change> change;

    public Game() {
    }

    public Game(Player firstPlayer, Player secondPlayer, Tournament tournament) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.tournament = tournament;
        result = -1000;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        boolean result = false;
        if (obj instanceof Game) {
            result = ((Game) obj).getFirstPlayer() == this.firstPlayer &&
                    ((Game) obj).getSecondPlayer() == this.secondPlayer &&
                    ((Game) obj).getTournament() == this.tournament;
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

    public Set<Change> getChange() {
        return change;
    }

    public void setChange(Set<Change> change) {
        this.change = change;
    }
}
