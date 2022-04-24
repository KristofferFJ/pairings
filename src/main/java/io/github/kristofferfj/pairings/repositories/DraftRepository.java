package io.github.kristofferfj.pairings.repositories;

import io.github.kristofferfj.pairings.entities.Draft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftRepository extends CrudRepository<Draft, Long> {
}
