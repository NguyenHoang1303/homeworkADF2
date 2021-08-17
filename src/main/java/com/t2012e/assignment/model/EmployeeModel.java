package com.t2012e.assignment.model;

import com.t2012e.assignment.entity.Employee;
import com.t2012e.assignment.util.ConnectionHelper;
import com.t2012e.assignment.util.HandlerTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeModel {
    //    {
//        register(new Employee("nguyen","Hung Yen", "nguyen@gmail.com", "nguyenhy", "123"));
//        register(new Employee("nguyen1","Hung Yen1", "nguyen1@gmail.com", "nguyenhy1", "123"));
//        register(new Employee("nguyen2","Hung Yen2", "nguyen2@gmail.com", "nguyenhy2", "123"));
//        register(new Employee("nguyen3","Hung Yen3", "nguyen3@gmail.com", "nguyenhy3", "123"));
//        register(new Employee("nguyen4","Hung Yen4", "nguyen4@gmail.com", "nguyenhy4", "123"));
//        register(new Employee("nguyen5","Hung Yen5", "nguyen5@gmail.com", "nguyenhy5", "123"));
//        register(new Employee("nguyen6","Hung Yen6", "nguyen6@gmail.com", "nguyenhy6", "123"));
//        register(new Employee("nguyen7","Hung Yen7", "nguyen6@gmail.com", "nguyenhy7", "123"));
//        register(new Employee("nguyen8","Hung Yen8", "nguyen7@gmail.com", "nguyenhy8", "123"));
//        register(new Employee("nguyen9","Hung Yen9", "nguyen8@gmail.com", "nguyenhy9", "123"));
//    }
    public boolean register(Employee employee) {
        try {
            if (checkExistAccount(employee.getTaiKhoan())) {
                System.out.println("Tài khoản đã tồn tại");
                return false;
            } else {
                Connection cnn = ConnectionHelper.getConnection();
                if (cnn == null) {
                    System.err.println("không thể kết nối tới database");
                    return false;
                }
                PreparedStatement pp = cnn.prepareStatement
                        ("insert into employees" +
                                "(ten, diaChi, email, taiKhoan, matKhau, ngayTao, ngayUpdate, trangThai)" +
                                "values (?, ?, ?, ?, ?, ?, ?, ?)");
                pp.setString(1, employee.getTen());
                pp.setString(2, employee.getDiaChi());
                pp.setString(3, employee.getEmail());
                pp.setString(4, employee.getTaiKhoan());
                pp.setString(5, employee.getMatKhau());
                pp.setString(6, HandlerTime.convertDateToString(employee.getNgayTao()));
                pp.setString(7, HandlerTime.convertDateToString(employee.getNgayUpdate()));
                pp.setInt(8, employee.getTrangThai());
                pp.execute();
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("loi");
        }
        return false;
    }

    public boolean checkExistAccount(String account) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            if (cnn == null) {
                throw new SQLException("không thể kết nối tới database!");
            }
            PreparedStatement pp = cnn.prepareStatement("select * from employees where taiKhoan = ? ");
            pp.setString(1, account);
            ResultSet rs = pp.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employee login(String account, String password) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            if (cnn == null) {
                throw new SQLException("không thể kết nối tới database!");
            }
            PreparedStatement pp = cnn.prepareStatement("select * from employees where taiKhoan = ? and matKhau = ?");
            pp.setString(1, account);
            pp.setString(2, password);
            ResultSet rs = pp.executeQuery();
            if (rs.next()) {
                String name = rs.getString("ten");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String taiKhoan = rs.getString("taiKhoan");
                String matKhau = rs.getString("matKhau");
                LocalDate ngayTao = HandlerTime.convertStringToDate(rs.getString("ngayTao"));
                LocalDate ngayUpdate = HandlerTime.convertStringToDate(rs.getString("ngayUpdate"));
                int trangThai = rs.getInt("trangThai");
                return new Employee(name, diaChi, email, taiKhoan, matKhau, ngayTao, ngayUpdate, trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
