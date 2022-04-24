package io.github.kristofferfj.pairings.rest;

import io.github.kristofferfj.pairings.entities.Player;
import io.github.kristofferfj.pairings.repositories.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity.ok(playerRepository.findAll().stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                new PlayerDto(playerRepository.getById(id)));
    }

    @PostMapping
    public ResponseEntity<PlayerDto> newPlayer(@RequestBody NewPlayerDto newPlayerDto) {
        Player player = new Player(newPlayerDto.getName());
        player = playerRepository.saveAndFlush(player);
        return ResponseEntity.ok(
                new PlayerDto(player));
    }

    private static class NewPlayerDto {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PlayerDto {
        public Long id;
        public String name;

        public PlayerDto(Player player) {
            this.id = player.getId();
            this.name = player.getName();
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
