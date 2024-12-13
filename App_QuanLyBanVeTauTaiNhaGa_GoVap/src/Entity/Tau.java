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
        if (maTau == null || !maTau.matches("[A-Z]{4}\\d{4}")) {
            throw new IllegalArgumentException("Mã tàu không hợp lệ. Định dạng đúng: XXXXNNN (4 chữ cái + 3 chữ số).");
        }
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
        if (tenTau == null || tenTau.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tàu không được để trống.");
        }
        this.tenTau = tenTau;
    }

    public int getSoToa() {
        return soToa;
    }

    public void setSoToa(int soToa) {
        if (soToa < 0) {
            throw new IllegalArgumentException("Số toa không được là số âm.");
        }
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
