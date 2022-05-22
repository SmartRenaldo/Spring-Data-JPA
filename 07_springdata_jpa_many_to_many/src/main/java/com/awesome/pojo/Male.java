package com.awesome.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_male")
@Getter
@Setter
@NoArgsConstructor
public class Male {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Male(String name) {
        this.name = name;
    }

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
    @ManyToMany(cascade = CascadeType.ALL)
    /**
     * maintain middle table by JoinTable
     * joinColumns: set this table's foreign key
     * inverseJoinColumns: set associated table's foreign key
     */
    @JoinTable(
            name = "t_male_female",
            joinColumns = {@JoinColumn(name = "mid")},
            inverseJoinColumns = {@JoinColumn(name = "fid")}
    )
    private List<Female> females;
}
