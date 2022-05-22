package com.awesome.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    /**
     * cascade: set association
     * orphanRemoval: if the associated attribute is null or updated
     *                , and if orphanRemoval = true,
     *                the old associated data will be deleted
     * optional: associated attribute cannot be null
     * mappedBy: Have the other party enforce the foreign key constraint,
     *                the value equles to the other party's field name for this attribute
     * default FetchType: LAZY
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="sid")
    private List<Course> courses;
}
