package com.catalog.cotroller;

import com.catalog.dto.CatalogDto;
import com.catalog.service.CatalogService;
import com.catalog.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogRestController {

    private final CatalogService catalogService;

    private final ModelMapper modelMapper;

    @GetMapping("/heath-check")
    public String check(HttpServletRequest request) {
        return String.format("health-check : {}", request.getServerPort());
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> findAllCatalog() {

        List<ResponseCatalog> list = new ArrayList();
        catalogService.findAllCatalog().forEach(c -> {
            list.add(modelMapper.map(c, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
