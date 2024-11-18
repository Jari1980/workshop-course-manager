package se.lexicon.course_manager.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager.dto.views.CourseView;
import se.lexicon.course_manager.model.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }



    @Test
    void coursesToCourseViews() {
        Collection<Course> test = new ArrayList<>();
        test.add(new Course(1));
        test.add(new Course(2));
        test.add(new Course());

        List<CourseView> result = testObject.coursesToCourseViews(test);
        assertNotNull(result);
    }


}
