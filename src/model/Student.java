package model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    private int id;
    private String name;
    private int age;
    private float gpa;
    private boolean isInternational;
    private String gradeLevel;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects;

    public Student() {} // Required for JAXB

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public float getGpa() { return gpa; }
    public boolean isInternational() { return isInternational; }
    public String getGradeLevel() { return gradeLevel; }
    public List<Subject> getSubjects() { return subjects; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGpa(float gpa) { this.gpa = gpa; }
    public void setInternational(boolean international) { isInternational = international; }
    public void setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", GPA=" + gpa + ", Subjects=" + subjects + "]";
    }
}