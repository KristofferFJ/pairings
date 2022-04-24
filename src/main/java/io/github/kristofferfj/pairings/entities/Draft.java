package io.github.kristofferfj.pairings.entities;

import javax.persistence.*;

@Entity
public class Draft {

    public Draft() {
    }

    public Draft(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long draftId;

    private String name;

    public Long getDraftId() {
        return draftId;
    }

    public void setDraftId(Long id) {
        this.draftId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
