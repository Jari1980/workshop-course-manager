package se.lexicon.course_manager.model;

import se.lexicon.course_manager.data.sequencers.CourseSequencer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// TODO implement model
public class Course implements Serializable {
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private Set<Student> students;

    public Course(){
        this.students = new HashSet<>();
    }

    public Course(int id){

        this.id = id;
        this.students = new HashSet<>();
    }

    public Course(int id, String courseName, LocalDate startDate, int weekDuration){
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = new HashSet<>();
    }

    public Set<Student> getStudents() {
        return students;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getId() {
        return id;
    }
    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student student){
        if(student == null || students.contains(student)){
            return false;
        }
        return students.add(student);
    }
    public boolean unrollStudent(Student student){
        return students.remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }
}
