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
@NoArgsConstructor
@Table(name = "safe")
public class SafeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "code")
    private String code;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "safe")
    private Set<DemandEntity> demands = new HashSet<>();

    SafeEntity(String name, String code){
        this.name = name;
        this.code = code;
    }

    SafeEntity(String name){
        this.name = name;
    }
}
