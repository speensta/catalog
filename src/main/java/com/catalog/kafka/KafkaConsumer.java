package com.catalog.kafka;

import com.catalog.entity.CatalogEntity;
import com.catalog.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "test-topic")
    public void listener(String message) {
        log.info("kafka Message {} ", message);
        Map<Object, Object> map = new HashMap<>();

        try {
            map = objectMapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        CatalogEntity entity = catalogRepository.findByProductId((String)map.get("productId"));
        if(entity != null) {
            entity.setStock(entity.getStock() - (Integer)map.get("qty"));
        }
    }

}
