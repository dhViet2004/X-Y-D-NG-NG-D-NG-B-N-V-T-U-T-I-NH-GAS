package Entity;

import java.util.Objects;

public class Tau {
    private String maTau;
    private TuyenTau tuyenTau;
    private String tenTau;
    private int soToa;

    public Tau(Entity.TuyenTau tuyenTau, String tenTau, String maTau, int soToa) {
        this.tuyenTau = tuyenTau;
        this.tenTau = tenTau;
        this.maTau = maTau;
        this.soToa = soToa;
    }

    public Entity.TuyenTau getTuyenTau() {
        return tuyenTau;
    }

    public String getTenTau() {
        return tenTau;
    }

    public void setTenTau(String tenTau) {
        this.tenTau = tenTau;
    }

    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public int getSoToa() {
        return soToa;
    }

    public void setSoToa(int soToa) {
        this.soToa = soToa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tau tau = (Tau) o;
        return Objects.equals(maTau, tau.maTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maTau);
    }

    @Override
    public String toString() {
        return "Tau{" +
                "maTau='" + maTau + '\'' +
                ", tuyenTau=" + tuyenTau +
                ", tenTau='" + tenTau + '\'' +
                ", soToa=" + soToa +
                '}';
    }
}
