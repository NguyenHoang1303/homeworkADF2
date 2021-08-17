package com.t2012e.baithiadf2;

import java.util.HashMap;
import java.util.Scanner;

public class AdressBook {

    private HashMap<String, Contact> list = new HashMap<>();

    public void addNewContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("plesea enter name:");
        String name = scanner.nextLine();
        System.out.println("plesea enter phone:");
        String phone = scanner.nextLine();
        Contact contact = new Contact(name, phone);
        list.put(name, contact);
    }

    public void findAContactByName() {
        System.out.println("Plese enter name search:");
        Scanner scanner = new Scanner(System.in);
        String nameSearch = scanner.nextLine();
        Contact contact = list.get(nameSearch);
        System.out.println("Address Book");
        System.out.println("Contact Name    |    phone    ");
        System.out.printf("%s   |   %s", contact.getName(), contact.getPhone());
        System.out.println("\n");
    }

    public void displayContact() {
        for (String key : list.keySet()) {
            System.out.println("Address Book");
            System.out.println("Contact Name    |    phone    ");
            System.out.printf("%s   |   %s", list.get(key).getName(), list.get(key).getPhone());
            System.out.println("\n");
        }
    }

}
