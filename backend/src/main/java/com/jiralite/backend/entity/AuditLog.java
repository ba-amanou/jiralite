package com.jiralite.backend.entity;

import java.util.UUID;

import com.jiralite.backend.entity.base.TimestampedEntity;
import com.jiralite.backend.entity.enums.ActionType;
import com.jiralite.backend.entity.enums.EntityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
public class AuditLog extends TimestampedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performed_by", nullable = false)
    private User performedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ActionType action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EntityType entityType;

    @Column(nullable = false)
    private UUID entityId;

    @Column(columnDefinition = "TEXT")
    private String details;

}
