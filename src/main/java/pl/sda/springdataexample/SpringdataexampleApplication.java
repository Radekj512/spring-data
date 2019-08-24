package pl.sda.springdataexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.sda.springdataexample.entity.User;
import pl.sda.springdataexample.repository.UserRepository;
import pl.sda.springdataexample.service.UserService;

import java.util.List;

@SpringBootApplication
public class SpringdataexampleApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringdataexampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.persist();
//        userService.persistOneWithOrder();
//        userService.persisManyToMany();
//        userService.remove();
//        userService.update();
//        userService.deleteOneToMany();

        /* ---- Spring DATA ---- */
        userRepository.save(new User("Jan", "kowalski"));
        userRepository.save(new User("Jan", "asd"));
        userRepository.save(new User("Jan", "qwe"));
        userRepository.save(new User("Jan", "gyk"));

        PageRequest pageRequest = PageRequest.of(2, 10);
        Page<User> all = userRepository.findAll(pageRequest);

        List<User> users = userRepository.findByFirstNameOrderByLastName("Jan");
        users.forEach(System.out::println);
    }
}
