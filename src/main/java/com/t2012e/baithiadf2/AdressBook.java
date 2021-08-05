package com.t2012e.baithiadf2;

import java.util.HashMap;
import java.util.Scanner;

public class AdressBook {

    private HashMap<String,Contact>  list = new HashMap<>();

    public void addNewContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("plesea enter name:");
        String name = scanner.nextLine();
        System.out.println("plesea enter phone:");
        String phone = scanner.nextLine();
        System.out.println("plesea enter id:");
        String id = scanner.nextLine();
        Contact contact = new Contact(name, phone);
        list.put(id,contact);
    }

    public void findAContactByName(){
        System.out.println("Plese enter name search:");
        Scanner scanner = new Scanner(System.in);
        String nameSearch = scanner.nextLine();
        for (String key: list.keySet()) {
            String name = list.get(key).getName();
            if (name.equals(nameSearch)){
                System.out.println("Address Book");
                System.out.println("Contact Name    |    phone    ");
                System.out.printf("%s   |   %s",list.get(key).getName(), list.get(key).getPhone());
                System.out.println("\n");
            }
        }
    }

    public void displayContact(){
        for (String key: list.keySet()) {
            Contact contact = list.get(key);
            System.out.println("Address Book");
            System.out.println("Contact Name    |    phone    ");
            System.out.printf("%s   |   %s",list.get(key).getName(), list.get(key).getPhone());
            System.out.println("\n");
        }
    }

}
