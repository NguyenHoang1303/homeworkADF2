package com.t2012e.lab2;

import java.io.*;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainLab2 {

    public static final String REGEX = "(GV.+ )\\|( .+)\\|(.+ )\\|( .+)";

    public static void main(String[] args) {
        HashMap<String, TeacherBody> listTB = new HashMap<>();

        String pathInput = "src/main/java/com/t2012e/lab2/input.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(pathInput);
            Scanner scanner = new Scanner(fileInputStream);
            String id;
            String name;
            LocalDate date;
            String line;
            int number;
            Pattern pattern = Pattern.compile(REGEX);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    TeacherBody teacherBody = new TeacherBody();
                    id = matcher.group(1).trim();
                    teacherBody.setId(id);
                    name =matcher.group(2).trim();
                    teacherBody.setName(name);
                    date = Util.stringToDate(matcher.group(3).trim());
                    teacherBody.setDate(date);
                    number = Integer.parseInt(matcher.group(4).trim());
                    teacherBody.setNumberOfCakes(number);
                    if (listTB.containsKey(teacherBody.getId())){
                        TeacherBody tcDuplicate = listTB.get(teacherBody.getId());
                        tcDuplicate.setNumberOfCakes(tcDuplicate.getNumberOfCakes() + teacherBody.getNumberOfCakes());
                    } else{
                        listTB.put(teacherBody.getId(),teacherBody);
                    }
                }
            }
            List<TeacherBody> listResult = MainLab2.sort(listTB);
            FileWriter fw = new FileWriter("src/main/java/com/t2012e/lab2/output.txt");
            for (int i = 0; i < 3; i++) {
                TeacherBody tc = listResult.get(i);
                System.out.println(tc);
                fw.write(tc.getId() + " | " + tc.getName() + " | " + tc.getNumberOfCakes() +"\n");
            }
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TeacherBody> sort (HashMap hashMap){
        List<TeacherBody> list = new  ArrayList<TeacherBody>(hashMap.values());
        Collections.sort(list, new Comparator<TeacherBody>() {
            @Override
            public int compare(TeacherBody o1, TeacherBody o2) {
                int numO1 = o1.getNumberOfCakes();
                int numO2 = o2.getNumberOfCakes();
                if (numO1 > numO2) return -(numO1 - numO2);
                else return o1.getDate().compareTo(o2.getDate());
            }
        });
        return list;
    }
}
