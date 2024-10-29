package Entity;
        
public class TaiKhoan {
    private String maNhanVien;
    private String password;

    // Constructor mặc định
    public TaiKhoan() {}

    // Constructor có tham số
    public TaiKhoan(String maNhanVien, String password) {
        this.maNhanVien = maNhanVien;
        setPassword(password);
    }

    // Getter và Setter
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            this.maNhanVien = maNhanVien;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 8) {
            this.password = password;
        }
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "TaiKhoan [maNhanVien=" + maNhanVien + ", password=" + password + "]";
    }
}
