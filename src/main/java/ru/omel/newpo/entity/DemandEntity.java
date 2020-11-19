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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    //загружено в 1С
    private Boolean load1c;
    //обновлено
    private Boolean rewrite;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    private Set<HistoryEntity> history = new HashSet<>();

    public String forHistory(){
        return "Объект: '" + this.getObject() +
                "'. Адрес: '" + this.getAdress() +
                "'. Мощность текущая: '" + this.getPowerCur().toString() +
                "'. Мощность требуемая: '" + this.getPowerDec().toString() +
                "'. Класс напряжения: '" + this.getVolt().getName() +
                "'. Категория надёжности: '" + this.getSafe().getName() +
                "'.";
    }
}
