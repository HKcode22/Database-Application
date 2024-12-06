package io.bootify.my_app.rest;

import io.bootify.my_app.model.TableeDTO;
import io.bootify.my_app.service.TableeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/tablees", produces = MediaType.APPLICATION_JSON_VALUE)
public class TableeResource {

    private final TableeService tableeService;

    public TableeResource(final TableeService tableeService) {
        this.tableeService = tableeService;
    }

    @GetMapping
    public ResponseEntity<List<TableeDTO>> getAllTablees() {
        return ResponseEntity.ok(tableeService.findAll());
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<TableeDTO> getTablee(
            @PathVariable(name = "tableId") final Integer tableId) {
        return ResponseEntity.ok(tableeService.get(tableId));
    }

    @PostMapping
    public ResponseEntity<Integer> createTablee(@RequestBody @Valid final TableeDTO tableeDTO) {
        final Integer createdTableId = tableeService.create(tableeDTO);
        return new ResponseEntity<>(createdTableId, HttpStatus.CREATED);
    }

    @PutMapping("/{tableId}")
    public ResponseEntity<Integer> updateTablee(
            @PathVariable(name = "tableId") final Integer tableId,
            @RequestBody @Valid final TableeDTO tableeDTO) {
        tableeService.update(tableId, tableeDTO);
        return ResponseEntity.ok(tableId);
    }

    @DeleteMapping("/{tableId}")
    public ResponseEntity<Void> deleteTablee(
            @PathVariable(name = "tableId") final Integer tableId) {
        tableeService.delete(tableId);
        return ResponseEntity.noContent().build();
    }

}
