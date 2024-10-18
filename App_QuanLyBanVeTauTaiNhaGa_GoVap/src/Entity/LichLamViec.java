package Entity;

import java.time.LocalTime;
import java.util.Objects;

public class LichLamViec {
    private String MaLichLamViec;
    private String MaNV;
    private LocalTime GioBatDau;
    private LocalTime GioKetThuc;
    private String TrangThai;
    private String TenCa;

    public LichLamViec(String maLichLamViec, String maNV, LocalTime gioBatDau, LocalTime gioKetThuc, String trangThai, String tenCa) {
        MaLichLamViec = maLichLamViec;
        MaNV = maNV;
        GioBatDau = gioBatDau;
        GioKetThuc = gioKetThuc;
        TrangThai = trangThai;
        TenCa = tenCa;
    }

    public String getMaLichLamViec() {
        return MaLichLamViec;
    }

    public void setMaLichLamViec(String maLichLamViec) {
        MaLichLamViec = maLichLamViec;
    }

    public String getMaNV() {
        return MaNV;
    }

    public LocalTime getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        GioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        GioKetThuc = gioKetThuc;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTenCa() {
        return TenCa;
    }

    public void setTenCa(String tenCa) {
        TenCa = tenCa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichLamViec that = (LichLamViec) o;
        return Objects.equals(MaLichLamViec, that.MaLichLamViec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaLichLamViec);
    }

    @Override
    public String toString() {
        return "LichLamViec{" +
                "MaLichLamViec='" + MaLichLamViec + '\'' +
                ", MaNV='" + MaNV + '\'' +
                ", GioBatDau=" + GioBatDau +
                ", GioKetThuc=" + GioKetThuc +
                ", TrangThai='" + TrangThai + '\'' +
                ", TenCa='" + TenCa + '\'' +
                '}';
    }
}
