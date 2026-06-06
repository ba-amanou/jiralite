package com.jiralite.backend.entity;

import com.jiralite.backend.entity.base.BaseEntity;
import com.jiralite.backend.entity.enums.GlobalRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column(nullable = false, unique = true, length = 20)
    @Enumerated(EnumType.STRING)
    private GlobalRole type;

}
