package com.gargolin.model;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by User on 19.09.2016.
 */
@Entity
@Table(name="changes")
public class Change {
        @Id
        @GeneratedValue
        @Column(name = "Change_ID")
        private int id;



        @ManyToOne(cascade = CascadeType.REMOVE)
        @JoinColumn(name = "game_ID",referencedColumnName = "game_ID")
        private Game game;
        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }
        private int result;
        private java.sql.Timestamp timestamp;
        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }
        public Change(){

        }
        public Change(Game game, int result){
            this.game= game;
            this.result=result;
            timestamp=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        }
}
