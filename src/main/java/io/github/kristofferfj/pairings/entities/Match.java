package io.github.kristofferfj.pairings.entities;

import javax.persistence.*;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Player.class)
    private Player player1;

    @ManyToOne(targetEntity = Player.class)
    private Player player2;

    @ManyToOne(targetEntity = Draft.class)
    private Draft match;
}
