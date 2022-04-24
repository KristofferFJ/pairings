package io.github.kristofferfj.pairings.repositories;

import io.github.kristofferfj.pairings.entities.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
}
