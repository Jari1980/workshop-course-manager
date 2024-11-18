package se.lexicon.course_manager.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {

    Course testObject;
    Student student;

    @BeforeEach
    void setUp() {
        testObject = new Course(1);
        student = new Student(1);
    }

    @Test
    void enrollStudent() {
        testObject.enrollStudent(student);
        assertTrue(testObject.getStudents().contains(student));
    }
}
