package com.t2012e.demoFile;

import com.t2012e.lab3reflection.reflection.Student;

import java.io.*;
import java.util.ArrayList;

public class MainFile {

    static final String PATH_FILEREAD = "src/main/java/com/t2012e/demoFile/Fileread.txt";
    static final String PATH_FILEWRITE = "src/main/java/com/t2012e/demoFile/WriteFile.txt";
    static final String PATH_OBJ = "src/main/java/com/t2012e/demoFile/obj.dat";

    public static void main(String[] args) {
        readListObj();
    }

    static void readFileWithReader() {
        try (
                FileReader fileReader = new FileReader(PATH_FILEREAD);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String result = null;
            while ((result = bufferedReader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeFileReader() {
        try (
                FileWriter fileReader = new FileWriter(PATH_FILEWRITE);
                BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
        ) {
            bufferedWriter.write("hello Nguyen 12345");
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readDataWithInputStream() {
        try (
                FileInputStream fileInputStream = new FileInputStream(PATH_FILEREAD);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ) {
            int result;
            while ((result = bufferedInputStream.read()) != -1) {
                System.out.print((char) result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeDataWithInputStream() {
        File file = new File(PATH_FILEWRITE);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ) {
            String content = "hello Nguyen!";
            bufferedOutputStream.write(content.getBytes());
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    static void writeObjWithFile() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(PATH_OBJ);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            Student student = new Student("A001", "Nguyen");
            objectOutputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readDataWithObj() {
        try (
                FileInputStream fileInputStream = new FileInputStream(PATH_OBJ);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            boolean checkObj = true;
            while (checkObj) {
                try {
                    Student student = (Student) objectInputStream.readObject();
                    System.out.println(student.toString());
                } catch (EOFException eof) {
                    checkObj = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void writeListObjWithFile() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(PATH_OBJ);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            ArrayList<Student> list = new ArrayList<>();
            list.add(new Student("A001", "Nguyen"));
            list.add(new Student("A002", "Nguyen1"));
            list.add(new Student("A003", "Nguyen2"));
            objectOutputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readListObj() {
        try (
                FileInputStream fileInputStream = new FileInputStream(PATH_OBJ);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            ArrayList<Student> list = (ArrayList<Student>) objectInputStream.readObject();
            for (Student student: list
                 ) {
                System.out.println(student.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
