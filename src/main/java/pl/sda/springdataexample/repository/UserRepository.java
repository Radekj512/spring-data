package pl.sda.springdataexample.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.sda.springdataexample.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String firstName);
    List<User> findByFirstNameOrderByLastName(String firstName);

}
