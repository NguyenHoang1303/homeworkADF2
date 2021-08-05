package com.t2012e.lab3reflection.reflection;


import com.t2012e.lab3reflection.reflection.myannotion.Column;
import com.t2012e.lab3reflection.reflection.myannotion.Id;
import com.t2012e.lab3reflection.reflection.myannotion.Table;

import java.io.Serializable;

@Table(name = "sinhvien")
public class Student implements Serializable {
    @Id(autoIncrement = true)
    public String id;
    @Column(filedName = "tenSV", filedType = "VARCHAR(250)")
    public String name;


    public Student() {
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
