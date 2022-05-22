package com.awesome.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_course")
@Setter
@Getter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Student student) {
        this.name = name;
        this.student = student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sid")
    private Student student;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentID=" + student.getId() +
                ", studentName=" + student.getName() +
                ", studentAge=" + student.getAge() +
                '}';
    }
}
