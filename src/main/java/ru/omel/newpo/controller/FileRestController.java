package ru.omel.newpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.FileEntity;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.entity.dto.FileDto;
import ru.omel.newpo.entity.util.FileConverter;
import ru.omel.newpo.repository.FileRepository;
import ru.omel.newpo.service.DemandService;
import ru.omel.newpo.service.FileService;
import ru.omel.newpo.service.HistoryService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FileRestController {
    private final DemandService demandService;
    private final HistoryService historyService;
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final FileConverter fileConverter;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    FileRestController(DemandService demandService,
                       HistoryService historyService,
                       FileService fileService,
                       FileRepository fileRepository,
                       FileConverter fileConverter){
        this.demandService = demandService;
        this.historyService = historyService;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
        this.fileConverter = fileConverter;
    }

    //@RequestMapping("/fileListRest/{id}")
    //public String listFiles(@PathVariable("id") Long id,
//                           Model model){
        //DemandEntity demandEntity = demandService.findById(id);
        //List<FileEntity> fileEntities = fileService.findAllByDemand(demandEntity);

        //model.addAttribute("files",fileEntities);
        //model.addAttribute("demand", demandEntity);
//        return "<h3>Request: </h3>" + id.toString();
//    }

    @GetMapping(value = "/fileListRest")
    public ResponseEntity<List<FileDto>> read(@RequestParam Long id) {
        DemandEntity demandEntity = demandService.findById(id);
        final List<FileDto> files = fileService.findAllByDemand(demandEntity)
                .stream()
                .map(fileConverter::fileEntityToFileDto)
                .collect(Collectors.toList());

        return files != null &&  !files.isEmpty()
                ? new ResponseEntity<>(files, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/fileListRest/{id}")
    public String uploadFile(@PathVariable("id") Long id,
                             @RequestParam("file") MultipartFile file,
                             @AuthenticationPrincipal UserEntity user,
                             Model model){
        DemandEntity demandEntity = demandService.findById(id);
        List<FileEntity> fileEntities = fileService.findAllByDemand(demandEntity);

        try {
            saveFile(demandEntity, file);
        } catch (IOException e) {

        }
        return "redirect:/demand/" + id.toString();
    }

    private void saveFile(@Valid DemandEntity demand,
                          @RequestParam("file") MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            String file1, file2 = "";

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            file1 = file.getOriginalFilename();
            if(file1.lastIndexOf(".") != -1 && file1.lastIndexOf(".") != 0)
                // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
                file2 = file1.substring(file1.lastIndexOf(".")+1);
            String resultFilename = uuidFile + "." + file2;

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setLink(resultFilename);
            fileEntity.setDemand(demand);
            fileRepository.save(fileEntity);
            historyService.saveHistory("Загружен файл: " + file.getOriginalFilename(),demand);
        }
    }


}
