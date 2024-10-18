package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class VeTau {
    private String MaVe;
    private LichTrinhTau lichTrinhTau;
    private ChoNgoi choNgoi;
    private String TenKhachHang;
    private String GiayTo;
    private LocalDate NgayDi;
    private String DoiTuong;
    private double GiaVe;
    private String TrangThai;

    public VeTau(String maVe, LichTrinhTau lichTrinhTau, ChoNgoi choNgoi, String tenKhachHang, String giayTo, LocalDate ngayDi, String doiTuong, double giaVe, String trangThai) {
        MaVe = maVe;
        this.lichTrinhTau = lichTrinhTau;
        this.choNgoi = choNgoi;
        TenKhachHang = tenKhachHang;
        GiayTo = giayTo;
        NgayDi = ngayDi;
        DoiTuong = doiTuong;
        GiaVe = giaVe;
        TrangThai = trangThai;
    }

    public String getMaVe() {
        return MaVe;
    }

    public void setMaVe(String maVe) {
        MaVe = maVe;
    }

    public LichTrinhTau getLichTrinhTau() {
        return lichTrinhTau;
    }

    public void setLichTrinhTau(LichTrinhTau lichTrinhTau) {
        this.lichTrinhTau = lichTrinhTau;
    }

    public ChoNgoi getChoNgoi() {
        return choNgoi;
    }

    public void setChoNgoi(ChoNgoi choNgoi) {
        this.choNgoi = choNgoi;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getGiayTo() {
        return GiayTo;
    }

    public void setGiayTo(String giayTo) {
        GiayTo = giayTo;
    }

    public LocalDate getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        NgayDi = ngayDi;
    }

    public String getDoiTuong() {
        return DoiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        DoiTuong = doiTuong;
    }

    public double getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(double giaVe) {
        GiaVe = giaVe;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeTau veTau = (VeTau) o;
        return Objects.equals(MaVe, veTau.MaVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaVe);
    }

    @Override
    public String toString() {
        return "VeTau{" + "MaVe='" + MaVe + '\'' + ", lichTrinhTau=" + lichTrinhTau + ", choNgoi=" + choNgoi + ", TenKhachHang='" + TenKhachHang + '\'' + ", GiayTo='" + GiayTo + '\'' + ", NgayDi=" + NgayDi + ", DoiTuong='" + DoiTuong + '\'' + ", GiaVe=" + GiaVe + ", TrangThai='" + TrangThai + '\'' + '}';
    }
}
