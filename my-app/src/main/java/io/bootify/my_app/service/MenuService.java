package io.bootify.my_app.service;

import io.bootify.my_app.domain.Menu;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.model.MenuDTO;
import io.bootify.my_app.repos.MenuRepository;
import io.bootify.my_app.repos.RestaurantRepository;
import io.bootify.my_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(final MenuRepository menuRepository,
            final RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MenuDTO> findAll() {
        final List<Menu> menus = menuRepository.findAll(Sort.by("menuId"));
        return menus.stream()
                .map(menu -> mapToDTO(menu, new MenuDTO()))
                .toList();
    }

    public MenuDTO get(final Integer menuId) {
        return menuRepository.findById(menuId)
                .map(menu -> mapToDTO(menu, new MenuDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final MenuDTO menuDTO) {
        final Menu menu = new Menu();
        mapToEntity(menuDTO, menu);
        return menuRepository.save(menu).getMenuId();
    }

    public void update(final Integer menuId, final MenuDTO menuDTO) {
        final Menu menu = menuRepository.findById(menuId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(menuDTO, menu);
        menuRepository.save(menu);
    }

    public void delete(final Integer menuId) {
        menuRepository.deleteById(menuId);
    }

    private MenuDTO mapToDTO(final Menu menu, final MenuDTO menuDTO) {
        menuDTO.setMenuId(menu.getMenuId());
        menuDTO.setItemName(menu.getItemName());
        menuDTO.setItemPrice(menu.getItemPrice());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setRestaurant(menu.getRestaurant() == null ? null : menu.getRestaurant().getRestaurantId());
        return menuDTO;
    }

    private Menu mapToEntity(final MenuDTO menuDTO, final Menu menu) {
        menu.setItemName(menuDTO.getItemName());
        menu.setItemPrice(menuDTO.getItemPrice());
        menu.setDescription(menuDTO.getDescription());
        final Restaurant restaurant = menuDTO.getRestaurant() == null ? null : restaurantRepository.findById(menuDTO.getRestaurant())
                .orElseThrow(() -> new NotFoundException("restaurant not found"));
        menu.setRestaurant(restaurant);
        return menu;
    }

}
