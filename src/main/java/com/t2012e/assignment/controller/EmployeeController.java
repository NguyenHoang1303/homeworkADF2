package com.t2012e.assignment.controller;

import com.t2012e.assignment.entity.Employee;
import com.t2012e.assignment.model.EmployeeModel;

import java.util.Scanner;

public class EmployeeController {
    private Scanner scanner;
    private EmployeeModel employeeModel;

    public EmployeeController() {
        this.scanner = new Scanner(System.in);
        this.employeeModel = new EmployeeModel();
    }

    public void creatEmployee() {
        while (true) {
            System.out.println("Vui lòng nhập thông tin đăng ký.");
            System.out.println("Ten: ");
            String ten = scanner.nextLine();
            System.out.println("Địa chỉ: ");
            String diaChi = scanner.nextLine();
            System.out.println("Email: ");
            String email = scanner.nextLine();
            System.out.println("Tài khoản: ");
            String taiKhoan = scanner.nextLine();
            System.out.println("Mật khẩu: ");
            String matKhau = scanner.nextLine();
            Employee employee = new Employee(ten, diaChi, email, taiKhoan, matKhau);
            if (employeeModel.register(employee)) {
                System.out.println("Tạo tài khoản thành công!");
                printfEmployeeByFomart();
                System.out.println(employee.toString());
                break;
            } else {
                System.out.println("Vui lòng điền lại thông tin!");
            }
        }
    }

    public void login() {
        int choice = 1;
        while (choice == 1) {
            System.out.println("Vui lòng nhập tài khoản:");
            String taiKhoan = scanner.nextLine();
            System.out.println("Vui lòng nhập mật khẩu");
            String matKhau = scanner.nextLine();
            if (employeeModel.login(taiKhoan, matKhau) != null) {
                Employee employee = employeeModel.login(taiKhoan, matKhau);
                printfEmployeeByFomart();
                System.out.println(employee.toString());
                break;
            } else {
                System.out.println("Vui lòng kiểm tra lại tài khoản hoặc mật khẩu");
                System.out.println("1. nhập lại thông tin!");
                System.out.println("2. thoát");
                System.out.println("Vui lòng chọn 1-2");
                choice = scanner.nextInt();
                scanner.nextLine();
            }
        }
    }

    public void printfEmployeeByFomart(){
        System.out.printf("%5s%10s%5s | %5s%10s%5s | %5s%10s%18s | %5s%10s%15s | %5s%10s%5s | %5s%10s%5s | %5s%10s%5s | %5s%5s%5s \n",
                "", "ten", "",
                "", "diaChi", "",
                "", "email", "",
                "", "taiKhoan", "",
                "", "matKhau", "",
                "", "ngayTao", "",
                "", "ngayUpdate", "",
                "", "TrangThai", "");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
