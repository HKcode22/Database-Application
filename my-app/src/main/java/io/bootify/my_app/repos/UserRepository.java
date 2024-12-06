package io.bootify.my_app.repos;

import io.bootify.my_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User login(@Param("email") String email, @Param("password") String password);
    User findByEmail(String email);

}
