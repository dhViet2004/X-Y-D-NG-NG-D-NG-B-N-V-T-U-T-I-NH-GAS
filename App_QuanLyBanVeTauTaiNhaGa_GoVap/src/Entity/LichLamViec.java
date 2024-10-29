package Entity;
      
import java.time.LocalDateTime;
public class LichLamViec {
    private String maLichLamViec;
    private String maNhanVien;
    private LocalDateTime gioBatDau;
    private LocalDateTime gioKetThuc;
    private String trangThai;
    private String tenCa;

    // Constructor mặc định
    public LichLamViec() {}

    // Constructor có tham số
    public LichLamViec(String maLichLamViec, String maNhanVien, LocalDateTime gioBatDau, LocalDateTime gioKetThuc, String trangThai, String tenCa) {
        this.maLichLamViec = maLichLamViec;
        this.maNhanVien = maNhanVien;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.trangThai = trangThai;
        this.tenCa = tenCa;
    }

    // Getter và Setter
    public String getMaLichLamViec() {
        return maLichLamViec;
    }

    public void setMaLichLamViec(String maLichLamViec) {
        if (maLichLamViec != null && !maLichLamViec.isEmpty()) {
            this.maLichLamViec = maLichLamViec;
        }
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            this.maNhanVien = maNhanVien;
        }
    }

    public LocalDateTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalDateTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalDateTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalDateTime gioKetThuc) {
        if (gioKetThuc.isAfter(gioBatDau)) {
            this.gioKetThuc = gioKetThuc;
        }
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
        if (tenCa != null && !tenCa.isEmpty()) {
            this.tenCa = tenCa;
        }
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "LichLamViec [maLichLamViec=" + maLichLamViec + ", maNhanVien=" + maNhanVien + ", gioBatDau=" + gioBatDau +
               ", gioKetThuc=" + gioKetThuc + ", trangThai=" + trangThai + ", tenCa=" + tenCa + "]";
    }
}
