package pl.sda.springdataexample.service;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import pl.sda.springdataexample.entity.Order;
import pl.sda.springdataexample.entity.Product;
import pl.sda.springdataexample.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.ws.ServiceMode;
import java.math.BigDecimal;

@Service
public class UserService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void persist(){
        System.out.println("----====Persist user====----");
        User u1 = new User("Jan", "Kowalski");
        User u2 = new User("Przemek", "Nowak");
        User u3 = new User("John", "Snow");
        User u4 = new User("Ania", "Antczak");
        User u5 = new User("Romek", "Roman");
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);
        em.persist(u5);
    }

    @Transactional
    public void persistOneWithOrder(){
        User u1 = new User("Jan", "Kowalski");
        Order o1 = new Order(u1);
        em.persist(u1);
        em.persist(o1);
        u1.getOrders().add(o1);
    }

    @Transactional
    public void persisManyToMany(){
        System.out.println("--- MANY TO MANY ---");
        Product p1 = new Product("Produkt1", BigDecimal.valueOf(100));
        Product p2 = new Product("Produkt2", BigDecimal.valueOf(100));
        Product p3 = new Product("Produkt2", BigDecimal.valueOf(100));
        Product p4 = new Product("Produkt2", BigDecimal.valueOf(100));
        Product p5 = new Product("Produkt2", BigDecimal.valueOf(100));
        Product p6 = new Product("Produkt2", BigDecimal.valueOf(100));
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.persist(p6);
        User user = em.find(User.class, 1L);
        Order o1 = new Order(user);
        o1.getProducts().add(p1);
        o1.getProducts().add(p2);
        o1.getProducts().add(p3);
        o1.getProducts().add(p4);
        o1.getProducts().add(p5);
        o1.getProducts().add(p6);
        em.persist(o1);
    }
    @Transactional
    public void remove(){
        Order order = em.find(Order.class, 2L);
        System.out.println("FIND ORDER");
        order.getProducts().remove(0);
    }

    @Transactional
    public void update(){
        System.out.println("------UPDATE MANY TO MANY--------");
        Order order = em.find(Order.class, 2L);
        Product product = new Product("Test1", BigDecimal.valueOf(4001));
        em.persist(product);
        order.getProducts().add(product);
        product.getOrders().add(order);
    }

    @Transactional
    public void deleteOneToMany(){
        User user = new User("test", "test");
        em.persist(user);
        Order o1 = new Order(user);
        Order o2 = new Order(user);
        Order o3 = new Order(user);
        Order o4 = new Order(user);
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.persist(o4);
        user.getOrders().add(o1);
        user.getOrders().add(o2);
        user.getOrders().add(o3);
        user.getOrders().add(o4);

        System.out.println("=================================================");
        Order remove = user.getOrders().remove(0);
        em.remove(remove);
    }
}
