package ru.nikitalubimov.iMarket.data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

//    @ManyToMany (targetEntity = ru.nikitalubimov.iMarket.data.User.class)
//    @JoinTable (
//            name = "users_products",
//            joinColumns = @JoinColumn (name = "product_id"),
//            inverseJoinColumns = @JoinColumn (name = "user_id")
//    )
//    private List<User> users;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", cost=" + cost +
//                ", users=" + users +
//                '}';
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
