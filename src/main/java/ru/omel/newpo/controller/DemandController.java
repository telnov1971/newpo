package ru.omel.newpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.omel.newpo.entity.*;
import ru.omel.newpo.repository.DemandRepository;
import ru.omel.newpo.repository.FileRepository;
import ru.omel.newpo.service.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class DemandController {
    @Autowired
    private DemandService demandService;
    @Autowired
    private GarantService garantService;
    @Autowired
    private PlanService planService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SendService sendService;
    @Autowired
    private StatusService statusService;
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

    @GetMapping("/")
    public String main(Model model,
                       @AuthenticationPrincipal UserEntity user){
        List<DemandEntity> demandEntities = demandService.findAllByUser(user);
        model.addAttribute("url", "/main");
        model.addAttribute("demands", demandEntities);
        return "main";
    }

    @GetMapping("/demand/{id}")
    public String edit(@AuthenticationPrincipal UserEntity user,
                       Model model,
                       @PathVariable("id") Long id) {
        DemandEntity demandEntity = demandService.findById(id);
        if(!demandEntity.getUser().getId().equals(user.getId())) {
            return "redirect:/";
        }
        List<GarantEntity> garantEntities = garantService.findAll();
        List<PlanEntity> planEntities = planService.findAll();
        List<PriceEntity> priceEntities = priceService.findAll();
        List<RegionEntity> regionEntities = regionService.findAll();
        List<SendEntity> sendEntities = sendService.findAll();
        List<StatusEntity> statusEntities = statusService.findAll();
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        List<FileEntity> fileEntities = fileService.findAllByDemand(demandEntity);
        List<HistoryEntity> historyEntities = historyService.findAllByDemand(demandEntity);
        model.addAttribute("demand", demandEntity);
        model.addAttribute("garant", garantEntities);
        model.addAttribute("plan", planEntities);
        model.addAttribute("price", priceEntities);
        model.addAttribute("region", regionEntities);
        model.addAttribute("send", sendEntities);
        model.addAttribute("status", statusEntities);
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        model.addAttribute("files",fileEntities);
        model.addAttribute("history", historyEntities);
        return "demand";
    }


    @PostMapping("/demand/{id}")
    public String saveEdit(@AuthenticationPrincipal UserEntity user,
                           @Valid DemandEntity demandEntity,
                           BindingResult bindingResult,
                           @RequestParam(name = "file1", required = false) MultipartFile file1,
                           @RequestParam(name = "file2", required = false) MultipartFile file2,
                           @RequestParam(name = "file3", required = false) MultipartFile file3,
                           @RequestParam(name = "file4", required = false) MultipartFile file4,
                           @RequestParam(name = "file5", required = false) MultipartFile file5,
                           @RequestParam(name = "file6", required = false) MultipartFile file6,
                           @RequestParam(name = "file7", required = false) MultipartFile file7,
                           @RequestParam(name = "file8", required = false) MultipartFile file8,
                           @RequestParam(name = "file9", required = false) MultipartFile file9,
                           @RequestParam(name = "file10", required = false) MultipartFile file10) throws ValidationException {
        if (bindingResult.hasErrors()) {
            return "redirect:/demand/" + demandEntity.getId();
        }
        try {
            demandService.saveDemand(demandEntity);
            saveFile(demandEntity, file1);
            saveFile(demandEntity, file2);
            saveFile(demandEntity, file3);
            saveFile(demandEntity, file4);
            saveFile(demandEntity, file5);
            saveFile(demandEntity, file6);
            saveFile(demandEntity, file7);
            saveFile(demandEntity, file8);
            saveFile(demandEntity, file9);
            saveFile(demandEntity, file10);
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    private void saveFile(DemandEntity demand,
                          MultipartFile file) throws IOException {
        String uploadPath, osName;

        try {
        if(file == null || file.isEmpty()) return ;
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
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/new")
    public String newDemand(Model model,
                            @AuthenticationPrincipal UserEntity user){
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        return "demand";
    }

    @PostMapping("/new")
    public String saveNewDemand(Model model,
                           @AuthenticationPrincipal UserEntity user,
                           @Valid DemandEntity demandEntity,
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
        try {
            demandService.newDemand(demandEntity, user);
            saveFile(demandEntity, file1);
            saveFile(demandEntity, file2);
            saveFile(demandEntity, file3);
            saveFile(demandEntity, file4);
            saveFile(demandEntity, file5);
            saveFile(demandEntity, file6);
            saveFile(demandEntity, file7);
            saveFile(demandEntity, file8);
            saveFile(demandEntity, file9);
            saveFile(demandEntity, file10);
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

}
