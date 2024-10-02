package Entity;

import java.util.Objects;

public class LoaiHoaDon {
    private String maLoaiHoaDon;
    private  String tenLoaiHoaDon;
    private String theLoaiHoaDon;

    LoaiHoaDon(){
        this("","","");
    }

    LoaiHoaDon(String maLoaiHoaDon, String tenLoaiHoaDon, String theLoaiHoaDon){
        this.maLoaiHoaDon = maLoaiHoaDon;
        this.tenLoaiHoaDon = tenLoaiHoaDon;
        this.theLoaiHoaDon = theLoaiHoaDon;
    }
    public String getMaLoaiHoaDon() {
        return maLoaiHoaDon;
    }

    public void setMaLoaiHoaDon(String maLoaiHoaDon) {
        this.maLoaiHoaDon = maLoaiHoaDon;
    }

    public String getTenLoaiHoaDon() {
        return tenLoaiHoaDon;
    }

    public void setTenLoaiHoaDon(String tenLoaiHoaDon) {
        this.tenLoaiHoaDon = tenLoaiHoaDon;
    }

    public String getTheLoaiHoaDon() {
        return theLoaiHoaDon;
    }

    public void setTheLoaiHoaDon(String theLoaiHoaDon) {
        this.theLoaiHoaDon = theLoaiHoaDon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiHoaDon that = (LoaiHoaDon) o;
        return Objects.equals(maLoaiHoaDon, that.maLoaiHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiHoaDon);
    }

    @Override
    public String toString() {
        return "LoaiHoaDon{" +
                "maLoaiHoaDon='" + maLoaiHoaDon + '\'' +
                ", tenLoaiHoaDon='" + tenLoaiHoaDon + '\'' +
                ", theLoaiHoaDon='" + theLoaiHoaDon + '\'' +
                '}';
    }
}
