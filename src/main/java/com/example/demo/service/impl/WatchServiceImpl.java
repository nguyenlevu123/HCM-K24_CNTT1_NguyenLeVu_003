package com.example.demo.service.impl;

import com.example.demo.dto.request.WatchRequest;
import com.example.demo.dto.response.WatchResponse;
import com.example.demo.entity.Watch;
import com.example.demo.exception.WatchNotFoundException;
import com.example.demo.repository.WatchRepository;
import com.example.demo.service.WatchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WatchServiceImpl implements WatchService{

    private final WatchRepository watchRepository;

    @Override
    @Transactional
    public WatchResponse createWatch(WatchRequest request){
        Watch watch = Watch.builder().modelName(request.getModelName()).brand(request.getBrand()).price(request.getPrice())
                .movementType(request.getMovementType()).status(request.getStatus()).isDeleted(false).build();

        return toResponse(watchRepository.save(watch));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WatchResponse> getAllWatches(){
        return watchRepository.findAllByIsDeletedFalse()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public WatchResponse updateWatch(Long id, WatchRequest request){
        Watch watch = findWatchById(id);
        watch.setModelName(request.getModelName());
        watch.setBrand(request.getBrand());
        watch.setPrice(request.getPrice());
        watch.setMovementType(request.getMovementType());
        watch.setStatus(request.getStatus());

        return toResponse(watchRepository.save(watch));
    }

    @Override
    @Transactional
    public void deleteWatch(Long id){
        Watch watch = findWatchById(id);
        watch.setIsDeleted(true);
        watchRepository.save(watch);
    }

    private Watch findWatchById(Long id){
        return watchRepository.findById(id).filter(watch -> Boolean.FALSE.equals(watch.getIsDeleted()))
                .orElseThrow(() -> new WatchNotFoundException("Không tìm thấy đồng hồ với ID:" + id));
    }

    private WatchResponse toResponse(Watch watch){
        return WatchResponse.builder()
                .id(watch.getId())
                .modelName(watch.getModelName())
                .brand(watch.getBrand())
                .price(watch.getPrice())
                .movementType(watch.getMovementType())
                .status(watch.getStatus())
                .isDeleted(watch.getIsDeleted())
                .build();
    }
}
