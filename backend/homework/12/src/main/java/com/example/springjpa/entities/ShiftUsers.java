package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftUsers {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "shift_id", referencedColumnName = "id")
    private Shifts shift;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(columnDefinition = "TEXT DEFAULT 'NA'")
    private String createdBy;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(columnDefinition = "TEXT DEFAULT 'NA'")
    private String updatedBy;

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
