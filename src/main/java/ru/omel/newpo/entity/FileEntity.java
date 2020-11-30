package ru.omel.newpo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file_entity")
@Data
@NoArgsConstructor
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

    FileEntity(String name, String link, DemandEntity demand){
        this.name = name;
        this.link = link;
        this.demand = demand;
    }

}
