package io.bootify.my_app.service;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Notification;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.User;
import io.bootify.my_app.model.UserDTO;
import io.bootify.my_app.repos.CustomerRepository;
import io.bootify.my_app.repos.NotificationRepository;
import io.bootify.my_app.repos.RestaurantRepository;
import io.bootify.my_app.repos.UserRepository;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import io.bootify.my_app.domain.User;
import io.bootify.my_app.model.UserDTO;
import io.bootify.my_app.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final NotificationRepository notificationRepository;

    public UserService(final UserRepository userRepository,
            final CustomerRepository customerRepository,
            final RestaurantRepository restaurantRepository,
            final NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.notificationRepository = notificationRepository;
    }

    public UserDTO login(String email, String password) {
        User user = userRepository.login(email, password);
        if (user == null) {
            return null;
        }
        // Convert User entity to UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        // Add other fields as needed
        return userDTO;
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // public Integer create(UserDTO userDTO) {
    //     User user = new User();
    //     user.setEmail(userDTO.getEmail());
    //     user.setPassword(userDTO.getPassword());
    //     // Add other fields as needed
    //     return userRepository.save(user).getId();
    // }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("userId"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Integer userId) {
        return userRepository.findById(userId)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getUserId();
    }

    public void update(final Integer userId, final UserDTO userDTO) {
        final User user = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Integer userId) {
        userRepository.deleteById(userId);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    public ReferencedWarning getReferencedWarning(final Integer userId) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        final Customer userCustomer = customerRepository.findFirstByUser(user);
        if (userCustomer != null) {
            referencedWarning.setKey("user.customer.user.referenced");
            referencedWarning.addParam(userCustomer.getCustomerId());
            return referencedWarning;
        }
        final Restaurant userRestaurant = restaurantRepository.findFirstByUser(user);
        if (userRestaurant != null) {
            referencedWarning.setKey("user.restaurant.user.referenced");
            referencedWarning.addParam(userRestaurant.getRestaurantId());
            return referencedWarning;
        }
        final Notification userNotification = notificationRepository.findFirstByUser(user);
        if (userNotification != null) {
            referencedWarning.setKey("user.notification.user.referenced");
            referencedWarning.addParam(userNotification.getNotificationId());
            return referencedWarning;
        }
        return null;
    }

    

}
