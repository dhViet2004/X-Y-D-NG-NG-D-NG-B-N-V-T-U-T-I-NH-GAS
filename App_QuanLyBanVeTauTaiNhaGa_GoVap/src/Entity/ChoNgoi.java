package Entity;

import java.util.Objects;

public class ChoNgoi {
    private String MaCho;
    private LoaiCho LoaiCho;
    private LoaiToa LoaiToa;
    private String TenCho;
    private Boolean TinhTrang;
    private Double Gia;

    public ChoNgoi(String maCho, Entity.LoaiCho loaiCho, Entity.LoaiToa loaiToa, String tenCho, Boolean tinhTrang, Double gia) {
        MaCho = maCho;
        LoaiCho = loaiCho;
        LoaiToa = loaiToa;
        TenCho = tenCho;
        TinhTrang = tinhTrang;
        Gia = gia;
    }

    public String getMaCho() {
        return MaCho;
    }

    public void setMaCho(String maCho) {
        MaCho = maCho;
    }

    public Entity.LoaiCho getLoaiCho() {
        return LoaiCho;
    }

    public void setLoaiCho(Entity.LoaiCho loaiCho) {
        LoaiCho = loaiCho;
    }

    public String getTenCho() {
        return TenCho;
    }

    public void setTenCho(String tenCho) {
        TenCho = tenCho;
    }

    public Entity.LoaiToa getLoaiToa() {
        return LoaiToa;
    }

    public void setLoaiToa(Entity.LoaiToa loaiToa) {
        LoaiToa = loaiToa;
    }

    public Boolean getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoNgoi choNgoi = (ChoNgoi) o;
        return Objects.equals(MaCho, choNgoi.MaCho);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaCho);
    }

    @Override
    public String toString() {
        return "ChoNgoi{" + "MaCho='" + MaCho + '\'' + ", LoaiCho=" + LoaiCho + ", LoaiToa=" + LoaiToa + ", TenCho='" + TenCho + '\'' + ", TinhTrang=" + TinhTrang + ", Gia=" + Gia + '}';
    }
}
