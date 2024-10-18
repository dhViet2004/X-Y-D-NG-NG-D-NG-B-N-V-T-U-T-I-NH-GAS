package Entity;

import java.util.Objects;

public class LoaiToa {
    private  String MaLoai;
    private  String TenLoai;

    public LoaiToa(String tenLoai, String maLoai) {
        TenLoai = tenLoai;
        MaLoai = maLoai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiToa loaiToa = (LoaiToa) o;
        return Objects.equals(MaLoai, loaiToa.MaLoai);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaLoai);
    }

    @Override
    public String toString() {
        return "LoaiToa{" +
                "MaLoai='" + MaLoai + '\'' +
                ", TenLoai='" + TenLoai + '\'' +
                '}';
    }
}
