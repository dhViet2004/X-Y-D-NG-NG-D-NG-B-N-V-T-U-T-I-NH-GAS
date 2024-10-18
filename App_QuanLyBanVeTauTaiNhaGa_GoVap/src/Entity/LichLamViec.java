package Entity;

import java.time.LocalTime;
import java.util.Objects;

public class LichLamViec {
    private String maLichLamViec;
    private String maNV;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
    private String trangThai;
    private String tenCa;

    public LichLamViec(String maLichLamViec, String maNV, LocalTime gioBatDau, LocalTime gioKetThuc, String trangThai, String tenCa) {
        this.maLichLamViec = maLichLamViec;
        this.maNV = maNV;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.trangThai = trangThai;
        this.tenCa = tenCa;
    }

    public String getMaLichLamViec() {
        return maLichLamViec;
    }

    public void setMaLichLamViec(String maLichLamViec) {
        this.maLichLamViec = maLichLamViec;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichLamViec that = (LichLamViec) o;
        return Objects.equals(maLichLamViec, that.maLichLamViec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLichLamViec);
    }

    @Override
    public String toString() {
        return "LichLamViec{" +
                "maLichLamViec='" + maLichLamViec + '\'' +
                ", maNV='" + maNV + '\'' +
                ", gioBatDau=" + gioBatDau +
                ", gioKetThuc=" + gioKetThuc +
                ", trangThai='" + trangThai + '\'' +
                ", tenCa='" + tenCa + '\'' +
                '}';
    }
}
