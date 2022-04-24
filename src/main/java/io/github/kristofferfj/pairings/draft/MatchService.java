package io.github.kristofferfj.pairings.draft;

import io.github.kristofferfj.pairings.entities.Draft;
import io.github.kristofferfj.pairings.entities.Match;
import io.github.kristofferfj.pairings.entities.Player;
import io.github.kristofferfj.pairings.repositories.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public int calculatePointsInDraft(Draft draft, Player player) {
        return matchRepository.findAllMatchesForPlayerInDraft(player.getPlayerId(), draft.getDraftId())
                .stream()
                .mapToInt(match -> calculatePointsInMatch(match, player))
                .sum();
    }

    private int calculatePointsInMatch(Match match, Player player) {
        if (isDraw(match)) {
            return 1;
        }
        if (isWin(match, player)) {
            return 3;
        }
        return 0;
    }

    private boolean isWin(Match match, Player player) {
        return match.getPlayer1Rounds() > match.getPlayer2Rounds() && match.getPlayer1Id().equals(player.getPlayerId());
    }

    private boolean isDraw(Match match) {
        return match.getPlayer1Rounds() == match.getPlayer2Rounds();
    }

}
