package com.gargolin.model;

import javax.persistence.*;

/**
 * Created by User on 13.09.2016.
 */
@Entity
@Table(name="chessparty")
public class Parties {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FIRSTPLAYER_ID")
    private Player firstPlayer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SECONDPLAYER_ID")
    private Player secondPlayer;

}
