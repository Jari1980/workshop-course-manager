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
import se.lexicon.course_manager.model.Student;


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

        List<CourseView> result = new ArrayList<>();
        for(Course element : courseDao.findByDateBefore(end)){
            result.add((converters.courseToCourseView(element)));
        }
        return result;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {

        List<CourseView> result = new ArrayList<>();
        for(Course element : courseDao.findByDateAfter(start)){
            result.add((converters.courseToCourseView(element)));
        }
        return result;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);

        return course.enrollStudent(student);
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);

        return course.unrollStudent(student);
    }

    @Override
    public CourseView findById(int id) {

        return converters.courseToCourseView(courseDao.findById(id));
    }

    @Override
    public List<CourseView> findAll() {

        return converters.coursesToCourseViews(courseDao.findAll());
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        List<CourseView> result = new ArrayList<>();
        for(Course course : courseDao.findAll()){
            for(Student stud : course.getStudents()){
                if(stud.getId() == studentId){
                    result.add(converters.courseToCourseView(course));
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteCourse(int id) {
        Course course = courseDao.findById(id);

        return courseDao.removeCourse(course);
    }
}
