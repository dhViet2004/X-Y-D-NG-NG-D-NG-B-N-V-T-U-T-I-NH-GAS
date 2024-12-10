package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDon {
    private String maHD;
    private KhachHang khachHang;
    private KhuyenMai khuyenMai;
    private NhanVien nv;
    private LoaiHoaDon loaiHoaDon;
    private LocalDateTime ngayLap;
    private double tienGiam;
    private double tongTien;

    public HoaDon(String maHD, KhachHang khachHang, KhuyenMai khuyenMai, NhanVien nv, LoaiHoaDon loaiHoaDon, LocalDateTime ngayLap, double tienGiam, double tongTien) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.khuyenMai = khuyenMai;
        this.nv = nv;
        this.loaiHoaDon = loaiHoaDon;
        this.ngayLap = ngayLap;
        this.tienGiam = tienGiam;
        this.tongTien = tongTien;
    }

    public HoaDon() {

    }

    public String getMaHD() {
        return maHD;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public LoaiHoaDon getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(LoaiHoaDon loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(double tienGiam) {
        this.tienGiam = tienGiam;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(maHD, hoaDon.maHD);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHD);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD='" + maHD + '\'' +
                ", khachHang=" + khachHang +
                ", khuyenMai=" + khuyenMai +
                ", nv=" + nv +
                ", loaiHoaDon=" + loaiHoaDon +
                ", ngayLap=" + ngayLap +
                ", tienGiam=" + tienGiam +
                ", tongTien=" + tongTien +
                '}';
    }
}
