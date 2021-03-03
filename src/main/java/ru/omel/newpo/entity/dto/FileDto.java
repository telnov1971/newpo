package ru.omel.newpo.entity.dto;

public class FileDto {
    private Long id;
    private String name;
    private String link;
    private Long demand;

    FileDto(Long id, String name, String link, Long demand){
        this.id = id;
        this.name = name;
        this.link = link;
        this.demand = demand;
    }

    public FileDto() {
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

    public Long getDemand() {
        return demand;
    }

    public void setDemand(Long demand) {
        this.demand = demand;
    }
}
