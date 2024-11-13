package se.lexicon.course_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.course_manager.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager.model.Student;

import java.util.Collection;
import java.util.HashSet;

@SpringBootApplication
public class CourseManagerAssignmentApplication {

    public static void main(String[] args) {
        //SpringApplication.run(CourseManagerAssignmentApplication.class, args);
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository student = new StudentCollectionRepository(students);

        student.createStudent("Jean", "Email", "Adress");
        student.createStudent("Jean2", "Email2", "Adress2");
        student.createStudent("Jean3", "Email3", "Adress3");


        for(Student element : students){
            System.out.println(element);
        }
        System.out.println("----------");
        System.out.println(student.findByEmailIgnoreCase("Email"));
        System.out.println("----------------");
        System.out.println(student.findByNameContains("Je"));
    }

}
