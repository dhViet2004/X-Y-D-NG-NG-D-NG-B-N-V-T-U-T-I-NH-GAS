package Entity;

import java.util.Objects;

public class TuyenTau {
    private String maTuyen;
    private String tenTuyen;
    private String gaDi;
    private String gaDen;
    private String diaDiemDi;
    private String diaDiemDen;

    public TuyenTau(String tenTuyen, String maTuyen, String gaDi, String gaDen, String diaDiemDi, String diaDiemDen) {
        this.tenTuyen = tenTuyen;
        this.maTuyen = maTuyen;
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.diaDiemDi = diaDiemDi;
        this.diaDiemDen = diaDiemDen;
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
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

    public String getDiaDiemDi() {
        return diaDiemDi;
    }

    public void setDiaDiemDi(String diaDiemDi) {
        this.diaDiemDi = diaDiemDi;
    }

    public String getDiaDiemDen() {
        return diaDiemDen;
    }

    public void setDiaDiemDen(String diaDiemDen) {
        this.diaDiemDen = diaDiemDen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuyenTau tuyenTau = (TuyenTau) o;
        return Objects.equals(maTuyen, tuyenTau.maTuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maTuyen);
    }

    @Override
    public String toString() {
        return "TuyenTau{" +
                "maTuyen='" + maTuyen + '\'' +
                ", tenTuyen='" + tenTuyen + '\'' +
                ", gaDi='" + gaDi + '\'' +
                ", gaDen='" + gaDen + '\'' +
                ", diaDiemDi='" + diaDiemDi + '\'' +
                ", diaDiemDen='" + diaDiemDen + '\'' +
                '}';
    }
}
