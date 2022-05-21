package com.awesome.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_wife")
@Data
public class Wife {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY
            , orphanRemoval = true)
    // field name for foreign key
    @JoinColumn(name="hid")
    private Husband husband;
}
