package se.lexicon.course_manager.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager.dto.views.CourseView;
import se.lexicon.course_manager.dto.views.StudentView;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// TODO Convert model -> view & models -> views

@Component
public class ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        if(student == null){
            return null;
        }
        return new StudentView(student.getId(), student.getName(), student.getEmail(), student.getAddress());
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        if(course == null){
            return null;
        }
        List<StudentView> courses = studentsToStudentViews(course.getStudents());
        return new CourseView(course.getId(), course.getCourseName(), course.getStartDate(), course.getWeekDuration(), courses);
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        if(courses == null || courses.isEmpty()){
            return new ArrayList<>();
        }
        List<CourseView> courseView = new ArrayList<>();
        for(Course element : courses){
            courseView.add(courseToCourseView(element));
        }
        return courseView;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        if(students == null || students.isEmpty()){
            return new ArrayList<>();
        }
        List<StudentView> studentView = new ArrayList<>();
        for(Student element : students){
            studentView.add(studentToStudentView(element));
        }
        return studentView;
    }
}
