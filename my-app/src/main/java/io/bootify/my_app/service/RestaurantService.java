package io.bootify.my_app.service;

import io.bootify.my_app.domain.Category;
import io.bootify.my_app.domain.Menu;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.Review;
import io.bootify.my_app.domain.Tablee;
import io.bootify.my_app.domain.User;
import io.bootify.my_app.model.RestaurantDTO;
import io.bootify.my_app.repos.CategoryRepository;
import io.bootify.my_app.repos.MenuRepository;
import io.bootify.my_app.repos.ReservationRepository;
import io.bootify.my_app.repos.RestaurantRepository;
import io.bootify.my_app.repos.ReviewRepository;
import io.bootify.my_app.repos.TableeRepository;
import io.bootify.my_app.repos.UserRepository;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ReservationRepository reservationRepository;
    private final TableeRepository tableeRepository;
    private final MenuRepository menuRepository;
    private final ReviewRepository reviewRepository;

    public RestaurantService(final RestaurantRepository restaurantRepository,
            final UserRepository userRepository, final CategoryRepository categoryRepository,
            final ReservationRepository reservationRepository,
            final TableeRepository tableeRepository, final MenuRepository menuRepository,
            final ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
        this.tableeRepository = tableeRepository;
        this.menuRepository = menuRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<RestaurantDTO> findAll() {
        final List<Restaurant> restaurants = restaurantRepository.findAll(Sort.by("restaurantId"));
        return restaurants.stream()
                .map(restaurant -> mapToDTO(restaurant, new RestaurantDTO()))
                .toList();
    }

    public RestaurantDTO get(final Integer restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> mapToDTO(restaurant, new RestaurantDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RestaurantDTO restaurantDTO) {
        final Restaurant restaurant = new Restaurant();
        mapToEntity(restaurantDTO, restaurant);
        return restaurantRepository.save(restaurant).getRestaurantId();
    }

    public void update(final Integer restaurantId, final RestaurantDTO restaurantDTO) {
        final Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(restaurantDTO, restaurant);
        restaurantRepository.save(restaurant);
    }

    public void delete(final Integer restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    private RestaurantDTO mapToDTO(final Restaurant restaurant, final RestaurantDTO restaurantDTO) {
        restaurantDTO.setRestaurantId(restaurant.getRestaurantId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setEmail(restaurant.getEmail());
        restaurantDTO.setStreetAddress(restaurant.getStreetAddress());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setState(restaurant.getState());
        restaurantDTO.setZipCode(restaurant.getZipCode());
        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDTO.setOpeningHours(restaurant.getOpeningHours());
        restaurantDTO.setCreatedAt(restaurant.getCreatedAt());
        restaurantDTO.setUser(restaurant.getUser() == null ? null : restaurant.getUser().getUserId());
        restaurantDTO.setCategory(restaurant.getCategory() == null ? null : restaurant.getCategory().getCategoryId());
        return restaurantDTO;
    }

    private Restaurant mapToEntity(final RestaurantDTO restaurantDTO, final Restaurant restaurant) {
        restaurant.setName(restaurantDTO.getName());
        restaurant.setEmail(restaurantDTO.getEmail());
        restaurant.setStreetAddress(restaurantDTO.getStreetAddress());
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setState(restaurantDTO.getState());
        restaurant.setZipCode(restaurantDTO.getZipCode());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setOpeningHours(restaurantDTO.getOpeningHours());
        restaurant.setCreatedAt(restaurantDTO.getCreatedAt());
        final User user = restaurantDTO.getUser() == null ? null : userRepository.findById(restaurantDTO.getUser())
                .orElseThrow(() -> new NotFoundException("User not found"));
        restaurant.setUser(user);
        final Category category = restaurantDTO.getCategory() == null ? null : categoryRepository.findById(restaurantDTO.getCategory())
                .orElseThrow(() -> new NotFoundException("category not found"));
        restaurant.setCategory(category);
        return restaurant;
    }

    public ReferencedWarning getReferencedWarning(final Integer restaurantId) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(NotFoundException::new);
        final Reservation restaurantReservation = reservationRepository.findFirstByRestaurant(restaurant);
        if (restaurantReservation != null) {
            referencedWarning.setKey("restaurant.reservation.restaurant.referenced");
            referencedWarning.addParam(restaurantReservation.getReservationId());
            return referencedWarning;
        }
        final Tablee restaurantTablee = tableeRepository.findFirstByRestaurant(restaurant);
        if (restaurantTablee != null) {
            referencedWarning.setKey("restaurant.tablee.restaurant.referenced");
            referencedWarning.addParam(restaurantTablee.getTableId());
            return referencedWarning;
        }
        final Menu restaurantMenu = menuRepository.findFirstByRestaurant(restaurant);
        if (restaurantMenu != null) {
            referencedWarning.setKey("restaurant.menu.restaurant.referenced");
            referencedWarning.addParam(restaurantMenu.getMenuId());
            return referencedWarning;
        }
        final Review restaurantReview = reviewRepository.findFirstByRestaurant(restaurant);
        if (restaurantReview != null) {
            referencedWarning.setKey("restaurant.review.restaurant.referenced");
            referencedWarning.addParam(restaurantReview.getReviewId());
            return referencedWarning;
        }
        return null;
    }
}
