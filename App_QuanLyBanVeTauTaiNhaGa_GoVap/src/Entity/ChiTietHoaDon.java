package Entity;

import java.util.Objects;

public class ChiTietHoaDon {
    private final String MaVe;
    private final String MaHD;
    private int SoLuong;
    private double VAT;
    private double ThanhTien;
    private double TienThue;

    public ChiTietHoaDon(String maVe, String maHD, int soLuong, double VAT, double thanhTien, double tienThue) {
        MaVe = maVe;
        MaHD = maHD;
        SoLuong = soLuong;
        this.VAT = VAT;
        ThanhTien = thanhTien;
        TienThue = tienThue;
    }

    public String getMaVe() {
        return MaVe;
    }

    public String getMaHD() {
        return MaHD;
    }


    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }

    public double getTienThue() {
        return TienThue;
    }

    public void setTienThue(double tienThue) {
        TienThue = tienThue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(MaVe, that.MaVe) && Objects.equals(MaHD, that.MaHD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaVe, MaHD);
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "MaVe='" + MaVe + '\'' + ", MaHD='" + MaHD + '\'' + ", SoLuong=" + SoLuong + ", VAT=" + VAT + ", ThanhTien=" + ThanhTien + ", TienThue=" + TienThue + '}';
    }
}
