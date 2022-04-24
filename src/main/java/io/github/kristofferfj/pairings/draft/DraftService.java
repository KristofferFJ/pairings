package io.github.kristofferfj.pairings.draft;

import io.github.kristofferfj.pairings.draft.dto.DraftStanding;
import io.github.kristofferfj.pairings.draft.dto.Standing;
import io.github.kristofferfj.pairings.entities.Draft;
import io.github.kristofferfj.pairings.entities.Match;
import io.github.kristofferfj.pairings.entities.Player;
import io.github.kristofferfj.pairings.repositories.MatchRepository;
import io.github.kristofferfj.pairings.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DraftService {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchService matchService;

    public DraftService(PlayerRepository playerRepository,
                        MatchRepository matchRepository,
                        MatchService matchService) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.matchService = matchService;
    }

    public void startNextRound(Draft draft) {
        createPairings(draft);
    }

    public DraftStanding getDraftStanding(Draft draft) {
        List<Match> matches = getAllMatchesForDraft(draft);
        List<Standing> standings = playerRepository.findPlayersInDraft(draft.getDraftId())
                .stream()
                .map(player -> getStanding(player, draft))
                .collect(Collectors.toList());
        return new DraftStanding(draft, matches, standings, false);
    }

    public Standing getStanding(Player player, Draft draft) {
        return new Standing(player.getPlayerId(),
                player.getName(),
                matchService.calculatePointsInDraft(draft, player));
    }

    private List<Match> getAllMatchesForDraft(Draft draft) {
        return matchRepository.findAllByDraftId(draft.getDraftId());
    }

    private boolean isRoundOngoing(Draft draft) {
        return false; //TODO
    }

    private List<Match> createPairings(Draft draft) {
        List<Player> players = playerRepository.findPlayersInDraft(draft.getDraftId())
                .stream().sorted(Comparator.comparingInt(o -> matchService.calculatePointsInDraft(draft, o)))
                .collect(Collectors.toList());
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < players.size(); i += 2) {
            matches.add(new Match(
                    players.get(i).getPlayerId(),
                    players.get(i + 1).getPlayerId(),
                    draft.getDraftId()));
        }
        return matchRepository.saveAllAndFlush(matches);
    }
}
