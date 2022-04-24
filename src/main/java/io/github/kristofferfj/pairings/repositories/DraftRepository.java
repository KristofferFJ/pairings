package io.github.kristofferfj.pairings.repositories;

import io.github.kristofferfj.pairings.entities.Draft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO PLAYER_DRAFT_MAP VALUES (:playerId, :draftId)", nativeQuery = true)
    int addPlayerToDraft(@Param("playerId") Long playerId, @Param("draftId") Long draftId);

}
