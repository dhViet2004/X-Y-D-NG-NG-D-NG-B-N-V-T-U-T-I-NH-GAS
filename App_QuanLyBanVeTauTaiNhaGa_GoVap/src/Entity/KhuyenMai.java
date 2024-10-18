package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
    private String maKM;
    private LocalDate thoiGianBatDau;
    private LocalDate thoiGianKetThuc;
    private String noiDungKM;
    private double chietKhau;
    private String doiTuongApDung;

    public KhuyenMai(String maKM, LocalDate thoiGianBatDau, LocalDate thoiGianKetThuc, String noiDungKM, double chietKhau, String doiTuongApDung) {
        this.maKM = maKM;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.noiDungKM = noiDungKM;
        this.chietKhau = chietKhau;
        this.doiTuongApDung = doiTuongApDung;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public LocalDate getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDate thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalDate getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDate thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getNoiDungKM() {
        return noiDungKM;
    }

    public void setNoiDungKM(String noiDungKM) {
        this.noiDungKM = noiDungKM;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public String getDoiTuongApDung() {
        return doiTuongApDung;
    }

    public void setDoiTuongApDung(String doiTuongApDung) {
        this.doiTuongApDung = doiTuongApDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMai khuyenMai = (KhuyenMai) o;
        return Objects.equals(maKM, khuyenMai.maKM);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKM);
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "maKM='" + maKM + '\'' +
                ", thoiGianBatDau=" + thoiGianBatDau +
                ", thoiGianKetThuc=" + thoiGianKetThuc +
                ", noiDungKM='" + noiDungKM + '\'' +
                ", chietKhau=" + chietKhau +
                ", doiTuongApDung='" + doiTuongApDung + '\'' +
                '}';
    }
}
