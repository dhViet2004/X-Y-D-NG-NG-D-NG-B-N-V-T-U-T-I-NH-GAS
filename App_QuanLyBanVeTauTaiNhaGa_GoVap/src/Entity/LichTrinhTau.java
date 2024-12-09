package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class LichTrinhTau {
    // 1.1 maLichTrinh
    private String maLichTrinh; // XXyyMMddNN: mã tuyến, năm, tháng, ngày, số chuyến
    // 1.2 gioDi
    private LocalTime gioDi; // Giờ khởi hành, không null
    // 1.3 ngayDi
    private LocalDate ngayDi; // Ngày đi, không null
    // 1.4 tau là một đối tượng Tau
    private Tau tau; // Mã tàu là đối tượng Tau
    // 1.5 trangThai
    private String trangThai; // Trạng thái của lịch trình tàu

    // Định dạng regex cho mã lịch trình
    private static final String MA_LICH_TRINH_PATTERN = "^[A-Z]{4}\\d{4}\\d{8}$";


    // Constructor
    public LichTrinhTau(String maLichTrinh, LocalTime gioDi, LocalDate ngayDi, Tau tau, String trangThai) {
        setMaLichTrinh(maLichTrinh); // Gọi phương thức set với ràng buộc
        this.gioDi = gioDi;
        this.ngayDi = ngayDi;
        this.tau = tau;
        this.trangThai = trangThai; // Khởi tạo trạng thái
    }

    public LichTrinhTau(String maLichTrinh, LocalDate ngayDi, LocalTime gioDi) {
        this.maLichTrinh = maLichTrinh;
        this.gioDi = gioDi;
        this.ngayDi = ngayDi;
    }

    // Getter và setter cho maLichTrinh
    public String getMaLichTrinh() {
        return maLichTrinh;
    }

    public void setMaLichTrinh(String maLichTrinh) {
        if (!isValidMaLichTrinh(maLichTrinh)) {
            throw new IllegalArgumentException("Mã lịch trình không hợp lệ! Phải theo định dạng XXyyMMddNN.");
        }
        this.maLichTrinh = maLichTrinh;
    }

    // Phương thức kiểm tra tính hợp lệ của mã lịch trình
    private boolean isValidMaLichTrinh(String maLichTrinh) {
        return Pattern.matches(MA_LICH_TRINH_PATTERN, maLichTrinh);
    }

    // Getters và setters khác
    public LocalTime getGioDi() {
        return gioDi;
    }

    public void setGioDi(LocalTime gioDi) {
        this.gioDi = gioDi;
    }

    public LocalDate getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        this.ngayDi = ngayDi;
    }

    public Tau getTau() {
        return tau;
    }

    public void setTau(Tau tau) {
        this.tau = tau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "LichTrinhTau{" +
                "maLichTrinh='" + maLichTrinh + '\'' +
                ", gioDi=" + gioDi +
                ", ngayDi=" + ngayDi +
                ", tau=" + tau +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
