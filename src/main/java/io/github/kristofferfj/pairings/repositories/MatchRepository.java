package io.github.kristofferfj.pairings.repositories;

import io.github.kristofferfj.pairings.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = "SELECT * FROM MATCH " +
            "WHERE DRAFT_ID = :draftId " +
            "AND (PLAYER1_ID = :playerId OR PLAYER2_ID = :playerId)",
            nativeQuery = true)
    List<Match> findAllMatchesForPlayerInDraft(@Param("playerId") Long playerId, @Param("draftId") Long draftId);

    List<Match> findAllByDraftId(Long draftId);
}
