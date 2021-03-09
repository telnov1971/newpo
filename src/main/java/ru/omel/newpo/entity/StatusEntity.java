package ru.omel.newpo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class StatusEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "code")
        private String code;

        @OneToMany(mappedBy = "status")
        private Set<DemandEntity> demands = new HashSet<>();

        StatusEntity(String name, String code){
            this.name = name;
            this.code = code;
        }

        public StatusEntity() {
        }

        StatusEntity(String name){
            this.name = name;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Set<DemandEntity> getDemands() {
            return demands;
        }

        public void setDemands(Set<DemandEntity> demands) {
            this.demands = demands;
        }
    }

