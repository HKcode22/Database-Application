package io.bootify.my_app.rest;

import io.bootify.my_app.model.RestaurantDTO;
import io.bootify.my_app.service.RestaurantService;
import io.bootify.my_app.util.ReferencedException;
import io.bootify.my_app.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantResource {

    private final RestaurantService restaurantService;

    public RestaurantResource(final RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurant(
            @PathVariable(name = "restaurantId") final Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.get(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Integer> createRestaurant(
            @RequestBody @Valid final RestaurantDTO restaurantDTO) {
        final Integer createdRestaurantId = restaurantService.create(restaurantDTO);
        return new ResponseEntity<>(createdRestaurantId, HttpStatus.CREATED);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Integer> updateRestaurant(
            @PathVariable(name = "restaurantId") final Integer restaurantId,
            @RequestBody @Valid final RestaurantDTO restaurantDTO) {
        restaurantService.update(restaurantId, restaurantDTO);
        return ResponseEntity.ok(restaurantId);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable(name = "restaurantId") final Integer restaurantId) {
        final ReferencedWarning referencedWarning = restaurantService.getReferencedWarning(restaurantId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        restaurantService.delete(restaurantId);
        return ResponseEntity.noContent().build();
    }

}
