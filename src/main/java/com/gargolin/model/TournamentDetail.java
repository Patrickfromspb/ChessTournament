package com.gargolin.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by User on 20.08.2016.
 */
@Entity
@Table(name="tournamentdetails")
public class TournamentDetail {
    @Id
    @GeneratedValue
    @Column(name = "TOURNAMENTDETAILS_ID")
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYER_ID")
    private Player player;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;
    public Integer getId() {

        return id;
    }
    public void setId(Integer id) {

        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public TournamentDetail(){

    }
    public TournamentDetail(Tournament tournament, Player player){
        this.player=player;
        this.tournament=tournament;
    }
}

