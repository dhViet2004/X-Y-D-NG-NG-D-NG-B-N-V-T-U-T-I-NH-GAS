package Entity;

import java.util.Objects;

public class Tau {
    private String MaTau;
    private TuyenTau TuyenTau;
    private String TenTau;
    private int SoToa;

    public Tau(Entity.TuyenTau tuyenTau, String tenTau, String maTau, int soToa) {
        TuyenTau = tuyenTau;
        TenTau = tenTau;
        MaTau = maTau;
        SoToa = soToa;
    }

    public Entity.TuyenTau getTuyenTau() {
        return TuyenTau;
    }


    public String getTenTau() {
        return TenTau;
    }

    public void setTenTau(String tenTau) {
        TenTau = tenTau;
    }

    public String getMaTau() {
        return MaTau;
    }

    public void setMaTau(String maTau) {
        MaTau = maTau;
    }

    public int getSoToa() {
        return SoToa;
    }

    public void setSoToa(int soToa) {
        SoToa = soToa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tau tau = (Tau) o;
        return Objects.equals(MaTau, tau.MaTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MaTau);
    }

    @Override
    public String toString() {
        return "Tau{" + "MaTau='" + MaTau + '\'' + ", TuyenTau=" + TuyenTau + ", TenTau='" + TenTau + '\'' + ", SoToa=" + SoToa + '}';
    }
}
