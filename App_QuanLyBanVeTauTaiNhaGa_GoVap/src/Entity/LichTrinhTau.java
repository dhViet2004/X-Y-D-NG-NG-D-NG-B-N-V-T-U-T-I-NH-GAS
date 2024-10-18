package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class LichTrinhTau {
    private String MaLich;
    private String MaTau;
    private String GioDi;
    private LocalDate NgayDi;

    public LichTrinhTau(String maLich, String maTau, String gioDi, LocalDate ngayDi) {
        MaLich = maLich;
        MaTau = maTau;
        GioDi = gioDi;
        NgayDi = ngayDi;
    }

    public String getMaTau() {
        return MaTau;
    }

    public String getGioDi() {
        return GioDi;
    }

    public void setGioDi(String gioDi) {
        GioDi = gioDi;
    }

    public String getMaLich() {
        return MaLich;
    }

    public LocalDate getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        NgayDi = ngayDi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichTrinhTau that = (LichTrinhTau) o;
        return Objects.equals(MaLich, that.MaLich) && Objects.equals(MaTau, that.MaTau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaLich, MaTau);
    }

    @Override
    public String toString() {
        return "LichTrinhTau{" +
                "MaLich='" + MaLich + '\'' +
                ", MaTau='" + MaTau + '\'' +
                ", GioDi='" + GioDi + '\'' +
                ", NgayDi=" + NgayDi +
                '}';
    }
}
