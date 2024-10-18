package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
    private String maHD;
    private KhachHang khachHang;
    private KhuyenMai khuyenMai;
    private NhanVien nv;
    private LoaiHoaDon LoaiHoaDon;
    private LocalDate ngayLap;
    private double TienGiam;
    private double TongTien;

    public HoaDon(String maHD, KhachHang khachHang, KhuyenMai khuyenMai, NhanVien nv, Entity.LoaiHoaDon loaiHoaDon, LocalDate ngayLap, double tienGiam, double tongTien) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.khuyenMai = khuyenMai;
        this.nv = nv;
        LoaiHoaDon = loaiHoaDon;
        this.ngayLap = ngayLap;
        TienGiam = tienGiam;
        TongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }


    public KhachHang getKhachHang() {
        return khachHang;
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

    public Entity.LoaiHoaDon getLoaiHoaDon() {
        return LoaiHoaDon;
    }


    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public double getTienGiam() {
        return TienGiam;
    }

    public double getTongTien() {
        return TongTien;
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
                ", LoaiHoaDon=" + LoaiHoaDon +
                ", ngayLap=" + ngayLap +
                ", TienGiam=" + TienGiam +
                ", TongTien=" + TongTien +
                '}';
    }
}
