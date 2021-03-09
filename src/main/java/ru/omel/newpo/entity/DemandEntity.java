package ru.omel.newpo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "demand")
public class DemandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // дата создания
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;
    // дата закрытия
    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;
    // дата последнего изменения
    @Temporal(TemporalType.DATE)
    @Column(name = "mod_date")
    private Date modDate;

    // кем изменено
    private Boolean update1c;

    // статус
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    // заявитель
    @Column(name = "declarant")
    private String declarant;

    // контактный телефон
    @Column(name = "contact")
    private String contact;

    // реквизиты заявителя
    @Column(name = "requisite")
    private String requisite;

    // Юридический адрес заявителя
    @Column(name = "adress_ur")
    private String adressUr;

    // Фактический адрес заявителя
    @Column(name = "adress_fact")
    private String adressFact;

    // объект
    @Column(name = "object", nullable = false)
    private String Object;

    // адрес объекта
    @Column(name = "adress")
    private String adress;

    // зона ответственности
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity region;

    // описание
    @Column(name = "description")
    private String description;

    // мощность текущая
    @Column(name = "powcur")
    private Double powerCur;

    // мощность заявляемая
    @Column(name = "powdec")
    private Double powerDec;

    // мощность максимальная
    @Column(name = "powmax")
    private Double powerMax;

    // уровень напряжнения
    @ManyToOne
    @JoinColumn(name = "volt_id")
    private VoltEntity volt;

    // категория надёжности
    @ManyToOne
    @JoinColumn(name = "safe_id")
    private SafeEntity safe;

    // сумма договора
    @Column(name = "sum")
    private Double sum;

    // Получить проект договора
    @ManyToOne
    @JoinColumn(name = "send_id")
    private SendEntity send;

    // Рассрочка платежа -> list
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private PlanEntity plan;

    // СОГЛАСИЕ НА ОБРАБОТКУ ДАННЫХ -> Bool
    @Column(name = "consent")
    private Boolean consent;

    // Типовой договор -> url
    @Column(name = "contract")
    private String contract;

    // Ценовая категория - > list
    @ManyToOne
    @JoinColumn(name = "price_id")
    private PriceEntity price;

    // гарантирующий поставщик -> list
    @ManyToOne
    @JoinColumn(name = "garant_id")
    private GarantEntity garant;

    // пользователь
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
