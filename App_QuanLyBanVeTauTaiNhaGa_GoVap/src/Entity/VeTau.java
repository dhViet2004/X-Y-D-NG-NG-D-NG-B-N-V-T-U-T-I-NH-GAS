package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class VeTau {
    private String maVe;
    private LichTrinhTau lichTrinhTau;
    private ChoNgoi choNgoi;
    private String tenKhachHang;
    private String giayTo;
    private LocalDate ngayDi;
    private String doiTuong;
    private double giaVe;
    private String trangThai;

    public VeTau(String maVe, LichTrinhTau lichTrinhTau, ChoNgoi choNgoi, String tenKhachHang, String giayTo, LocalDate ngayDi, String doiTuong, double giaVe, String trangThai) {
        this.maVe = maVe;
        this.lichTrinhTau = lichTrinhTau;
        this.choNgoi = choNgoi;
        this.tenKhachHang = tenKhachHang;
        this.giayTo = giayTo;
        this.ngayDi = ngayDi;
        this.doiTuong = doiTuong;
        this.giaVe = giaVe;
        this.trangThai = trangThai;
    }

    public VeTau(String maVeTau, String maLichTrinh, String choNgoiMaCho, String tenKH, String giayTo, LocalDate ngayDi, String doiTuong, double giaVe, String trangThai) {
        this.maVe = maVeTau;
        this.lichTrinhTau = new LichTrinhTau(maLichTrinh, null, null, null, null); // Tạo đối tượng từ mã lịch trình
        this.choNgoi = new ChoNgoi(choNgoiMaCho);
        this.tenKhachHang = tenKH;
        this.giayTo = giayTo;
        this.ngayDi = ngayDi;
        this.doiTuong = doiTuong;
        this.giaVe = giaVe;
        this.trangThai = trangThai;
    }

    public VeTau() {

    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
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
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getGiayTo() {
        return giayTo;
    }

    public void setGiayTo(String giayTo) {
        this.giayTo = giayTo;
    }

    public LocalDate getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeTau veTau = (VeTau) o;
        return Objects.equals(maVe, veTau.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maVe);
    }

    @Override
    public String toString() {
        return "VeTau{" +
                "maVe='" + maVe + '\'' +
                ", lichTrinhTau=" + lichTrinhTau +
                ", choNgoi=" + choNgoi +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", giayTo='" + giayTo + '\'' +
                ", ngayDi=" + ngayDi +
                ", doiTuong='" + doiTuong + '\'' +
                ", giaVe=" + giaVe +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
