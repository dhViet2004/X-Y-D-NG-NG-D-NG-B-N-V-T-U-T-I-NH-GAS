package Entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

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

    // Constructor
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

    public KhachHang(String maKH, String tenKH, String cccd, String soDT, String diaChi) {
        this.maKhachHang = maKH;
        this.tenKhachHang = tenKH;
        this.CCCD = cccd;
        this.soDienThoai = soDT;
        this.diaChi = diaChi;
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
        // Kiểm tra định dạng số điện thoại
        if (!isValidPhoneNumber(soDienThoai)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ.");
        }
        this.soDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        // Kiểm tra tên không rỗng
        if (tenKhachHang == null || tenKhachHang.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được để trống.");
        }
        this.tenKhachHang = tenKhachHang;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        // Kiểm tra định dạng CCCD
        if (!isValidCCCD(CCCD)) {
            throw new IllegalArgumentException("CCCD không hợp lệ.");
        }
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        // Kiểm tra địa chỉ không rỗng
        if (diaChi == null || diaChi.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }
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
        // Kiểm tra ngày sinh trước ngày hiện tại
        if (ngaySinh.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày sinh phải trước ngày hiện tại.");
        }
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

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Định dạng số điện thoại (ví dụ: 0xxxxxxxxx hoặc +84xxxxxxxxx)
        return Pattern.matches("(0[0-9]{9}|\\+84[0-9]{9})", phoneNumber);
    }

    private boolean isValidCCCD(String cccd) {
        // Định dạng CCCD (ví dụ: 12 số)
        return Pattern.matches("[0-9]{12}", cccd);
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", loaiKhachHang=" + loaiKhachHang +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", diemTichLuy=" + diemTichLuy +
                ", ngaySinh=" + ngaySinh +
                ", ngayThamgGia=" + ngayThamgGia +
                ", hangThanhVien='" + hangThanhVien + '\'' +
                '}';
    }
}
