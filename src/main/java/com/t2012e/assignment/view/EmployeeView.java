package com.t2012e.assignment.view;

import com.t2012e.assignment.controller.EmployeeController;

import java.util.Scanner;

public class EmployeeView {
    private EmployeeController employeeController;
    private Scanner scanner;

    public EmployeeView() {
        this.employeeController = new EmployeeController();
        this.scanner = new Scanner(System.in);
    }

    public void generateMenu() {
        while(true){
            System.out.println("Employee Manager - FPT Academy");
            System.out.println("----------------------");
            System.out.println("1. Đăng kí tài khoản Employee.");
            System.out.println("2. Đăng nhập.");
            System.out.println("3. Exit.");
            System.out.println("----------------------");
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    employeeController.creatEmployee();
                    break;
                case 2:
                    employeeController.login();
                    break;
                case 3:
                    System.out.println("Bye bye.");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1 - 3.");
                    break;
            }
            if(choice == 3){
                break;
            }
            System.out.println("nhấn phím Enter để tiếp tục");
            scanner.nextLine();
        }
    }
}
