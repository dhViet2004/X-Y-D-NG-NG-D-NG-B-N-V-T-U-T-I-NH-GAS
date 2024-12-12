package Entity;

public class Tau {
    private String maTau; // Mã tàu
    private TuyenTau tuyenTau; // Đối tượng TuyenTau
    private String tenTau; // Tên tàu
    private int soToa; // Số toa

    // Constructor
    public Tau(String maTau, TuyenTau tuyenTau, String tenTau, int soToa) {
        this.maTau = maTau;
        this.tuyenTau = tuyenTau;
        this.tenTau = tenTau;
        this.soToa = soToa;
    }

    public Tau() {

    }

    // Getters và Setters
    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public TuyenTau getTuyenTau() {
        return tuyenTau;
    }

    public void setTuyenTau(TuyenTau tuyenTau) {
        this.tuyenTau = tuyenTau;
    }

    public String getTenTau() {
        return tenTau;
    }

    public void setTenTau(String tenTau) {
        this.tenTau = tenTau;
    }

    public int getSoToa() {
        return soToa;
    }

    public void setSoToa(int soToa) {
        this.soToa = soToa;
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
