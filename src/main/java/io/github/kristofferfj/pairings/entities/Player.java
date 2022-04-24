package io.github.kristofferfj.pairings.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }
}
