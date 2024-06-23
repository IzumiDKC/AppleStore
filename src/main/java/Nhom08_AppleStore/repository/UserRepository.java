package Nhom08_AppleStore.repository;

import Nhom08_AppleStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
