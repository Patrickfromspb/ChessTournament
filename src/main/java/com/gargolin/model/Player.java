package com.gargolin.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "PLAYER_ID")
    private Integer id;
    private String firstName;
    private String secondName;
    private Integer startRating;
    private Integer currentRating;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "firstPlayer", cascade = CascadeType.REMOVE)
    private Set<Game> game;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSecondName() {

        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getStartRating() {
        return startRating;
    }

    public void setStartRating(Integer startRating) {
        this.startRating = startRating;
    }

    public void setStartRating(String startRating) {
        this.startRating = 1000;
    }

    public Integer getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Integer currentRating) {
        this.currentRating = currentRating;
    }

    public Set<Game> getGame() {
        return game;
    }

    public void setGame(Set<Game> game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.secondName + " " + this.currentRating;
    }
}