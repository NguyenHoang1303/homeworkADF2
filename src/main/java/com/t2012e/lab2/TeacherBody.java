package com.t2012e.lab2;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class TeacherBody
        implements Comparator<TeacherBody>
{
    private String id;
    private String name;
    private LocalDate date;
    private int numberOfCakes;

    public TeacherBody() {
    }

    public TeacherBody(String id, String name, LocalDate date, int numberOfCakes) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.numberOfCakes = numberOfCakes;
    }

    @Override
    public int compare(TeacherBody o1, TeacherBody o2) {
        int numO1 = o1.numberOfCakes;
        int numO2 = o2.numberOfCakes;
        if (numO1 != numO2) return (numO1 - numO2);
        else return o1.getDate().compareTo(o2.getDate());

    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TeacherBody that = (TeacherBody) o;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

    @Override
    public String toString() {
        return "TeacherBody{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", numberOfCakes=" + numberOfCakes +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfCakes() {
        return numberOfCakes;
    }

    public void setNumberOfCakes(int numberOfCakes) {
        this.numberOfCakes = numberOfCakes;
    }
}
