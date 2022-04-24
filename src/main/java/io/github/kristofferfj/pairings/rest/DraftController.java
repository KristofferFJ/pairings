package io.github.kristofferfj.pairings.rest;

import io.github.kristofferfj.pairings.entities.Draft;
import io.github.kristofferfj.pairings.entities.Player;
import io.github.kristofferfj.pairings.repositories.DraftRepository;
import io.github.kristofferfj.pairings.repositories.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drafts")
public class DraftController {

    private final DraftRepository draftRepository;
    private final PlayerRepository playerRepository;

    public DraftController(DraftRepository draftRepository, PlayerRepository playerRepository) {
        this.draftRepository = draftRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public ResponseEntity<List<DraftDto>> getDrafts() {
        return ResponseEntity.ok(draftRepository.findAll().stream()
                .map(draft -> new DraftDto(
                        draft, playerRepository.findPlayersInDraft(draft.getId())
                ))
                .collect(Collectors.toList()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DraftDto> newDraft(@RequestBody NewDraftDto newDraftDto) {
        List<Player> players = newDraftDto.getPlayerNames().stream()
                .map(name -> playerRepository.findByName(name).orElseThrow(() ->
                        new IllegalArgumentException("Player " + name + " does not exist.")))
                .collect(Collectors.toList());
        Draft draft = new Draft(newDraftDto.getName());
        draft = draftRepository.saveAndFlush(draft);

        for (Player player : players) {
            draftRepository.addPlayerToDraft(player.getId(), draft.getId());
        }

        return ResponseEntity.ok(
                new DraftDto(draft, players));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDraft(@PathVariable("id") Long id) {
        playerRepository.deleteById(id);
        return ResponseEntity.ok("{}");
    }

    private static class NewDraftDto {
        public String name;
        public List<String> playerNames;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getPlayerNames() {
            return playerNames;
        }

        public void setPlayerNames(List<String> playerNames) {
            this.playerNames = playerNames;
        }
    }

    private static class DraftDto {
        public Long id;
        public String name;
        public List<PlayerController.PlayerDto> players;

        public DraftDto(Draft draft, List<Player> players) {
            this.id = draft.getId();
            this.name = draft.getName();
            this.players = players.stream().map(PlayerController.PlayerDto::new)
                    .collect(Collectors.toList());
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
