package Entity;

import java.util.Objects;

public class LoaiHoaDon {
    private String maLoaiHoaDon;
    private String tenLoaiHoaDon;

    public LoaiHoaDon(String maLoaiHoaDon, String tenLoaiHoaDon) {
        this.maLoaiHoaDon = maLoaiHoaDon;
        this.tenLoaiHoaDon = tenLoaiHoaDon;
    }

    public LoaiHoaDon(String maLoaiHoaDon) {
        this.maLoaiHoaDon = maLoaiHoaDon;
    }

    public String getMaLoaiHoaDon() {
        return maLoaiHoaDon;
    }

    public String getTenLoaiHoaDon() {
        return tenLoaiHoaDon;
    }

    public void setTenLoaiHoaDon(String tenLoaiHoaDon) {
        this.tenLoaiHoaDon = tenLoaiHoaDon;
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
        return "LoaiHoaDon{" + "maLoaiHoaDon='" + maLoaiHoaDon + '\'' + ", tenLoaiHoaDon='" + tenLoaiHoaDon + '\'' + '}';
    }
}
