package ru.omel.newpo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "volt")
@NoArgsConstructor
public class VoltEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "code")
    private String code;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "volt")
    private Set<DemandEntity> demands = new HashSet<>();

    VoltEntity(String name, String code){
        this.name = name;
        this.code = code;
    }

    VoltEntity(String name){
        this.name = name;
    }
}
