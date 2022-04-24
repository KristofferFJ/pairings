package io.github.kristofferfj.pairings.repositories;

import io.github.kristofferfj.pairings.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);

    @Query(value = "" +
            "SELECT * FROM PLAYER " +
            "WHERE ID IN " +
            "(SELECT PLAYER FROM PLAYER_DRAFT_MAP WHERE DRAFT = :draftId)",
            nativeQuery = true)
    List<Player> findPlayersInDraft(@Param("draftId") Long draftId);
}
