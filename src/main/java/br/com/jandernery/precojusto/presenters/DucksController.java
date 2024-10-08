package br.com.jandernery.precojusto.presenters;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jandernery.precojusto.application.dto.DuckDTO;
import br.com.jandernery.precojusto.application.dto.DuckRecord;
import br.com.jandernery.precojusto.application.services.DuckService;
import br.com.jandernery.precojusto.domain.entities.DuckEntity;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/ducks")
public class DucksController {

    @Autowired
    public DuckService duckService;

    @PostMapping
    public void createDuck(@RequestBody DuckRecord duck) {
        duckService.createDuck(duck);
    }

    // ResponseEntity<List<DuckEntity>>
    @GetMapping
    public void findAll(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ducks.xls";
        response.setHeader(headerKey, headerValue);

        duckService.findAllDucks(response);

        // return ResponseEntity.ok(duckService.findAll());
    }

    @GetMapping("/duck/{duckId}")
    public ResponseEntity<DuckEntity> getDucksByMother(@PathVariable UUID duckId) {
        DuckEntity duck = duckService.getDuckById(duckId);
        return ResponseEntity.ok(duck);
    }

    @GetMapping("/mother/{motherId}")
    public ResponseEntity<DuckEntity> getDuckById(@PathVariable UUID motherId) {
        DuckEntity duck = duckService.getDucksByMother(motherId);
        return ResponseEntity.ok(duck);
    }

}
