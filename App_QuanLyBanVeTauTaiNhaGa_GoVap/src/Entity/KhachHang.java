package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
    private String MaKhachHang;
    private LoaiKhachHang LoaiKhachHang;
    private String SoDienThoai;
    private String TenKhachHang;
    private String CCCD;
    private String DiaChi;
    private double DiemTichLuy;
    private LocalDate NgaySinh;
    private LocalDate NgayThamgGia;
    private String HangThanhVien;

    public KhachHang(String maKhachHang, Entity.LoaiKhachHang loaiKhachHang, String soDienThoai, String tenKhachHang, String CCCD, String diaChi, double diemTichLuy, LocalDate ngaySinh, LocalDate ngayThamgGia, String hangThanhVien) {
        MaKhachHang = maKhachHang;
        LoaiKhachHang = loaiKhachHang;
        SoDienThoai = soDienThoai;
        TenKhachHang = tenKhachHang;
        this.CCCD = CCCD;
        DiaChi = diaChi;
        DiemTichLuy = diemTichLuy;
        NgaySinh = ngaySinh;
        NgayThamgGia = ngayThamgGia;
        HangThanhVien = hangThanhVien;
    }

    public Entity.LoaiKhachHang getLoaiKhachHang() {
        return LoaiKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public String getCCCD() {
        return CCCD;
    }

    public double getDiemTichLuy() {
        return DiemTichLuy;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public LocalDate getNgayThamgGia() {
        return NgayThamgGia;
    }

    public String getHangThanhVien() {
        return HangThanhVien;
    }

    public void setLoaiKhachHang(Entity.LoaiKhachHang loaiKhachHang) {
        LoaiKhachHang = loaiKhachHang;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setDiemTichLuy(double diemTichLuy) {
        DiemTichLuy = diemTichLuy;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public void setNgayThamgGia(LocalDate ngayThamgGia) {
        NgayThamgGia = ngayThamgGia;
    }

    public void setHangThanhVien(String hangThanhVien) {
        HangThanhVien = hangThanhVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(MaKhachHang, khachHang.MaKhachHang);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaKhachHang);
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "MaKhachHang='" + MaKhachHang + '\'' +
                ", LoaiKhachHang=" + LoaiKhachHang +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                ", TenKhachHang='" + TenKhachHang + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", DiemTichLuy=" + DiemTichLuy +
                ", NgaySinh=" + NgaySinh +
                ", NgayThamgGia=" + NgayThamgGia +
                ", HangThanhVien='" + HangThanhVien + '\'' +
                '}';
    }
}
