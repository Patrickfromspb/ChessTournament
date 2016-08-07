package com.gargolin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue
    private Integer id;


    private String firstName;
    private String secondName;
    private Integer startRating;
    private Integer currentRating;

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

    public Integer getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Integer currentRating) {
        this.currentRating = currentRating;
    }
}