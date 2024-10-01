CREATE DATABASE UngDungQuanLyBanVeTauTaiGaGoVap;

use UngDungQuanLyBanVeTauTaiGaGoVap;

CREATE TABLE NhanVien (
    MaNV VARCHAR(255) PRIMARY KEY,
    TenNV VARCHAR(255) NOT NULL,
    SoDT VARCHAR(255),
	TrangThai VARCHAR(255),
	CCCD VARCHAR(255),
    DiaChi VARCHAR(255),
    NgayThamGia DATE,
    ChucVu VARCHAR(50)
);

CREATE TABLE TaiKhoan (
    MaNV VARCHAR(255),
    Password VARCHAR(255),
    PRIMARY KEY (MaNV),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE LichLamViec (
    MaLichLamViec VARCHAR(255) PRIMARY KEY,
    MaNhanVien VARCHAR(255),
    GioBatDau TIME,
    GioKetThuc TIME,
    TrangThai VARCHAR(255),
    TenCa VARCHAR(255),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNV)
);

CREATE TABLE LoaiKhachHang (
    MaLoaiKH VARCHAR(255) PRIMARY KEY,
    TenLoaiKH VARCHAR(255)
);

CREATE TABLE KhachHang (
    MaKH VARCHAR(255) PRIMARY KEY,
    LoaiKhachHangMaLoaiKH VARCHAR(255),
    SoDT VARCHAR(255),
    TenKH VARCHAR(255),
    DiaChi VARCHAR(255),
    CCCD VARCHAR(255),
    DiemTichLuy INTEGER,
    NgaySinh DATE,
    NgayThamGia DATE,
    HangThanhVien VARCHAR(255),
    FOREIGN KEY (LoaiKhachHangMaLoaiKH) REFERENCES LoaiKhachHang(MaLoaiKH)
);

CREATE TABLE KhuyenMai (
    MaKM VARCHAR(255) PRIMARY KEY,
    ThoiGianBatDau DATE,
    ThoiGianKetThuc DATE,
    NoiDungKM VARCHAR(255),
    ChietKhau FLOAT,
    DoiTuongApDung VARCHAR(255)
);

CREATE TABLE TuyenTau (
    MaTuyen VARCHAR(255) PRIMARY KEY,
    GaDi VARCHAR(255),
    GaDen VARCHAR(255),
    DiaDiemDi VARCHAR(255),
    DiaDiemDen VARCHAR(255)
);

CREATE TABLE ChuyenTau (
    MaChuyen VARCHAR(255) PRIMARY KEY,
    MaTuyen VARCHAR(255),
    TenChuyen VARCHAR(255),
    GoDen Time,
	GioDi Time,
    SoToa INTEGER,
    FOREIGN KEY (MaTuyen) REFERENCES TuyenTau(MaTuyen)
);

CREATE TABLE LoaiToa (
    MaLoai VARCHAR(255) PRIMARY KEY,
    TenLoai VARCHAR(255)
);

CREATE TABLE ToaTau (
    MaToa VARCHAR(255) PRIMARY KEY,
    LoaiToaMaLoai VARCHAR(255),
    ChuyenTauMaChuyen VARCHAR(255),
    TenToa VARCHAR(255),
    SoGhe INTEGER,
    ThuTu INTEGER,
    FOREIGN KEY (LoaiToaMaLoai) REFERENCES LoaiToa(MaLoai),
    FOREIGN KEY (ChuyenTauMaChuyen) REFERENCES ChuyenTau(MaChuyen)
);

CREATE TABLE LoaiCho (
    MaLoai VARCHAR(255) PRIMARY KEY,
    TenLoai VARCHAR(255)
);

CREATE TABLE ChoNgoi (
    MaCho VARCHAR(255) PRIMARY KEY,
    LoaiChoMaLoai VARCHAR(255),
	LoaiToaMaToa VARCHAR(255),
	TenCho VARCHAR(255),
    TinhTrang BIT,
    GiaTien float,
    FOREIGN KEY (LoaiChoMaLoai) REFERENCES LoaiCho(MaLoai),
	FOREIGN KEY (LoaiToaMaToa) REFERENCES ToaTau(MaToa)
);

CREATE TABLE VeTau (
    MaVe VARCHAR(255) PRIMARY KEY,
    ChoNgoiMaCho VARCHAR(255),
	TenKH NVARCHAR(255),
	GiayTo NVARCHAR(255),
	NgayDi date,
	DoiTuong NVARCHAR(255),
	GiaVe float,
    FOREIGN KEY (ChoNgoiMaCho) REFERENCES ChoNgoi(MaCho),
);


CREATE TABLE LoaiHoaDon (
    MaLoai VARCHAR(255) PRIMARY KEY,
    TenLoaiHoaDon VARCHAR(255)
);

CREATE TABLE HoaDon (
    MaHD VARCHAR(255) PRIMARY KEY,
    MaKH VARCHAR(255),
    KhuyenMaiMaKM VARCHAR(255),
    NhanVienMaNV VARCHAR(255),
	MaLoai VARCHAR(255),
	NgayHoaDon DATE,
	TienKhuyenMai float,
    TongTien float,
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (KhuyenMaiMaKM) REFERENCES KhuyenMai(MaKM),
    FOREIGN KEY (NhanVienMaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaLoai) REFERENCES LoaiHoaDon (MaLoai)
);

CREATE TABLE ChiTietHoaDon (
    MaVe VARCHAR(255),
    MaHD VARCHAR(255),
    SoLuong INTEGER,
    VAT float,
    ThanhTien float,
    TenThue VARCHAR(255),
    PRIMARY KEY (MaVe, MaHD),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaVe) REFERENCES VeTau(MaVe)
);