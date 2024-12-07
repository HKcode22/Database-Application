package io.bootify.my_app.rest;

import io.bootify.my_app.model.MenuDTO;
import io.bootify.my_app.service.MenuService;
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
@RequestMapping(value = "/api/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuResource {

    private final MenuService menuService;

    public MenuResource(final MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable(name = "menuId") final Integer menuId) {
        return ResponseEntity.ok(menuService.get(menuId));
    }

    @PostMapping
    public ResponseEntity<Integer> createMenu(@RequestBody @Valid final MenuDTO menuDTO) {
        final Integer createdMenuId = menuService.create(menuDTO);
        return new ResponseEntity<>(createdMenuId, HttpStatus.CREATED);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Integer> updateMenu(@PathVariable(name = "menuId") final Integer menuId,
            @RequestBody @Valid final MenuDTO menuDTO) {
        menuService.update(menuId, menuDTO);
        return ResponseEntity.ok(menuId);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable(name = "menuId") final Integer menuId) {
        menuService.delete(menuId);
        return ResponseEntity.noContent().build();
    }

}
