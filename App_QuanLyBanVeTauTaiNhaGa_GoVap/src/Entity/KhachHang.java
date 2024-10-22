package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
    private String maKhachHang;
    private LoaiKhachHang loaiKhachHang;
    private String soDienThoai;
    private String tenKhachHang;
    private String CCCD;
    private String diaChi;
    private double diemTichLuy;
    private LocalDate ngaySinh;
    private LocalDate ngayThamgGia;
    private String hangThanhVien;

    public KhachHang(String maKhachHang, LoaiKhachHang loaiKhachHang, String soDienThoai, String tenKhachHang, String CCCD, String diaChi, double diemTichLuy, LocalDate ngaySinh, LocalDate ngayThamgGia, String hangThanhVien) {
        this.maKhachHang = maKhachHang;
        this.loaiKhachHang = loaiKhachHang;
        this.soDienThoai = soDienThoai;
        this.tenKhachHang = tenKhachHang;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.diemTichLuy = diemTichLuy;
        this.ngaySinh = ngaySinh;
        this.ngayThamgGia = ngayThamgGia;
        this.hangThanhVien = hangThanhVien;
    }

    public KhachHang(String maKhachHang){
        this.maKhachHang = maKhachHang;
    }
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public LoaiKhachHang getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(double diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public LocalDate getNgayThamgGia() {
        return ngayThamgGia;
    }

    public void setNgayThamgGia(LocalDate ngayThamgGia) {
        this.ngayThamgGia = ngayThamgGia;
    }

    public String getHangThanhVien() {
        return hangThanhVien;
    }

    public void setHangThanhVien(String hangThanhVien) {
        this.hangThanhVien = hangThanhVien;
    }
}
