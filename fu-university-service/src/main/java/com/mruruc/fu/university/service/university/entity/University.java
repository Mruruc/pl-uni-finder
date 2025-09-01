package com.mruruc.fu.university.service.university.entity;


import com.mruruc.fu.university.service.program.entity.Program;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universities")
@EntityListeners(AuditingEntityListener.class)
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UniversityType type;

    @Column(name = "established_year", nullable = false)
    private Integer establishedYear;

    @Column(name = "world_ranking")
    private Integer worldRanking;

    @Column(name = "national_ranking")
    private Integer nationalRanking;

    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    private double rating;

    @Column(name = "total_students")
    private Integer totalStudents;

    @Column(name = "international_students")
    private Integer internationalStudents;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Embedded
    private Address address;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Program> programs;


    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", establishedYear=" + establishedYear +
                ", worldRanking=" + worldRanking +
                ", nationalRanking=" + nationalRanking +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", rating=" + rating +
                ", totalStudents=" + totalStudents +
                ", internationalStudents=" + internationalStudents +
                ", isActive=" + isActive +
                ", address=" + address +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
