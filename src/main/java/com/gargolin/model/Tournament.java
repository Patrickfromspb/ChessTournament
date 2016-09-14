package com.gargolin.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by User on 05.08.2016.
 */
@Entity
@Table(name="tournaments")
public class Tournament {
    @Id
    @GeneratedValue
    @Column(name = "TOURNAMENT_ID")
    private Integer id;
    private String tournamentName;
    private String judgeName;

    public Integer getId() {

        return id;
    }
    public void setId(Integer id) {

        this.id = id;
    }

    public String getTournamentName() {

        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {

        this.tournamentName= tournamentName;
    }


    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament")
    private Set<TournamentDetail> tournamentDetails;


    public Set<TournamentDetail> getTournamentDetails() {
        return tournamentDetails;
    }

    public void setTournamentDetails(Set<TournamentDetail> tournamentDetails) {
        this.tournamentDetails = tournamentDetails;
    }
}
