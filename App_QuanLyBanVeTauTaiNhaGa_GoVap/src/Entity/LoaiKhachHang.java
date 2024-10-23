package Entity;

import java.util.Objects;

public class LoaiKhachHang {
    private String maLoaiKhachHang;
    private String tenLoaiKhachHang;

    public LoaiKhachHang() {
        this("", "");
    }
    public  LoaiKhachHang(String maLoai){
        this.maLoaiKhachHang = maLoai;
    }


    public LoaiKhachHang(String maLoaiKhachHang, String tenLoaiKhachHang) {
        this.maLoaiKhachHang = maLoaiKhachHang;
        this.tenLoaiKhachHang = tenLoaiKhachHang;
    }

    public String getMaLoaiKhachHang() {
        return maLoaiKhachHang;
    }

    public void setMaLoaiKhachHang(String maLoaiKhachHang) {
        this.maLoaiKhachHang = maLoaiKhachHang;
    }

    public String getTenLoaiKhachHang() {
        return tenLoaiKhachHang;
    }

    public void setTenLoaiKhachHang(String tenLoaiKhachHang) {
        this.tenLoaiKhachHang = tenLoaiKhachHang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiKhachHang that = (LoaiKhachHang) o;
        return Objects.equals(maLoaiKhachHang, that.maLoaiKhachHang);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiKhachHang);
    }

    @Override
    public String toString() {
        return "LoaiKhachHang{" +
                "maLoaiKhachHang='" + maLoaiKhachHang + '\'' +
                ", tenLoaiKhachHang='" + tenLoaiKhachHang + '\'' +
                '}';
    }
}

