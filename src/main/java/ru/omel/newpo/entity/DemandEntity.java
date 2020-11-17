package ru.omel.newpo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "demand")
public class DemandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "object", nullable = false)
    private String Object;

    @Column(name = "adress")
    private String adress;

    @Column(name = "powcur")
    private Double powerCur;

    @Column(name = "powdec")
    private Double powerDec;

    @ManyToOne
    @JoinColumn(name = "volt_id")
    private VoltEntity volt;

    @ManyToOne
    @JoinColumn(name = "safe_id")
    private SafeEntity safe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    private Set<HistoryEntity> history = new HashSet<>();
}
