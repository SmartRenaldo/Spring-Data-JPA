package com.awesome.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_husband")
@Data
public class Husband {
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
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY
            , orphanRemoval = true, mappedBy = "husband"/*, optional = false*/)
    // field name for foreign key
    @JoinColumn(name="wid")
    private Wife wife;
}
