package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShiftTypes {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(columnDefinition = "text DEFAULT 'NA'")
    private String name;

    @Column(columnDefinition = "text DEFAULT 'NA'")
    private String description;

    @Column(columnDefinition = "bool DEFAULT false")
    private boolean active;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(columnDefinition = "text DEFAULT 'NA'")
    private String createdBy;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(columnDefinition = "text DEFAULT 'NA'")
    private String updatedBy;

    @Column(columnDefinition = "varchar(32) DEFAULT 'UTC'")
    private String timeZone;

    @PrePersist
    public void prePersist() {
        // Ensure default values are set before persisting
        this.createdAt = new Timestamp(System.currentTimeMillis());
        if (this.updatedBy == null) {
            this.updatedBy = "NA";
        }
    }
}
