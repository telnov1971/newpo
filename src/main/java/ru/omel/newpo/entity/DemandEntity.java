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

    public String getObject() {
        return Object;
    }

    public void setObject(String object) {
        Object = object;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getPowerCur() {
        return powerCur;
    }

    public void setPowerCur(Double powerCur) {
        this.powerCur = powerCur;
    }

    public Double getPowerDec() {
        return powerDec;
    }

    public void setPowerDec(Double powerDec) {
        this.powerDec = powerDec;
    }

    public VoltEntity getVolt() {
        return volt;
    }

    public void setVolt(VoltEntity volt) {
        this.volt = volt;
    }

    public SafeEntity getSafe() {
        return safe;
    }

    public void setSafe(SafeEntity safe) {
        this.safe = safe;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Boolean getLoad1c() {
        return load1c;
    }

    public void setLoad1c(Boolean load1c) {
        this.load1c = load1c;
    }

    public Boolean getRewrite() {
        return rewrite;
    }

    public void setRewrite(Boolean rewrite) {
        this.rewrite = rewrite;
    }

    public Set<HistoryEntity> getHistory() {
        return history;
    }

    public void setHistory(Set<HistoryEntity> history) {
        this.history = history;
    }

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
