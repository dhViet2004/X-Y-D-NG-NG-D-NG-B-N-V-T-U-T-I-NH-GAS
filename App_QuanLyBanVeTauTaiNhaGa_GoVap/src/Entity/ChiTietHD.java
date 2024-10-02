package Entity;

import java.util.Objects;

public class ChiTietHD {
    private  String maHD;
    private VeTau ve;
    private  int soLuong;
    private  double gia;
    private  double thue;
    private  double vat;

    ChiTietHD(){
        this("",null,0,0.0,0.0,0.0);
    }

    ChiTietHD(String maHD, VeTau ve, int soLuong, double gia,double thue, double vat){
        this.maHD = maHD;
        this.ve = ve;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thue = thue;
        this.vat = vat;
    }

    public String getMaHD() {
        return maHD;
    }

    public VeTau getVe() {
        return ve;
    }

    public void setVe(VeTau ve) {
        this.ve = ve;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHD chiTietHD = (ChiTietHD) o;
        return Objects.equals(maHD, chiTietHD.maHD);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHD);
    }

    @Override
    public String toString() {
        return "ChiTietHD{" +
                "maHD='" + maHD + '\'' +
                ", ve=" + ve +
                ", soLuong=" + soLuong +
                ", gia=" + gia +
                ", thue=" + thue +
                ", vat=" + vat +
                '}';
    }
}
