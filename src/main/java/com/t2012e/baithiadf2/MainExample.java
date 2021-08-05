package com.t2012e.baithiadf2;

import java.util.Scanner;

public class MainExample {
    public static void main(String[] args) {
        AdressBook adressBook = new AdressBook();
        while (true){

            System.out.println("-------AdressBook--------");
            System.out.println("1. Add new contact");
            System.out.println("2. Find by Name");
            System.out.println("3. display contact");
            System.out.println("4. exit");
            System.out.println("Plesae choice 1-4");
            System.out.println("-----------------------");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            boolean checkExit = false;
            switch (choice){
                case 1:
                    adressBook.addNewContact();
                    break;
                case 2:
                    adressBook.findAContactByName();
                    break;
                case 3:
                    adressBook.displayContact();
                    break;
                case 4:
                    System.out.println("bye bye");
                    checkExit = true;
                    break;
                default:
                    System.out.println("Please choose again");
                    break;
            }
            if (checkExit){
                break;
            }

        }
    }
}
