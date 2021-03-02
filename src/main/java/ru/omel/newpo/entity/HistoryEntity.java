package ru.omel.newpo.entity;

import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "event")
    @Length(max = 2048)
    private String event;

    @Column(name = "load1c")
    private Boolean load1c;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private DemandEntity demand;

    HistoryEntity(String event, DemandEntity demand){
        this.createDate = new Date();
        this.event = event;
        this.demand = demand;
    }

    public HistoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Boolean getLoad1c() {
        return load1c;
    }

    public void setLoad1c(Boolean load1c) {
        this.load1c = load1c;
    }

    public DemandEntity getDemand() {
        return demand;
    }

    public void setDemand(DemandEntity demand) {
        this.demand = demand;
    }
}
