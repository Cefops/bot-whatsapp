package br.com.cefops.SendMenssage.storage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.cefops.SendMenssage.readExcel.Service.ReadExcell;
import br.com.cefops.SendMenssage.storage.exception.StorageFileNotFoundException;
import br.com.cefops.SendMenssage.storage.services.StorageCustomResponse;
import br.com.cefops.SendMenssage.storage.services.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller
@RequestMapping("file")
public class FileUploadController {

    private final StorageService storageService;
    private final ReadExcell excell;
    private final StorageCustomResponse customResponseService;


    @Autowired

    public FileUploadController(StorageService storageService, ReadExcell excell, StorageCustomResponse customResponseService) {
        this.storageService = storageService;
        this.excell = excell;
        this.customResponseService = customResponseService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";

    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {
        Map<String, Object> urlPersonalizda = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        urlPersonalizda.put("fileName", file.getOriginalFilename());
        ObjectNode objectNode = mapper.createObjectNode();

        String path = storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!" + "jsonTeste" + urlPersonalizda);
//        excell.readExcll(path);
        return "redirect:/file";
    }

    @PostMapping("/api-upload")
    public ResponseEntity<?> handleFileUploadApi(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                                                 RedirectAttributes redirectAttributes) throws IOException {


        String path = storageService.store(file);


        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        String token = UUID.randomUUID().toString().replace("-","");
        int totalFiles = excell.readExcll(path,token,file.getOriginalFilename()).size();

        Map<String, Object> data = new HashMap<>();
        data.put("fileName", file.getOriginalFilename());
        data.put("totalOfMessages", totalFiles);
        data.put("baseUrl", baseUrl);
        data.put("token", token);
        var customResponse = customResponseService.sendResponseBody(data);
        return ResponseEntity.ok(customResponse);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
