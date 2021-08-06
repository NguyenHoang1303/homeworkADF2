package com.t2012e.assignment.entity;

import java.time.LocalDate;

public class Employee {
    private String ten;
    private String diaChi;
    private String email;
    private String taiKhoan;
    private String matKhau;
    private LocalDate ngayTao;
    private LocalDate ngayUpdate;
    private int trangThai;

    public Employee() {
    }

    public Employee(String ten, String diaChi, String email, String taiKhoan, String matKhau, LocalDate ngayTao, LocalDate ngayUpdate, int trangThai) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
        this.ngayUpdate = ngayUpdate;
        this.trangThai = trangThai;
    }

    public Employee(String ten, String diaChi, String email, String taiKhoan, String matKhau) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngayTao = LocalDate.now();
        this.ngayUpdate = LocalDate.now();
        this.trangThai = 1;
    }

    @Override
    public String toString() {
        return String.format("%5s%10s%5s | %5s%10s%5s | %5s%10s%10s | %5s%10s%15s | %5s%10s%5s | %5s%10s%5s | %5s%10s%5s | %5s%5s%5s \n",
                "", ten, "",
                "", diaChi, "",
                "", email, "",
                "", taiKhoan, "",
                "", matKhau, "",
                "", ngayTao, "",
                "", ngayUpdate, "",
                "", handlerTrangThai(), ""
        );
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDate getNgayUpdate() {
        return ngayUpdate;
    }

    public void setNgayUpdate(LocalDate ngayUpdate) {
        this.ngayUpdate = ngayUpdate;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public String handlerTrangThai(){
        if (this.trangThai == 1 ){
            return "đã kich hoạt";
        }
        return "đã xoá";
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
