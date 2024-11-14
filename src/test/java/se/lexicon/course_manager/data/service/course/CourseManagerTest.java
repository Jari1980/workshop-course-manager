package se.lexicon.course_manager.data.service.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager.data.dao.CourseDao;
import se.lexicon.course_manager.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager.data.dao.StudentDao;
import se.lexicon.course_manager.data.sequencers.CourseSequencer;
import se.lexicon.course_manager.data.service.converter.Converters;
import se.lexicon.course_manager.data.service.converter.ModelToDto;
import se.lexicon.course_manager.dto.forms.CreateCourseForm;
import se.lexicon.course_manager.dto.views.CourseView;
import se.lexicon.course_manager.model.Course;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {

    @Autowired
    private CourseService testObject;

    @Autowired
    private CourseDao courseDao;



    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(courseDao);
    }

    // TODO Write your tests here


    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }

    @Test
    void create() {
        Course course = courseDao.createCourse("TestCourseName", LocalDate.of(2024,12,12), 4);
        assertNotNull(course);
    }


    @Test
    void findById() {
        Course course = courseDao.createCourse("TestCourseName", LocalDate.of(2024,12,12), 4);
        Course result = courseDao.findById(1);
        assertNotNull(result);
        Course result2 = courseDao.findById(2);
        assertNull(result2);
    }
}
