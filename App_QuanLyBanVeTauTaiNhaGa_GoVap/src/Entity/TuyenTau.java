package Entity;

import java.util.Objects;

public class TuyenTau {
    private String maTuyen;
    private String tenTuyen;
    private String gaDi;
    private String gaDen;
    private String diaDiemDi;
    private String diaDiemDen;


    // Constructor
    public TuyenTau(String maTuyen, String tenTuyen, String gaDi, String gaDen, String diaDiemDi, String diaDiemDen) {
        setMaTuyen(maTuyen);
        setTenTuyen(tenTuyen);
        this.diaDiemDen = diaDiemDen;
        this.diaDiemDi = diaDiemDi;
        this.gaDen = gaDen;
        this.gaDi = gaDi;

    }

    public TuyenTau() {
    }

    // Getters và Setters
    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        if (maTuyen == null || !maTuyen.matches("[A-Z]{2}-[A-Z]{2}-\\d")) {
            throw new IllegalArgumentException("Mã tuyến không hợp lệ. Định dạng đúng: XX-yy-z (2 chữ cái viết hoa, 2 chữ cái thường, 1 chữ số).");
        }
        this.maTuyen = maTuyen;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        if (tenTuyen == null || tenTuyen.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tuyến không được để trống.");
        }
        this.tenTuyen = tenTuyen;
    }

    public String getGaDi() {
        return gaDi;
    }

    public void setGaDi(String gaDi) {
        if (gaDi == null || gaDi.trim().isEmpty()) {
            throw new IllegalArgumentException("Ga đi không được để trống.");
        }
        this.gaDi = gaDi;
    }

    public String getGaDen() {
        return gaDen;
    }

    public void setGaDen(String gaDen) {
        if (gaDen == null || gaDen.trim().isEmpty()) {
            throw new IllegalArgumentException("Ga đến không được để trống.");
        }
        this.gaDen = gaDen;
    }

    public String getDiaDiemDi() {
        return diaDiemDi;
    }

    public void setDiaDiemDi(String diaDiemDi) {
        if (diaDiemDi == null || diaDiemDi.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa điểm đi không được để trống.");
        }
        this.diaDiemDi = diaDiemDi;
    }

    public String getDiaDiemDen() {
        return diaDiemDen;
    }

    public void setDiaDiemDen(String diaDiemDen) {
        if (diaDiemDen == null || diaDiemDen.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa điểm đến không được để trống.");
        }
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
