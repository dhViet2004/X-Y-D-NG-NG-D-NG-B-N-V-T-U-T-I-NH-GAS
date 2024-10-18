package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
    private String MaKM;
    private LocalDate ThoiGianBatDau;
    private LocalDate ThoiGianKetThuc;
    private String NoiDungKM;
    private double ChietKhau;
    private String DoiTuongApDung;

    public KhuyenMai(String maKM, LocalDate thoiGianBatDau, LocalDate thoiGianKetThuc, String noiDungKM, double chietKhau, String doiTuongApDung) {
        MaKM = maKM;
        ThoiGianBatDau = thoiGianBatDau;
        ThoiGianKetThuc = thoiGianKetThuc;
        NoiDungKM = noiDungKM;
        ChietKhau = chietKhau;
        DoiTuongApDung = doiTuongApDung;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String maKM) {
        MaKM = maKM;
    }

    public LocalDate getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDate thoiGianBatDau) {
        ThoiGianBatDau = thoiGianBatDau;
    }

    public LocalDate getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDate thoiGianKetThuc) {
        ThoiGianKetThuc = thoiGianKetThuc;
    }

    public String getNoiDungKM() {
        return NoiDungKM;
    }

    public void setNoiDungKM(String noiDungKM) {
        NoiDungKM = noiDungKM;
    }

    public double getChietKhau() {
        return ChietKhau;
    }

    public void setChietKhau(double chietKhau) {
        ChietKhau = chietKhau;
    }

    public String getDoiTuongApDung() {
        return DoiTuongApDung;
    }

    public void setDoiTuongApDung(String doiTuongApDung) {
        DoiTuongApDung = doiTuongApDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMai khuyenMai = (KhuyenMai) o;
        return Objects.equals(MaKM, khuyenMai.MaKM);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaKM);
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "MaKM='" + MaKM + '\'' +
                ", ThoiGianBatDau=" + ThoiGianBatDau +
                ", ThoiGianKetThuc=" + ThoiGianKetThuc +
                ", NoiDungKM='" + NoiDungKM + '\'' +
                ", ChietKhau=" + ChietKhau +
                ", DoiTuongApDung='" + DoiTuongApDung + '\'' +
                '}';
    }
}
