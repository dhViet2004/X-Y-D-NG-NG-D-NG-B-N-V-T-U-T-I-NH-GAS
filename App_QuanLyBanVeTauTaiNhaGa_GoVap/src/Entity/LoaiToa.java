package Entity;

import java.util.Objects;

public class LoaiToa {
    private String maLoai;
    private String tenLoai;

    public LoaiToa(String tenLoai, String maLoai) {
        this.tenLoai = tenLoai;
        this.maLoai = maLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiToa loaiToa = (LoaiToa) o;
        return Objects.equals(maLoai, loaiToa.maLoai);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoai);
    }

    @Override
    public String toString() {
        return "LoaiToa{" +
                "maLoai='" + maLoai + '\'' +
                ", tenLoai='" + tenLoai + '\'' +
                '}';
    }
}
