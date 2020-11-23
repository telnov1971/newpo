package ru.omel.newpo.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
}
