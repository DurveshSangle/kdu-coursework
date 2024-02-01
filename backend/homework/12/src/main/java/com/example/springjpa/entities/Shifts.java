package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shifts {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "shift_types_id", referencedColumnName = "id")
    private ShiftTypes shiftType;

    @Column(columnDefinition = "varchar(128) DEFAULT 'NA'")
    private String name;

    @Column(columnDefinition = "date")
    private Date dateStart;

    @Column(columnDefinition = "date")
    private Date dateEnd;

    @Column(columnDefinition = "time")
    private Time timeStart;

    @Column(columnDefinition = "time")
    private Time timeEnd;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(columnDefinition = "TEXT DEFAULT 'NA'")
    private String createdBy;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(columnDefinition = "text DEFAULT 'NA'")
    private String updatedBy;

    @Column(columnDefinition = "varchar(32) DEFAULT 'UTC'")
    private String timeZone;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenants tenant;

    @PrePersist
    public void prePersist() {
        // Ensure default values are set before persisting
        this.createdAt = new Timestamp(System.currentTimeMillis());
        if (this.updatedBy == null) {
            this.updatedBy = "NA";
        }
    }
}
