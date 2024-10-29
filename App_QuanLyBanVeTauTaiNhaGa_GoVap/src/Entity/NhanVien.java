package Entity;

import java.time.LocalDate;

public class NhanVien {
    private String maNhanVien;
    private String tenNV;
    private String soDT;
    private String trangThai;
    private String cccd;
    private String diaChi;
    private LocalDate ngayVaoLam;
    private String chucVu;
    private String avata; // Thêm thuộc tính avata

    // Constructor mặc định
    public NhanVien() {}

    public NhanVien(String maNhanVien, String chucVu) {
        this.maNhanVien = maNhanVien;
        this.chucVu = chucVu;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }


    // Constructor có tham số
    public NhanVien(String maNhanVien, String tenNV, String soDT, String trangThai, String cccd, String diaChi, LocalDate ngayVaoLam, String chucVu, String avata) {
        this.maNhanVien = maNhanVien;
        this.tenNV = tenNV;
        this.soDT = soDT;
        this.trangThai = trangThai;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.chucVu = chucVu;
        this.avata = avata; // Khởi tạo thuộc tính avata
    }

    // Getter và Setter
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            this.maNhanVien = maNhanVien;
        }
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        if (tenNV != null && !tenNV.isEmpty()) {
            this.tenNV = tenNV;
        }
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        if (soDT != null && soDT.matches("0[1-9][0-9]{8}")) {
            this.soDT = soDT;
        }
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        if (trangThai.equals("Đang làm") || trangThai.equals("Nghỉ việc")) {
            this.trangThai = trangThai;
        }
    }

    public String getCCCD() {
        return cccd;
    }

    public void setCCCD(String cccd) {
        if (cccd != null && !cccd.isEmpty()) {
            this.cccd = cccd;
        }
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        if (diaChi != null && !diaChi.isEmpty()) {
            this.diaChi = diaChi;
        }
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        if (chucVu != null && !chucVu.isEmpty()) {
            this.chucVu = chucVu;
        }
    }

    public String getAvata() {
        return avata; // Getter cho avata
    }

    public void setAvata(String avata) {
        this.avata = avata; // Setter cho avata
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "NhanVien [maNhanVien=" + maNhanVien + ", tenNV=" + tenNV + ", soDT=" + soDT + ", trangThai=" + trangThai +
               ", CCCD=" + cccd + ", diaChi=" + diaChi + ", ngayVaoLam=" + ngayVaoLam + ", chucVu=" + chucVu + ", avata=" + avata + "]";
    }
}
