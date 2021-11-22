package com.ac.cooperativism.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime openDate = OffsetDateTime.now();

    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime closeDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
    private Topic topic;

    public void setCloseDate(OffsetDateTime closeDate) {
        this.closeDate = Objects.requireNonNullElseGet(closeDate, () -> OffsetDateTime.now().plusMinutes(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
