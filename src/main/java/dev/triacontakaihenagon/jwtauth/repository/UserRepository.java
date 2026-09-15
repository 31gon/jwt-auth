package dev.triacontakaihenagon.jwtauth.repository;


import dev.triacontakaihenagon.jwtauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
