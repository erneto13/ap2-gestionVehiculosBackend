package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor
public class MediaController {

    public final StorageService service;
    private final HttpServletRequest request;

    @PostMapping("upload")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = service.store(file);
        String host = request.getRequestURL().toString().replace(
                request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/src/main/resources/static/media/")
                .path(path)
                .toUriString();
        return Map.of("url", url);
    }

    @PostMapping("upload-files")
    public Map<String, Object> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            String path = service.store(file);
            String host = request.getRequestURL().toString().replace(
                    request.getRequestURI(), "");
            String url = ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/src/main/resources/static/media/")
                    .path(path)
                    .toUriString();
            urls.add(url);
        }

        return Map.of("urls", urls);
    }


    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = service.loadResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }

    @PostMapping("load-images")
    public ResponseEntity<?> loadFiles(@RequestBody Map<String, List<String>> request) {
        List<String> filenames = request.get("filenames");
        if (filenames == null || filenames.isEmpty()) {
            return ResponseEntity.badRequest().body("No filenames provided");
        }

        List<Map<String, String>> files = filenames.stream().map(filename -> {
            try {
                Resource file = service.loadResource(filename);
                String contentType = Files.probeContentType(file.getFile().toPath());

                return Map.of(
                        "filename", filename,
                        "resource", file.getURL().toString(),
                        "contentType", contentType
                );
            } catch (IOException e) {
                return Map.of("filename", filename, "error", "File not found or inaccessible");
            }
        }).toList();

        return ResponseEntity.ok(files);
    }

}
