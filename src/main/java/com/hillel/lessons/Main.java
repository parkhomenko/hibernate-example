package com.hillel.lessons;

import com.hillel.lessons.entities.Role;
import com.hillel.lessons.entities.Student;
import com.hillel.lessons.entities.User;
import com.hillel.lessons.reports.ExamReport;
import com.hillel.lessons.reports.Reports;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            NativeQuery query = session.createNativeQuery("select version();");
            String result = (String) query.getSingleResult();
            System.out.println(result);

            Transaction tx = session.beginTransaction();

            /*
            Student student = new Student();
            student.setFirstname("John");
            student.setLastname("Wayne");
            student.setAge(35);

            session.save(student);*/

            /*Student student = session.load(Student.class, (long) 1);
            System.out.println(student);

            student.setAge(student.getAge() + 10);*/

            /*
            User user = new User();
            user.setLogin("test");
            user.setPassword("test123");

            Role role1 = new Role();
            role1.setName("admin");

            Role role2 = new Role();
            role2.setName("customer");

            Set<Role> roles = new HashSet<>();
            roles.add(role1);
            roles.add(role2);

            user.setRoles(roles);

            session.save(user);
            */

            /*
            Reports reports = new Reports();
            List<ExamReport> exams = reports.getExamResults("John", "Wayne", session);
            */

            Query query1 = session.createNamedQuery("studentByName");
            query1.setParameter("firstname", "John");

            Student student = (Student) query1.getSingleResult();

            NativeQuery<Student> nativeQuery = session.
                    createNativeQuery("select * from students")
                    .addEntity(Student.class);

            Student student1 = nativeQuery.getSingleResult();

            tx.commit();
        }

        sessionFactory.close();
    }
}
