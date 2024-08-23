package br.com.jandernery.precojusto.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import br.com.jandernery.precojusto.application.dto.DuckDTO;
import br.com.jandernery.precojusto.application.dto.DuckRecord;
import br.com.jandernery.precojusto.domain.entities.DuckEntity;
import br.com.jandernery.precojusto.infra.exceptions.DuckNotFoundException;
import br.com.jandernery.precojusto.infra.repositories.DuckRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

@Service
public class DuckService {
    private final DuckRepository duckRepository;

    @Autowired
    public DuckService(DuckRepository duckRepository) {
        this.duckRepository = duckRepository;
    }

    public void createDuck(DuckRecord duck) {
        if (duck.motherId() != null) {
            DuckEntity mother = getMother(duck);
            DuckEntity duckEntity = new DuckEntity();
            duckEntity.setName(duck.name());
            duckEntity.setMother(mother);
            duckRepository.save(duckEntity);
        } else {
            DuckEntity duckEntity = new DuckEntity();
            duckEntity.setName(duck.name());
            duckEntity.setMother(null);
            duckRepository.save(duckEntity);
        }

        // getMother(duck);
        // DuckEntity duckEntity = new DuckEntity();
        // duckEntity.setName(duck.name());
        // duckEntity.setIsMother(duck.isMother());
        // if (duck.isMother()) {
        // duckEntity.setMother(null);
        // } else {
        // DuckEntity mother = duckRepository.findByName(duck.mother());
        // duckEntity.setMother(mother);
        // }
        // duckRepository.save(duckEntity);

    }

    public DuckEntity getMother(DuckRecord duck) {
        DuckEntity duckEntity = duckRepository.findByIdUUid(duck.motherId());
        System.out.println(duckEntity.getName());

        if (duckEntity == null) {
            throw new DuckNotFoundException(HttpStatus.NOT_FOUND, "Duck Mother not found", this.getClass().getName());
        }

        return duckEntity;
    }

    public void findAllDucks(HttpServletResponse response) throws Exception {

        List<DuckEntity> ducks = duckRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook(); // Para XSSF, use XSSFWorkbook
        HSSFSheet sheet = workbook.createSheet("Relatorio"); // Para XSSF, use XSSFSheet
        HSSFRow row = sheet.createRow(0);

        // Criando o cabeçalho da planilha
        row.createCell(0).setCellValue("Nome");
        row.createCell(1).setCellValue("Status");
        row.createCell(2).setCellValue("Cliente");
        row.createCell(3).setCellValue("Tipo do Cliente");
        row.createCell(4).setCellValue("Valor");

        // Preenchendo os dados na planilha
        int dataRowIndex = 1;

        for (DuckEntity duck : ducks) {
            row = sheet.createRow(dataRowIndex++);
            row.createCell(0).setCellValue(duck.getName());
            row.createCell(1).setCellValue("Vendido");
            row.createCell(2).setCellValue("Ze do Pato");
            row.createCell(3).setCellValue("com desconto");
            row.createCell(4).setCellValue("R$ 100,00");
        }
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        }

        workbook.close();
        // return duckRepository.findAllDucks();
    }

    public DuckEntity getDucksByMother(UUID motherId) {
        DuckEntity duck = duckRepository.findById(motherId)
                .orElseThrow(() -> new DuckNotFoundException(HttpStatus.NOT_FOUND, "Mother Duck not found",
                        this.getClass().getName()));

        return duck;
    }

    public DuckEntity getDuckById(UUID duckId) {
        DuckEntity duck = duckRepository.findById(duckId)
                .orElseThrow(() -> new DuckNotFoundException(HttpStatus.NOT_FOUND, "Mother Duck not found",
                        this.getClass().getName()));

        // ModelMapper modelMapper = new ModelMapper();

        // System.out.println(duck.getName());
        // DuckDTO duckDTO = modelMapper.map(duck, DuckDTO.class);

        return duck;
    }

}
