package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Course;
import com.awesome.pojo.Student;
import com.awesome.repositories.CourseRepository;
import com.awesome.repositories.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToOneTest {
    @Autowired
    CourseRepository repository;

    @Test
    public void testC() {
        Student student = new Student();
        student.setName("John");
        student.setAge(11);

        List<Course> courses = new ArrayList<>();

        Course course1 = new Course("Maven", student);
        Course course2 = new Course("Gradle", student);

        courses.add(course1);
        courses.add(course2);

        repository.saveAll(courses);
    }

    @Test
    public void testR() {
        Student student = new Student();
        student.setId(1L);

        System.out.println(repository.findByStudent(student));
    }

    @Test
    public void testD() {
        Student student = new Student();
        student.setId(1L);
        List<Course> courses = repository.findByStudent(student);
        repository.deleteAll(courses);
    }
}
