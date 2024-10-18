package Entity;

import java.util.Objects;

public class ToaTau {
    private String maToa;
    private LoaiToa loaiToa;
    private Tau tau;
    private String tenToa;
    private int soGhe;
    private int thuTu;

    public ToaTau(String maToa, LoaiToa loaiToa, Tau tau, int soGhe, String tenToa, int thuTu) {
        this.maToa = maToa;
        this.loaiToa = loaiToa;
        this.tau = tau;
        this.soGhe = soGhe;
        this.tenToa = tenToa;
        this.thuTu = thuTu;
    }

    public ToaTau(String maToa) {
        this.maToa = maToa;
    }

    public String getMaToa() {
        return maToa;
    }

    public LoaiToa getLoaiToa() {
        return loaiToa;
    }

    public Tau getTau() {
        return tau;
    }

    public String getTenToa() {
        return tenToa;
    }

    public void setTenToa(String tenToa) {
        this.tenToa = tenToa;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToaTau toaTau = (ToaTau) o;
        return Objects.equals(maToa, toaTau.maToa) &&
                Objects.equals(loaiToa, toaTau.loaiToa) &&
                Objects.equals(tau, toaTau.tau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maToa, loaiToa, tau);
    }

    @Override
    public String toString() {
        return "ToaTau{" +
                "maToa='" + maToa + '\'' +
                ", loaiToa=" + loaiToa +
                ", tau=" + tau +
                ", tenToa='" + tenToa + '\'' +
                ", soGhe=" + soGhe +
                ", thuTu=" + thuTu +
                '}';
    }
}
