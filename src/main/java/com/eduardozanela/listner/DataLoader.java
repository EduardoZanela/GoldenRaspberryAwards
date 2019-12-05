package com.eduardozanela.listner;

import java.io.File;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.eduardozanela.dto.CSVAwardsDTO;
import com.eduardozanela.entity.AwardsEntity;
import com.eduardozanela.repository.AwardsRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Component
public class DataLoader {

    @Autowired
    private AwardsRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @EventListener
    public void appReady(ApplicationReadyEvent event) throws IOException {
        Resource resource = new ClassPathResource("data/movielist.csv");
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
        CsvMapper mapper = new CsvMapper();
        File file = resource.getFile();
        MappingIterator<CSVAwardsDTO> readValues = mapper.reader(CSVAwardsDTO.class).with(bootstrapSchema).readValues(file);
        for(CSVAwardsDTO csv : readValues.readAll()) {
            repo.save(modelMapper.map(csv, AwardsEntity.class));
        }
    }
}
