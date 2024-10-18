package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class LichTrinhTau {
    private String maLich;
    private String maTau;
    private String gioDi;
    private LocalDate ngayDi;

    public LichTrinhTau(String maLich, String maTau, String gioDi, LocalDate ngayDi) {
        this.maLich = maLich;
        this.maTau = maTau;
        this.gioDi = gioDi;
        this.ngayDi = ngayDi;
    }

    public String getMaLich() {
        return maLich;
    }

    public void setMaLich(String maLich) {
        this.maLich = maLich;
    }

    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public String getGioDi() {
        return gioDi;
    }

    public void setGioDi(String gioDi) {
        this.gioDi = gioDi;
    }

    public LocalDate getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        this.ngayDi = ngayDi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichTrinhTau that = (LichTrinhTau) o;
        return Objects.equals(maLich, that.maLich);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLich);
    }

    @Override
    public String toString() {
        return "LichTrinhTau{" +
                "maLich='" + maLich + '\'' +
                ", maTau='" + maTau + '\'' +
                ", gioDi='" + gioDi + '\'' +
                ", ngayDi=" + ngayDi +
                '}';
    }
}
