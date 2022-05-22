package com.awesome.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_card")
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "passowrd")
    private String password;
}
