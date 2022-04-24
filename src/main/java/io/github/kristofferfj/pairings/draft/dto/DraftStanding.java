package io.github.kristofferfj.pairings.draft.dto;

import io.github.kristofferfj.pairings.entities.Draft;
import io.github.kristofferfj.pairings.entities.Match;

import java.util.List;

public class DraftStanding {
    private Long draftId;
    private String draftName;
    private List<Match> matches;
    private List<Standing> standings;
    private boolean isRoundOngoing;

    public DraftStanding() {
    }

    public DraftStanding(Draft draft,
                         List<Match> matches,
                         List<Standing> standings,
                         boolean isRoundOngoing) {
        this.draftId = draft.getDraftId();
        this.draftName = draft.getName();
        this.matches = matches;
        this.standings = standings;
        this.isRoundOngoing = isRoundOngoing;
    }

    public Long getDraftId() {
        return draftId;
    }

    public void setDraftId(Long draftId) {
        this.draftId = draftId;
    }

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public boolean isRoundOngoing() {
        return isRoundOngoing;
    }

    public void setRoundOngoing(boolean roundOngoing) {
        isRoundOngoing = roundOngoing;
    }

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }
}
