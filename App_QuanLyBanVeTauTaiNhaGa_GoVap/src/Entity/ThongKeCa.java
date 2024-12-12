package Entity;

import java.util.Date;

/**
 *
 * @author viet6
 */
public class ThongKeCa {
    private Date ngay;         // Ngày thống kê
    private String ca;         // Ca làm việc
    private double doanhThu;   // Doanh thu
    private int soVeBan;       // Số vé bán
    private int soHoaDon;      // Số hóa đơn

    // Constructor đầy đủ
    public ThongKeCa(Date ngay, String ca, double doanhThu, int soVeBan, int soHoaDon) {
        this.ngay = ngay;
        this.ca = ca;
        this.doanhThu = doanhThu;
        this.soVeBan = soVeBan;
        this.soHoaDon = soHoaDon;
    }

    // Getter và Setter cho các thuộc tính
    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public int getSoVeBan() {
        return soVeBan;
    }

    public void setSoVeBan(int soVeBan) {
        this.soVeBan = soVeBan;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    // Phương thức toString để hiển thị thông tin đối tượng
    @Override
    public String toString() {
        return "ThongKeCa{" +
                "ngay=" + ngay +
                ", ca='" + ca + '\'' +
                ", doanhThu=" + doanhThu +
                ", soVeBan=" + soVeBan +
                ", soHoaDon=" + soHoaDon +
                '}';
    }
}