package io.github.kristofferfj.pairings.draft.dto;

public class Standing {
    private Long playerId;
    private String playerName;
    private int points;

    public Standing(Long playerId, String playerName, int points) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
