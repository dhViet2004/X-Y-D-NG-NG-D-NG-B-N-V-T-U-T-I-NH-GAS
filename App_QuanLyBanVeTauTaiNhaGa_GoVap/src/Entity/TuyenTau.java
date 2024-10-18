package Entity;

import java.util.Objects;

public class TuyenTau {
    private String MaTuyen;
    private String TenTuyen;
    private String GaDi;
    private String GaDen;
    private String DiaDiemDi;
    private String DiaDiemDen;

    public TuyenTau(String tenTuyen, String maTuyen, String gaDi, String gaDen, String diaDiemDi, String diaDiemDen) {
        TenTuyen = tenTuyen;
        MaTuyen = maTuyen;
        GaDi = gaDi;
        GaDen = gaDen;
        DiaDiemDi = diaDiemDi;
        DiaDiemDen = diaDiemDen;
    }

    public String getMaTuyen() {
        return MaTuyen;
    }


    public String getTenTuyen() {
        return TenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        TenTuyen = tenTuyen;
    }

    public String getGaDi() {
        return GaDi;
    }

    public void setGaDi(String gaDi) {
        GaDi = gaDi;
    }

    public String getGaDen() {
        return GaDen;
    }

    public void setGaDen(String gaDen) {
        GaDen = gaDen;
    }

    public String getDiaDiemDi() {
        return DiaDiemDi;
    }

    public void setDiaDiemDi(String diaDiemDi) {
        DiaDiemDi = diaDiemDi;
    }

    public String getDiaDiemDen() {
        return DiaDiemDen;
    }

    public void setDiaDiemDen(String diaDiemDen) {
        DiaDiemDen = diaDiemDen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuyenTau tuyenTau = (TuyenTau) o;
        return Objects.equals(MaTuyen, tuyenTau.MaTuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaTuyen);
    }

    @Override
    public String toString() {
        return "TuyenTau{" + "MaTuyen='" + MaTuyen + '\'' + ", TenTuyen='" + TenTuyen + '\'' + ", GaDi='" + GaDi + '\'' + ", GaDen='" + GaDen + '\'' + ", DiaDiemDi='" + DiaDiemDi + '\'' + ", DiaDiemDen='" + DiaDiemDen + '\'' + '}';
    }
}
