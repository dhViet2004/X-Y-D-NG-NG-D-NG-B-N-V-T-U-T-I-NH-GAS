package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ThongTinChoNgoi {
    private String tenCho;
    private String toaSo;
    private LocalDate ngayDi;
    private LocalTime gioDi;
    private String gaDi;
    private String gaDen;
    private boolean trangThai;

    public ThongTinChoNgoi(String tenCho, String toaSo, LocalDate ngayDi, LocalTime gioDi, String gaDi, String gaDen, boolean trangThai) {
        this.tenCho = tenCho;
        this.toaSo = toaSo;
        this.ngayDi = ngayDi;
        this.gioDi = gioDi;
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.trangThai = trangThai;
    }

    public String getTenCho() {
        return tenCho;
    }

    public void setTenCho(String tenCho) {
        this.tenCho = tenCho;
    }

    public String getToaSo() {
        return toaSo;
    }

    public void setToaSo(String toaSo) {
        this.toaSo = toaSo;
    }

    public LocalDate getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        this.ngayDi = ngayDi;
    }

    public LocalTime getGioDi() {
        return gioDi;
    }

    public void setGioDi(LocalTime gioDi) {
        this.gioDi = gioDi;
    }

    public String getGaDi() {
        return gaDi;
    }

    public void setGaDi(String gaDi) {
        this.gaDi = gaDi;
    }

    public String getGaDen() {
        return gaDen;
    }

    public void setGaDen(String gaDen) {
        this.gaDen = gaDen;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "ThongTinChoNgoi{" +
                "tenCho='" + tenCho + '\'' +
                ", toaSo='" + toaSo + '\'' +
                ", ngayDi=" + ngayDi +
                ", gioDi=" + gioDi +
                ", gaDi='" + gaDi + '\'' +
                ", gaDen='" + gaDen + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
