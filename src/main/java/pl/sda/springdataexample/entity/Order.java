package pl.sda.springdataexample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

@Entity
@Table(name = "ORDER_")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime creationTime = LocalDateTime.now();

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public Order(User user) {
        this.user = user;
    }
}
