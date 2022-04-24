package io.github.kristofferfj.pairings.entities;

import javax.persistence.*;

@Entity
public class Match {

    public Match() {
    }

    public Match(Long player1Id, Long player2Id, Long draftId) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.draftId = draftId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @Column(name = "PLAYER1_ID")
    private Long player1Id;

    @Column(name = "PLAYER2_ID")
    private Long player2Id;

    @Column(name = "DRAFT_ID")
    private Long draftId;

    @Column(name = "PLAYER1_ROUNDS")
    private int Player1Rounds;

    @Column(name = "PLAYER2_ROUNDS")
    private int Player2Rounds;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long id) {
        this.matchId = id;
    }

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long player1Id) {
        this.player1Id = player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long player2Id) {
        this.player2Id = player2Id;
    }

    public Long getDraftId() {
        return draftId;
    }

    public void setDraftId(Long draftId) {
        this.draftId = draftId;
    }

    public int getPlayer1Rounds() {
        return Player1Rounds;
    }

    public void setPlayer1Rounds(int player1Rounds) {
        Player1Rounds = player1Rounds;
    }

    public int getPlayer2Rounds() {
        return Player2Rounds;
    }

    public void setPlayer2Rounds(int player2Rounds) {
        Player2Rounds = player2Rounds;
    }
}
