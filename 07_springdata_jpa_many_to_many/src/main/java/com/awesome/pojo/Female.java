package com.awesome.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_female")
@Getter
@Setter
@NoArgsConstructor
public class Female {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Female(String name) {
        this.name = name;
    }

    public Female(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Female{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Male> males;
}
