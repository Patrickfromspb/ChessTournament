package com.gargolin.model;

import java.util.Date;

/**
 * Created by User on 20.09.2016.
 */
public class ChangesView {
    private Player firstPlayer;
    private Player secondPlayer;
    private Integer result;
    private java.util.Date timestamp;

    public ChangesView() {
    }

    public ChangesView(Player firstPlayer, Player secondPlayer, Integer result, java.util.Date timestamp) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.result = result;
        this.timestamp = timestamp;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
