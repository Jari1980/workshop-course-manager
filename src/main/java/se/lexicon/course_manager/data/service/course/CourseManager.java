package se.lexicon.course_manager.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager.data.dao.CourseDao;
import se.lexicon.course_manager.data.dao.StudentDao;
import se.lexicon.course_manager.data.service.converter.Converters;
import se.lexicon.course_manager.dto.forms.CreateCourseForm;
import se.lexicon.course_manager.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager.dto.views.CourseView;
import se.lexicon.course_manager.model.Course;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// TODO provide proper implementation.
@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {
        Course course = courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());
        return converters.courseToCourseView(course);
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        Course course = courseDao.findById(form.getId());
        if(course == null){
            return null;
        }
        course.setCourseName(form.getCourseName());
        course.setStartDate(form.getStartDate());
        course.setWeekDuration(form.getWeekDuration());
        return converters.courseToCourseView(course);
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName)
    {
        List<CourseView> result = new ArrayList<>();
        for(Course element : courseDao.findAll()){
            result.add(converters.courseToCourseView(element));
        }
        return result;
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {

        return null;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        return null;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        return false;
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        return false;
    }

    @Override
    public CourseView findById(int id) {
        return null;
    }

    @Override
    public List<CourseView> findAll() {
        return null;
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        return null;
    }

    @Override
    public boolean deleteCourse(int id) {
        return false;
    }
}
