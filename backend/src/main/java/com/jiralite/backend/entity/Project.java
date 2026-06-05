package com.jiralite.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.jiralite.backend.entity.base.AuditableEntity;
import com.jiralite.backend.entity.enums.ProjectStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@SQLRestriction("deleted_at IS NULL")
public class Project extends AuditableEntity {

    @Column(nullable = false, length = 60)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProjectStatus status = ProjectStatus.ACTIVE;

    private LocalDateTime deletedAt;
}
