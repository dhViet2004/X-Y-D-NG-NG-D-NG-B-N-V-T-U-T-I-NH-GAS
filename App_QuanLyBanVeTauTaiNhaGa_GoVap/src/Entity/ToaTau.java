package Entity;

import java.util.Objects;

public class ToaTau {
    private String MaToa;
    private LoaiToa LoaiToa;
    private Tau Tau;
    private String TenToa;
    private int SoGhe;
    private int ThuTu;

    public ToaTau(String maToa, Entity.LoaiToa loaiToa, Entity.Tau tau, int soGhe, String tenToa, int thuTu) {
        MaToa = maToa;
        LoaiToa = loaiToa;
        Tau = tau;
        SoGhe = soGhe;
        TenToa = tenToa;
        ThuTu = thuTu;
    }

    public String getMaToa() {
        return MaToa;
    }


    public Entity.LoaiToa getLoaiToa() {
        return LoaiToa;
    }


    public Entity.Tau getTau() {
        return Tau;
    }


    public String getTenToa() {
        return TenToa;
    }

    public void setTenToa(String tenToa) {
        TenToa = tenToa;
    }

    public int getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(int soGhe) {
        SoGhe = soGhe;
    }

    public int getThuTu() {
        return ThuTu;
    }

    public void setThuTu(int thuTu) {
        ThuTu = thuTu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToaTau toaTau = (ToaTau) o;
        return Objects.equals(MaToa, toaTau.MaToa) && Objects.equals(LoaiToa, toaTau.LoaiToa) && Objects.equals(Tau, toaTau.Tau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaToa, LoaiToa, Tau);
    }

    @Override
    public String toString() {
        return "ToaTau{" + "MaToa='" + MaToa + '\'' + ", LoaiToa=" + LoaiToa + ", Tau=" + Tau + ", TenToa='" + TenToa + '\'' + ", SoGhe=" + SoGhe + ", ThuTu=" + ThuTu + '}';
    }
}
