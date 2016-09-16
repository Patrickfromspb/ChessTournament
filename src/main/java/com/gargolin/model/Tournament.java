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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament", cascade = CascadeType.REMOVE)
    private Set<TournamentDetail> tournamentDetail;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament", cascade = CascadeType.REMOVE)
    private Set<Party> party;


    public Set<TournamentDetail> getTournamentDetail() {
        return tournamentDetail;
    }

    public void setTournamentDetail(Set<TournamentDetail> tournamentDetail) {
        this.tournamentDetail = tournamentDetail;
    }

    public Set<Party> getParty() {
        return party;
    }

    public void setParty(Set<Party> party) {
        this.party = party;
    }
}
