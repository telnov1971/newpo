package ru.omel.newpo.entity;

import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "file_entity")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Length(max = 2048)
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "load1c")
    private Boolean load1c;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private DemandEntity demand;

    public FileEntity() {
    }

    FileEntity(String name, String link, DemandEntity demand){
        this.name = name;
        this.link = link;
        this.demand = demand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
