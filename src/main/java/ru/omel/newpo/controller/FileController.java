package ru.omel.newpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.FileEntity;
import ru.omel.newpo.entity.HistoryEntity;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.repository.FileRepository;
import ru.omel.newpo.service.DemandService;
import ru.omel.newpo.service.FileService;
import ru.omel.newpo.service.HistoryService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private DemandService demandService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/fileList/{id}")
    public String safeFile(@PathVariable("id") Long id,
                           Model model){
        DemandEntity demandEntity = demandService.findById(id);
        List<FileEntity> fileEntities = fileService.findAllByDemand(demandEntity);

        model.addAttribute("files",fileEntities);
        model.addAttribute("demand", demandEntity);
        return "parts/fileList";
    }

    @PostMapping("/fileList/{id}")
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
