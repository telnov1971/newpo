package ru.omel.newpo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "event")
    private String event;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private DemandEntity demand;

    HistoryEntity(String event, DemandEntity demand){
        this.createDate = new Date();
        this.event = event;
        this.demand = demand;
    }
}