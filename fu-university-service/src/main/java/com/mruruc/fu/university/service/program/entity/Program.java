package com.mruruc.fu.university.service.program.entity;

import com.mruruc.fu.university.service.university.entity.University;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programs")
@EntityListeners(AuditingEntityListener.class)
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT", length = 500)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramLevel level;

    @Column(nullable = false)
    private double duration;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private BigDecimal tuition;

    private double rating;

    private int studentsCount;

    private Instant nextDeadline;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    // CascadeType.PERSIST: When a Program is saved, any new Tags in the tags set will also be saved.
    // CascadeType.MERGE: When a Program is updated, any changes to existing Tags in the tags set will also be merged.
    // FetchType.LAZY: Tags are loaded on demand, not automatically with the Program entity.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "program_tags",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    // CascadeType.MERGE: When a Program is updated, any changes to existing Requirements in the requirements set will also be merged.
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "program_requirements",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id")
    )
    private Set<Requirement> requirements;


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Program program)) return false;
        return Double.compare(getDuration(), program.getDuration()) == 0 &&
                Double.compare(getRating(), program.getRating()) == 0 &&
                getStudentsCount() == program.getStudentsCount() &&
                isActive() == program.isActive() &&
                Objects.equals(getId(), program.getId()) &&
                Objects.equals(getName(), program.getName()) &&
                Objects.equals(getDescription(), program.getDescription()) &&
                getLevel() == program.getLevel() &&
                Objects.equals(getLanguage(), program.getLanguage()) &&
                Objects.equals(getTuition(), program.getTuition()) &&
                Objects.equals(getNextDeadline(), program.getNextDeadline()) &&
                Objects.equals(getCreatedAt(), program.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), program.getUpdatedAt()) &&
                Objects.equals(getUniversity().getId(), program.getUniversity().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getLevel(),
                getDuration(), getLanguage(), getTuition(), getRating(),
                getStudentsCount(), getNextDeadline(), isActive(), getCreatedAt(),
                getUpdatedAt(), getUniversity().getId());
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", duration=" + duration +
                ", language='" + language + '\'' +
                ", tuition=" + tuition +
                ", rating=" + rating +
                ", studentsCount=" + studentsCount +
                ", nextDeadline=" + nextDeadline +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
//                ", university=" + university +
//                ", tags=" + tags +
//                ", requirements=" + requirements +
                '}';
    }
}
