package Entity;

import java.util.Objects;

public class ChiTietHoaDon {
    private String maVe;
    private String maHD;
    private int soLuong;
    private double VAT;
    private double thanhTien;
    private double tienThue;

    public ChiTietHoaDon(String maVe, String maHD, int soLuong, double VAT, double thanhTien, double tienThue) {
        this.maVe = maVe;
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.VAT = VAT;
        this.thanhTien = thanhTien;
        this.tienThue = tienThue;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getTienThue() {
        return tienThue;
    }

    public void setTienThue(double tienThue) {
        this.tienThue = tienThue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(maVe, that.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maVe);
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "maVe='" + maVe + '\'' + ", maHD='" + maHD + '\'' + ", soLuong=" + soLuong + ", VAT=" + VAT + ", thanhTien=" + thanhTien + ", tienThue=" + tienThue + '}';
    }
}
