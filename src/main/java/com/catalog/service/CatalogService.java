package com.catalog.service;

import com.catalog.dto.CatalogDto;
import com.catalog.entity.CatalogEntity;
import com.catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public List<CatalogEntity> findAllCatalog() {
        return catalogRepository.findAll();
    }



}
