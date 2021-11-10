package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
