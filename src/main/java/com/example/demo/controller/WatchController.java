package com.example.demo.controller;

import com.example.demo.dto.request.WatchRequest;
import com.example.demo.dto.response.WatchResponse;
import com.example.demo.service.WatchService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watches")
public class WatchController{

    private final WatchService watchService;

    @PostMapping
    public ResponseEntity<WatchResponse> createWatch(@Valid @RequestBody WatchRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(watchService.createWatch(request));
    }

    @GetMapping
    public ResponseEntity<List<WatchResponse>> getAllWatches(){
        return ResponseEntity.ok(watchService.getAllWatches());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchResponse> updateWatch(
            @PathVariable Long id,
            @Valid @RequestBody WatchRequest request
    ){
        return ResponseEntity.ok(watchService.updateWatch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatch(@PathVariable Long id) {
        watchService.deleteWatch(id);
        return ResponseEntity.noContent().build();
    }
}
