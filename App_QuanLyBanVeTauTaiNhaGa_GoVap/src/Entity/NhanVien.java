package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String soDT;
    private String trangThai;
    private String CCCD;
    private String diaChi;
    private LocalDate ngayVaoLam;
    private String ChucVu;

    public String getMaNV() {
        return this.maNV;
    }

    public String getTenNV() {
        return this.tenNV;
    }

    public String getSoDT() {
        return this.soDT;
    }

    public String getTrangThai() {
        return this.trangThai;
    }

    public String getCCCD() {
        return this.CCCD;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public LocalDate getNgayVaoLam() {
        return this.ngayVaoLam;
    }

    public String getChucVu() {
        return this.ChucVu;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public void setChucVu(String chucVu) {
        this.ChucVu = chucVu;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            NhanVien that = (NhanVien) o;
            return Objects.equals(this.maNV, that.maNV);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.maNV);
    }

    public NhanVien(String maNV, String tenNV, String soDT, String trangThai, String CCCD, String diaChi, LocalDate ngayVaoLam, String chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.soDT = soDT;
        this.trangThai = trangThai;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.ChucVu = chucVu;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", soDT='" + soDT + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", ngayVaoLam=" + ngayVaoLam +
                ", ChucVu='" + ChucVu + '\'' +
                '}';
    }

}
