package se.lexicon.course_manager.data.service.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager.data.dao.StudentDao;
import se.lexicon.course_manager.data.sequencers.StudentSequencer;
import se.lexicon.course_manager.data.service.converter.ModelToDto;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {

    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(studentDao);
    }



    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }

    @Test
    void create() {
        Student student = studentDao.createStudent("Test Test", "test@test.se", "TestAdress");
        assertNotNull(student);
    }

    @Test
    void findById() {
        Student student = studentDao.createStudent("Test Test", "test@test.se", "TestAdress");
        Student test = studentDao.findById(1);
        assertNotNull(test);
    }
}
