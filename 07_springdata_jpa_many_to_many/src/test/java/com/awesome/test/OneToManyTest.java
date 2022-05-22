package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Course;
import com.awesome.pojo.Student;
import com.awesome.repositories.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToManyTest {

    @Autowired
    StudentRepository repository;

    @Test
    public void testC() {
        ArrayList<Course> courses = new ArrayList<>();
        Course course1 = new Course("JAVA");
        Course course2 = new Course("Spring");
        courses.add(course1);
        courses.add(course2);

        Student student = new Student();
        student.setName("Mike");
        student.setAge(27);
        student.setCourses(courses);

        repository.save(student);
    }

    @Test
    @Transactional(readOnly = true)
    public void testR() {
        Optional<Student> byId = repository.findById(1L);
        System.out.println(byId);
    }

    @Test
    public void testD() {
        repository.deleteById(1L);
    }

}
