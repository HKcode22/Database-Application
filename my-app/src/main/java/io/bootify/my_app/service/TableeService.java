package io.bootify.my_app.service;

import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.Tablee;
import io.bootify.my_app.model.TableeDTO;
import io.bootify.my_app.repos.RestaurantRepository;
import io.bootify.my_app.repos.TableeRepository;
import io.bootify.my_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TableeService {

    private final TableeRepository tableeRepository;
    private final RestaurantRepository restaurantRepository;

    public TableeService(final TableeRepository tableeRepository,
            final RestaurantRepository restaurantRepository) {
        this.tableeRepository = tableeRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<TableeDTO> findAll() {
        final List<Tablee> tablees = tableeRepository.findAll(Sort.by("tableId"));
        return tablees.stream()
                .map(tablee -> mapToDTO(tablee, new TableeDTO()))
                .toList();
    }

    public TableeDTO get(final Integer tableId) {
        return tableeRepository.findById(tableId)
                .map(tablee -> mapToDTO(tablee, new TableeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final TableeDTO tableeDTO) {
        final Tablee tablee = new Tablee();
        mapToEntity(tableeDTO, tablee);
        return tableeRepository.save(tablee).getTableId();
    }

    public void update(final Integer tableId, final TableeDTO tableeDTO) {
        final Tablee tablee = tableeRepository.findById(tableId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tableeDTO, tablee);
        tableeRepository.save(tablee);
    }

    public void delete(final Integer tableId) {
        tableeRepository.deleteById(tableId);
    }

    private TableeDTO mapToDTO(final Tablee tablee, final TableeDTO tableeDTO) {
        tableeDTO.setTableId(tablee.getTableId());
        tableeDTO.setCapacity(tablee.getCapacity());
        tableeDTO.setStatus(tablee.getStatus());
        tableeDTO.setRestaurant(tablee.getRestaurant() == null ? null : tablee.getRestaurant().getRestaurantId());
        return tableeDTO;
    }

    private Tablee mapToEntity(final TableeDTO tableeDTO, final Tablee tablee) {
        tablee.setCapacity(tableeDTO.getCapacity());
        tablee.setStatus(tableeDTO.getStatus());
        final Restaurant restaurant = tableeDTO.getRestaurant() == null ? null : restaurantRepository.findById(tableeDTO.getRestaurant())
                .orElseThrow(() -> new NotFoundException("restaurant not found"));
        tablee.setRestaurant(restaurant);
        return tablee;
    }

}
