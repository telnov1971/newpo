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
import ru.omel.newpo.repository.FileRepository;
import ru.omel.newpo.service.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class DemandController {
    private final DemandService demandService;
    private final GarantService garantService;
    private final PlanService planService;
    private final PriceService priceService;
    private final RegionService regionService;
    private final SendService sendService;
    private final StatusService statusService;
    private final SafeService safeService;
    private final VoltService voltService;
    private final HistoryService historyService;
    private final FileService fileService;
    private final FileRepository fileRepository;
    @Value("${upload.path.windows}")
    private String uploadPathWindows;
    @Value("${upload.path.linux}")
    private String uploadPathLinux;

    @Autowired
    public DemandController(DemandService demandService
            , GarantService garantService
            , PlanService planService
            , PriceService priceService
            , RegionService regionService
            , SendService sendService
            , StatusService statusService
            , SafeService safeService
            , VoltService voltService
            , HistoryService historyService
            , FileService fileService
            , FileRepository fileRepository
            ) {
        this.demandService = demandService;
        this.garantService = garantService;
        this.planService = planService;
        this.priceService = priceService;
        this.regionService = regionService;
        this.sendService = sendService;
        this.statusService = statusService;
        this.safeService = safeService;
        this.voltService = voltService;
        this.historyService = historyService;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
    }

    @GetMapping("/")
    public String main(Model model,
                       @AuthenticationPrincipal UserEntity user){
        var demandEntities = demandService.findAllByUser(user);
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
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        List<GarantEntity> garantEntities = garantService.findAll();
        List<PriceEntity> priceEntities = priceService.findAll();
        List<PlanEntity> planEntities = planService.findAll();
        List<SendEntity> sendEntities = sendService.findAll();
        List<StatusEntity> statusEntities = statusService.findAll();
        List<RegionEntity> regionEntities = regionService.findAll();
        List<FileEntity> fileEntities = fileService.findAllByDemand(demandEntity);
        List<HistoryEntity> historyEntities = historyService.findAllByDemand(demandEntity);
        model.addAttribute("garants", garantEntities);
        model.addAttribute("prices", priceEntities);
        model.addAttribute("plans", planEntities);
        model.addAttribute("sends",sendEntities);
        model.addAttribute("statuses",statusEntities);
        model.addAttribute("regions", regionEntities);
        model.addAttribute("demand", demandEntity);
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        model.addAttribute("files",fileEntities);
        model.addAttribute("user", user);
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
        if (!file.getOriginalFilename().isEmpty()) {
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
        List<GarantEntity> garantEntities = garantService.findAll();
        List<PriceEntity> priceEntities = priceService.findAll();
        List<PlanEntity> planEntities = planService.findAll();
        List<SendEntity> sendEntities = sendService.findAll();
        List<StatusEntity> statusEntities = statusService.findAll();
        List<RegionEntity> regionEntities = regionService.findAll();
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        model.addAttribute("garants", garantEntities);
        model.addAttribute("prices", priceEntities);
        model.addAttribute("plans", planEntities);
        model.addAttribute("sends",sendEntities);
        model.addAttribute("statuses",statusEntities);
        model.addAttribute("regions", regionEntities);
        return "demand";
    }

    @PostMapping("/new")
    public String saveNewDemand(
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
