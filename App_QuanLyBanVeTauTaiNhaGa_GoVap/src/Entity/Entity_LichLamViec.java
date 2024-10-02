package Entity;

import java.time.LocalTime;

public class Entity_LichLamViec {
    private String maLichLamViec;
    private String maNV;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
    private String trangThai;
    private String tenCa;

    public Entity_LichLamViec(String maLichLamViec, String maNV, LocalTime gioBatDau, LocalTime gioKetThuc, String trangThai, String tenCa) {
        this.maLichLamViec = maLichLamViec;
        this.maNV = maNV;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.trangThai = trangThai;
        this.tenCa = tenCa;
    }

    public String getMaLichLamViec() {
        return this.maLichLamViec;
    }

    public void setMaLichLamViec(String maLichLamViec) {
        this.maLichLamViec = maLichLamViec;
    }

    public String getMaNV() {
        return this.maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public LocalTime getGioBatDau() {
        return this.gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return this.gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public String getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenCa() {
        return this.tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public String toString() {
        String var10000 = this.maLichLamViec;
        return "Entity_LichLamViec{maLichLamViec='" + var10000 + "', maNV='" + this.maNV + "', gioBatDau=" + String.valueOf(this.gioBatDau) + ", gioKetThuc=" + String.valueOf(this.gioKetThuc) + ", trangThai='" + this.trangThai + "', tenCa='" + this.tenCa + "'}";
    }
}
