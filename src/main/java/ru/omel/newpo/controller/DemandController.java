package ru.omel.newpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.omel.newpo.entity.*;
import ru.omel.newpo.repository.FileRepository;
import ru.omel.newpo.service.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(name="/newpo")
public class DemandController {
    @Autowired
    private DemandService demandService;
    //private final DemandRepository demandRepository;
    @Autowired
    private SafeService safeService;
    @Autowired
    private VoltService voltService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;
    @Value("${upload.path.windows}")
    private String uploadPathWindows;
    @Value("${upload.path.linux}")
    private String uploadPathLinux;
    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @GetMapping("/")
    public String main(Model model,
                       @AuthenticationPrincipal UserEntity user){
        List<DemandEntity> demandEntities = demandService.findAllByUser(user);
        model.addAttribute("url", "/main");
        model.addAttribute("demands", demandEntities);
        model.addAttribute("contextPath", contextPath);
        return "main";
    }

    @GetMapping("/demand/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        DemandEntity demandEntity = demandService.findById(id);
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        List<FileEntity> fileEntities = fileService.findAllByDemand(demandEntity);
        List<HistoryEntity> historyEntities = historyService.findAllByDemand(demandEntity);
        model.addAttribute("demand", demandEntity);
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        model.addAttribute("files",fileEntities);
        model.addAttribute("history", historyEntities);
        model.addAttribute("contextPath", contextPath);
        return "demand";
    }


    @PostMapping("/demand/{id}")
    public String saveEdit(@AuthenticationPrincipal UserEntity user,
                           @RequestParam Long id,
                           @RequestParam String object,
                           @RequestParam String adress,
                           @RequestParam(defaultValue = "0.0") Double powerCur,
                           @RequestParam(defaultValue = "0.0") Double powerDec,
                           @RequestParam String volt,
                           @RequestParam String safe,
                           @RequestParam(name = "file1", required = false) MultipartFile file1,
                           @RequestParam(name = "file2", required = false) MultipartFile file2,
                           @RequestParam(name = "file3", required = false) MultipartFile file3,
                           @RequestParam(name = "file4", required = false) MultipartFile file4,
                           @RequestParam(name = "file5", required = false) MultipartFile file5,
                           @RequestParam(name = "file6", required = false) MultipartFile file6,
                           @RequestParam(name = "file7", required = false) MultipartFile file7,
                           @RequestParam(name = "file8", required = false) MultipartFile file8,
                           @RequestParam(name = "file9", required = false) MultipartFile file9,
                           @RequestParam(name = "file10", required = false) MultipartFile file10){
        demandService.saveDemand(id, object, adress, powerCur, powerDec, volt, safe, user);
        DemandEntity demandEntity = demandService.findById(id);
        if(file1!=null && !file1.isEmpty()){
            try { saveFile(demandEntity, file1);} catch (IOException | ValidationException e) {}}
        if(file2!=null && !file2.isEmpty()){
            try { saveFile(demandEntity, file2);} catch (IOException | ValidationException e) {}}
        if(file3!=null && !file3.isEmpty()){
            try { saveFile(demandEntity, file3);} catch (IOException | ValidationException e) {}}
        if(file4!=null && !file4.isEmpty()){
            try { saveFile(demandEntity, file4);} catch (IOException | ValidationException e) {}}
        if(file5!=null && !file5.isEmpty()){
            try { saveFile(demandEntity, file5);} catch (IOException | ValidationException e) {}}
        if(file6!=null && !file6.isEmpty()){
            try { saveFile(demandEntity, file6);} catch (IOException | ValidationException e) {}}
        if(file7!=null && !file7.isEmpty()){
            try { saveFile(demandEntity, file7);} catch (IOException | ValidationException e) {}}
        if(file8!=null && !file8.isEmpty()){
            try { saveFile(demandEntity, file8);} catch (IOException | ValidationException e) {}}
        if(file9!=null && !file9.isEmpty()){
            try { saveFile(demandEntity, file9);} catch (IOException | ValidationException e) {}}
        if(file10!=null && !file10.isEmpty()){
            try { saveFile(demandEntity, file10);} catch (IOException | ValidationException e) {}}
        return "redirect:/";
    }

    private void saveFile(@Valid DemandEntity demand,
                          MultipartFile file) throws IOException, ValidationException {
        String uploadPath, osName;
        uploadPath = new String();
        osName = System.getProperty("os.name");
        if(osName.contains("Windows")) uploadPath = uploadPathWindows;
        if(osName.contains("Linux")) uploadPath = uploadPathLinux;

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
            fileEntity.setLoad1c(false);
            fileRepository.save(fileEntity);
            historyService.saveHistory("Загружен файл: " + file.getOriginalFilename(),demand);
            demand.setRewrite(true);
            demandService.saveDemand(demand);
        }
    }


    @GetMapping("/new")
    public String newDemand(Model model,
                            @AuthenticationPrincipal UserEntity user){
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        model.addAttribute("contextPath", contextPath);
        return "demand";
    }

    @PostMapping("/new")
    public String saveNewDemand(Model model,
                           @AuthenticationPrincipal UserEntity user,
                           @RequestParam String object,
                           @RequestParam String adress,
                           @RequestParam Double powerCur,
                           @RequestParam Double powerDec,
                           @RequestParam String volt,
                           @RequestParam String safe,
                           @RequestParam(name = "file1", required = false) MultipartFile file1,
                           @RequestParam(name = "file2", required = false) MultipartFile file2,
                           @RequestParam(name = "file3", required = false) MultipartFile file3,
                           @RequestParam(name = "file4", required = false) MultipartFile file4,
                           @RequestParam(name = "file5", required = false) MultipartFile file5,
                           @RequestParam(name = "file6", required = false) MultipartFile file6,
                           @RequestParam(name = "file7", required = false) MultipartFile file7,
                           @RequestParam(name = "file8", required = false) MultipartFile file8,
                           @RequestParam(name = "file9", required = false) MultipartFile file9,
                           @RequestParam(name = "file10", required = false) MultipartFile file10){
        DemandEntity demandEntity = new DemandEntity();
        demandEntity = demandService.newDemand(object, adress, powerCur, powerDec, volt, safe, user);
        if(file1!=null && !file1.isEmpty()){
            try { saveFile(demandEntity, file1);} catch (IOException | ValidationException e) {}}
        if(file2!=null && !file2.isEmpty()){
            try { saveFile(demandEntity, file2);} catch (IOException | ValidationException e) {}}
        if(file3!=null && !file3.isEmpty()){
            try { saveFile(demandEntity, file3);} catch (IOException | ValidationException e) {}}
        if(file4!=null && !file4.isEmpty()){
            try { saveFile(demandEntity, file4);} catch (IOException | ValidationException e) {}}
        if(file5!=null && !file5.isEmpty()){
            try { saveFile(demandEntity, file5);} catch (IOException | ValidationException e) {}}
        if(file6!=null && !file6.isEmpty()){
            try { saveFile(demandEntity, file6);} catch (IOException | ValidationException e) {}}
        if(file7!=null && !file7.isEmpty()){
            try { saveFile(demandEntity, file7);} catch (IOException | ValidationException e) {}}
        if(file8!=null && !file8.isEmpty()){
            try { saveFile(demandEntity, file8);} catch (IOException | ValidationException e) {}}
        if(file9!=null && !file9.isEmpty()){
            try { saveFile(demandEntity, file9);} catch (IOException | ValidationException e) {}}
        if(file10!=null && !file10.isEmpty()){
            try { saveFile(demandEntity, file10);} catch (IOException | ValidationException e) {}}
        return "redirect:/";
    }

}
