package com.awesome.repositories;

import com.awesome.pojo.Course;
import com.awesome.pojo.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    /**
     * condition only relates to primary key for param
     * @param student
     * @return
     */
    List<Course> findByStudent(Student student);
}
