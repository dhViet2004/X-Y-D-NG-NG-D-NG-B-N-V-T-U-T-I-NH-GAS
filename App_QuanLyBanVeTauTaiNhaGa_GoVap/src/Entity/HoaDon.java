package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
    private String maHD;
    private LocalDate ngayLap;
    private  KhachHang khachHang;
    private KhuyenMai khuyenMai;
    private NhanVien nv;
    private String maLoaiHD;

    HoaDon(){
        this("",LocalDate.now(),null,null,null,"");
    }

    HoaDon(String maHD, LocalDate ngayLap,KhachHang kh, KhuyenMai km, NhanVien nv, String maLoaiHD){
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.khachHang = kh;
        this.khuyenMai = km;
        this.nv = nv;
        this.maLoaiHD = maLoaiHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
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

    public String getMaLoaiHD() {
        return maLoaiHD;
    }

    public void setMaLoaiHD(String maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
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
                ", ngayLap=" + ngayLap +
                ", khachHang=" + khachHang +
                ", khuyenMai=" + khuyenMai +
                ", nv=" + nv +
                ", maLoaiHD='" + maLoaiHD + '\'' +
                '}';
    }
}
