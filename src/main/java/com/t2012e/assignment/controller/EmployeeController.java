package com.t2012e.assignment.controller;

import com.t2012e.assignment.entity.Employee;
import com.t2012e.assignment.model.EmployeeModel;
import com.t2012e.assignment.util.PrintfEmployeeByFormat;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeController {
    private Scanner scanner;
    private EmployeeModel employeeModel;

    public EmployeeController() {
        this.scanner = new Scanner(System.in);
        this.employeeModel = new EmployeeModel();
    }

    public void creatEmployee() {
        String ten;
        String diaChi;
        String email;
        String taiKhoan;
        String matKhau;
        while (true) {
            System.out.println("Vui lòng nhập thông tin đăng ký.");
            System.out.println("Ten: ");
            ten = scanner.nextLine();
            System.out.println("Địa chỉ: ");
            diaChi = scanner.nextLine();
            System.out.println("Email: ");
            email = scanner.nextLine();
            System.out.println("Tài khoản: ");
            taiKhoan = scanner.nextLine();
            System.out.println("Mật khẩu: ");
            matKhau = scanner.nextLine();
            Employee employee = new Employee(ten, diaChi, email, taiKhoan, matKhau);
            if (employeeModel.register(employee)) {
                System.out.println("Tạo tài khoản thành công!");
                PrintfEmployeeByFormat.printf();
                System.out.println(employee.toString());
                break;
            } else {
                System.out.println("Vui lòng điền lại thông tin!");
            }
        }
    }

    public void checkExistAccount() {
        System.out.println("Vui lòng nhập tài khoản:");
        String taiKhoan = scanner.nextLine();
        try {
            if (employeeModel.checkExistAccount(taiKhoan)) {
                System.out.println("Tài khoản đã tồn tại");
            } else {
                System.out.println("Tài khoản không tồn tại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        String taiKhoan;
        String matKhau;
        int choice = 1;
        while (choice == 1) {
            System.out.println("Vui lòng nhập tài khoản:");
            taiKhoan = scanner.nextLine();
            System.out.println("Vui lòng nhập mật khẩu");
            matKhau = scanner.nextLine();
            try {
                if (employeeModel.login(taiKhoan, matKhau) != null) {
                    Employee employee = employeeModel.login(taiKhoan, matKhau);
                    PrintfEmployeeByFormat.printf();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
