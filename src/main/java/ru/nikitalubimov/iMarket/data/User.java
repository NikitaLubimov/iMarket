package ru.nikitalubimov.iMarket.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;


@Entity
@Table (name = "users")
@NoArgsConstructor
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "firstname")
    private String firstname;


}
