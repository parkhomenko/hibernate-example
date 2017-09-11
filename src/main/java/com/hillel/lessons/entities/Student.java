package com.hillel.lessons.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "studentByName",
                query = "from Student s where s.firstname = :firstname"
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "students",
                query = "select * from students",
                resultClass = Student.class
        )
})
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private int age;

    @Embedded
    private StudentAddress studentAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Exam> exams;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentAddress getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(StudentAddress studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
