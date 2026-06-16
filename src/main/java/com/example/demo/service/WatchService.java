package com.example.demo.service;

import com.example.demo.dto.request.WatchRequest;
import com.example.demo.dto.response.WatchResponse;
import java.util.List;

public interface WatchService{

    WatchResponse createWatch(WatchRequest request);

    List<WatchResponse> getAllWatches();

    WatchResponse updateWatch(Long id, WatchRequest request);

    void deleteWatch(Long id);
}
