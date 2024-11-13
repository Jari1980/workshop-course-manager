package se.lexicon.course_manager.data.dao;



import se.lexicon.course_manager.data.sequencers.CourseSequencer;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

// TODO provide proper implementation.

public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int id = CourseSequencer.nextCourseId();
        Course course = new Course(id, courseName, startDate,weekDuration);
        courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        //return courses.stream().filter(c -> c.getCourseName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        List<Course> result = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseName().toLowerCase().contains(name.toLowerCase()));
            result.add(course);
        }
        return result;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        return courses.stream().filter(c -> c.getStartDate().isBefore(end)).collect(Collectors.toList());
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {

        return courses.stream().filter(c -> c.getStartDate().isAfter(start)).collect(Collectors.toList());
    }

    @Override
    public Collection<Course> findAll() {

        return new HashSet<>(courses);
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> result = new HashSet<>();
        for(Course course: courses){
            for(Student student : course.getStudents()){
                if(student.getId() == studentId){
                    result.add(course);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public void clear() {
        this.courses.clear();
    }
}
