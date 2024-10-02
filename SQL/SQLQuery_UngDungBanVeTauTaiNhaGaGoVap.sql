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

INSERT INTO TuyenTau (MaTuyen, GaDi, GaDen, DiaDiemDi, DiaDiemDen) VALUES
('TT01', 'Ga Sài Gòn', 'Ga Hà Nội', 'Sài Gòn', 'Hà Nội'),
('TT02', 'Ga Sài Gòn', 'Ga Nha Trang', 'Sài Gòn', 'Nha Trang'),
('TT03', 'Ga Sài Gòn', 'Ga Đà Nẵng', 'Sài Gòn', 'Đà Nẵng'),
('TT04', 'Ga Sài Gòn', 'Ga Phan Thiết', 'Sài Gòn', 'Phan Thiết'),
('TT05', 'Ga Sài Gòn', 'Ga Quy Nhơn', 'Sài Gòn', 'Quy Nhơn'),
('TT06', 'Ga Sài Gòn', 'Ga Quảng Ngãi', 'Sài Gòn', 'Quảng Ngãi'),
('TT07', 'Ga Sài Gòn', 'Ga Huế', 'Sài Gòn', 'Huế'),
('TT08', 'Ga Sài Gòn', 'Ga Nam Định', 'Sài Gòn', 'Nam Định'),
('TT09', 'Ga Sài Gòn', 'Ga Vinh', 'Sài Gòn', 'Vinh'),
('TT10', 'Ga Sài Gòn', 'Ga Thanh Hóa', 'Sài Gòn', 'Thanh Hóa'),
('TT11', 'Ga Sài Gòn', 'Ga Hải Phòng', 'Sài Gòn', 'Hải Phòng'),
('TT12', 'Ga Sài Gòn', 'Ga Đông Hà', 'Sài Gòn', 'Đông Hà'),
('TT13', 'Ga Sài Gòn', 'Ga Đồng Hới', 'Sài Gòn', 'Đồng Hới'),
('TT14', 'Ga Sài Gòn', 'Ga Tháp Chàm', 'Sài Gòn', 'Tháp Chàm'),
('TT15', 'Ga Sài Gòn', 'Ga Biên Hòa', 'Sài Gòn', 'Biên Hòa'),
('TT16', 'Ga Sài Gòn', 'Ga Sóng Thần', 'Sài Gòn', 'Sóng Thần'),
('TT17', 'Ga Sài Gòn', 'Ga Dĩ An', 'Sài Gòn', 'Dĩ An'),
('TT18', 'Ga Sài Gòn', 'Ga Sông Lũy', 'Sài Gòn', 'Sông Lũy'),
('TT19', 'Ga Sài Gòn', 'Ga Bình Thuận', 'Sài Gòn', 'Bình Thuận'),
('TT20', 'Ga Sài Gòn', 'Ga Phan Rang', 'Sài Gòn', 'Phan Rang'),
('TT21', 'Ga Sài Gòn', 'Ga Đà Lạt', 'Sài Gòn', 'Đà Lạt'),
('TT22', 'Ga Sài Gòn', 'Ga Đà Lạt', 'Sài Gòn', 'Đà Lạt'),
('TT23', 'Ga Sài Gòn', 'Ga Buôn Ma Thuột', 'Sài Gòn', 'Buôn Ma Thuột'),
('TT24', 'Ga Sài Gòn', 'Ga Pleiku', 'Sài Gòn', 'Pleiku'),
('TT25', 'Ga Sài Gòn', 'Ga Kon Tum', 'Sài Gòn', 'Kon Tum'),
('TT26', 'Ga Sài Gòn', 'Ga Lạng Sơn', 'Sài Gòn', 'Lạng Sơn'),
('TT27', 'Ga Sài Gòn', 'Ga Cao Bằng', 'Sài Gòn', 'Cao Bằng'),
('TT28', 'Ga Sài Gòn', 'Ga Bắc Kạn', 'Sài Gòn', 'Bắc Kạn'),
('TT29', 'Ga Sài Gòn', 'Ga Hà Giang', 'Sài Gòn', 'Hà Giang'),
('TT30', 'Ga Sài Gòn', 'Ga Tuyên Quang', 'Sài Gòn', 'Tuyên Quang'),
('TT31', 'Ga Sài Gòn', 'Ga Yên Bái', 'Sài Gòn', 'Yên Bái'),
('TT32', 'Ga Sài Gòn', 'Ga Lào Cai', 'Sài Gòn', 'Lào Cai'),
('TT33', 'Ga Sài Gòn', 'Ga Phú Thọ', 'Sài Gòn', 'Phú Thọ'),
('TT34', 'Ga Sài Gòn', 'Ga Hòa Bình', 'Sài Gòn', 'Hòa Bình'),
('TT35', 'Ga Sài Gòn', 'Ga Sơn La', 'Sài Gòn', 'Sơn La'),
('TT36', 'Ga Sài Gòn', 'Ga Lai Châu', 'Sài Gòn', 'Lai Châu'),
('TT37', 'Ga Sài Gòn', 'Ga Điện Biên', 'Sài Gòn', 'Điện Biên'),
('TT38', 'Ga Sài Gòn', 'Ga Thái Nguyên', 'Sài Gòn', 'Thái Nguyên'),
('TT39', 'Ga Sài Gòn', 'Ga Bắc Ninh', 'Sài Gòn', 'Bắc Ninh'),
('TT40', 'Ga Sài Gòn', 'Ga Bắc Giang', 'Sài Gòn', 'Bắc Giang'),
('TT41', 'Ga Sài Gòn', 'Ga Hưng Yên', 'Sài Gòn', 'Hưng Yên'),
('TT42', 'Ga Sài Gòn', 'Ga Hà Nam', 'Sài Gòn', 'Hà Nam'),
('TT43', 'Ga Sài Gòn', 'Ga Ninh Bình', 'Sài Gòn', 'Ninh Bình'),
('TT44', 'Ga Sài Gòn', 'Ga Nam Định', 'Sài Gòn', 'Nam Định'),
('TT45', 'Ga Sài Gòn', 'Ga Hải Dương', 'Sài Gòn', 'Hải Dương'),
('TT46', 'Ga Sài Gòn', 'Ga Thái Bình', 'Sài Gòn', 'Thái Bình'),
('TT47', 'Ga Sài Gòn', 'Ga Vĩnh Phúc', 'Sài Gòn', 'Vĩnh Phúc'),
('TT48', 'Ga Sài Gòn', 'Ga Bắc Giang', 'Sài Gòn', 'Bắc Giang'),
('TT49', 'Ga Sài Gòn', 'Ga Hà Nội', 'Sài Gòn', 'Hà Nội'),
('TT50', 'Ga Sài Gòn', 'Ga Quảng Bình', 'Sài Gòn', 'Quảng Bình');

INSERT INTO ChuyenTau (MaChuyen, MaTuyen, TenChuyen, GoDen, GioDi, SoToa) VALUES
('CT01', 'TT01', 'SE1', '05:30', '21:55', 11),
('CT02', 'TT02', 'SE2', '04:50', '19:45', 10),
('CT03', 'TT03', 'SE3', '17:12', '09:00', 11),
('CT04', 'TT04', 'SE4', '15:30', '06:00', 10),
('CT05', 'TT05', 'SE5', '03:55', '14:40', 10),
('CT06', 'TT06', 'SE6', '17:12', '09:00', 11),
('CT07', 'TT07', 'SE7', '15:30', '06:00', 10),
('CT08', 'TT08', 'SE8', '03:55', '14:40', 10),
('CT09', 'TT09', 'SE9', '05:30', '21:55', 11),
('CT10', 'TT10', 'SE10', '04:50', '19:45', 10),
('CT11', 'TT11', 'SE11', '17:12', '09:00', 11),
('CT12', 'TT12', 'SE12', '15:30', '06:00', 10),
('CT13', 'TT13', 'SE13', '03:55', '14:40', 10),
('CT14', 'TT14', 'SE14', '17:12', '09:00', 11),
('CT15', 'TT15', 'SE15', '15:30', '06:00', 10),
('CT16', 'TT16', 'SE16', '03:55', '14:40', 10),
('CT17', 'TT17', 'SE17', '05:30', '21:55', 11),
('CT18', 'TT18', 'SE18', '04:50', '19:45', 10),
('CT19', 'TT19', 'SE19', '17:12', '09:00', 11),
('CT20', 'TT20', 'SE20', '15:30', '06:00', 10),
('CT21', 'TT21', 'SE21', '03:55', '14:40', 10),
('CT22', 'TT22', 'SE22', '17:12', '09:00', 11),
('CT23', 'TT23', 'SE23', '15:30', '06:00', 10),
('CT24', 'TT24', 'SE24', '03:55', '14:40', 10),
('CT25', 'TT25', 'SE25', '05:30', '21:55', 11),
('CT26', 'TT26', 'SE26', '04:50', '19:45', 10),
('CT27', 'TT27', 'SE27', '17:12', '09:00', 11),
('CT28', 'TT28', 'SE28', '15:30', '06:00', 10),
('CT29', 'TT29', 'SE29', '03:55', '14:40', 10),
('CT30', 'TT30', 'SE30', '17:12', '09:00', 11),
('CT31', 'TT31', 'SE31', '15:30', '06:00', 10),
('CT32', 'TT32', 'SE32', '03:55', '14:40', 10),
('CT33', 'TT33', 'SE33', '05:30', '21:55', 11),
('CT34', 'TT34', 'SE34', '04:50', '19:45', 10),
('CT35', 'TT35', 'SE35', '17:12', '09:00', 11),
('CT36', 'TT36', 'SE36', '15:30', '06:00', 10),
('CT37', 'TT37', 'SE37', '03:55', '14:40', 10),
('CT38', 'TT38', 'SE38', '17:12', '09:00', 11),
('CT39', 'TT39', 'SE39', '15:30', '06:00', 10),
('CT40', 'TT40', 'SE40', '03:55', '14:40', 10),
('CT41', 'TT41', 'SE41', '05:30', '21:55', 11),
('CT42', 'TT42', 'SE42', '04:50', '19:45', 10),
('CT43', 'TT43', 'SE43', '17:12', '09:00', 11),
('CT44', 'TT44', 'SE44', '15:30', '06:00', 10),
('CT45', 'TT45', 'SE45', '03:55', '14:40', 10),
('CT46', 'TT46', 'SE46', '17:12', '09:00', 11),
('CT47', 'TT47', 'SE47', '15:30', '06:00', 10),
('CT48', 'TT48', 'SE48', '03:55', '14:40', 10),
('CT49', 'TT49', 'SE49', '05:30', '21:55', 11),
('CT50', 'TT50', 'SE50', '04:50', '19:45', 10);

INSERT INTO LoaiToa (MaLoai, TenLoai) VALUES
('LT01', 'Ghế ngồi cứng'),
('LT02', 'Ghế ngồi mềm'),
('LT03', 'Ghế ngồi mềm điều hòa'),
('LT04', 'Giường nằm cứng'),
('LT05', 'Giường nằm mềm'),
('LT06', 'Ghế VIP'),
('LT07', 'Toa Thường loại 1'),
('LT08', 'Toa Thường loại 2'),
('LT09', 'Toa Thường loại 3'),
('LT10', 'Toa Thường loại 4'),
('LT11', 'Toa Thường loại 5'),
('LT12', 'Toa VIP loại 1'),
('LT13', 'Toa VIP loại 2'),
('LT14', 'Toa VIP loại 3'),
('LT15', 'Toa VIP loại 4'),
('LT16', 'Toa VIP loại 5'),
('LT17', 'Ghế ngồi cứng tầng 1'),
('LT18', 'Ghế ngồi cứng tầng 2'),
('LT19', 'Ghế ngồi mềm tầng 1'),
('LT20', 'Ghế ngồi mềm tầng 2'),
('LT21', 'Giường nằm cứng tầng 1'),
('LT22', 'Giường nằm cứng tầng 2'),
('LT23', 'Giường nằm mềm tầng 1'),
('LT24', 'Giường nằm mềm tầng 2'),
('LT25', 'Ghế ngồi đôi'),
('LT26', 'Ghế ngồi đơn'),
('LT27', 'Giường nằm đôi'),
('LT28', 'Giường nằm đơn'),
('LT29', 'Ghế ngồi phòng riêng'),
('LT30', 'Giường nằm phòng riêng'),
('LT31', 'Toa hành lý'),
('LT32', 'Toa hàng hóa'),
('LT33', 'Toa bưu điện'),
('LT34', 'Toa ăn uống'),
('LT35', 'Toa giải trí'),
('LT36', 'Toa điều hòa'),
('LT37', 'Toa nghỉ ngơi'),
('LT38', 'Toa đọc sách'),
('LT39', 'Toa chơi game'),
('LT40', 'Toa karaoke'),
('LT41', 'Toa gia đình'),
('LT42', 'Toa công tác'),
('LT43', 'Toa học tập'),
('LT44', 'Toa tập thể dục'),
('LT45', 'Toa yoga'),
('LT46', 'Toa chăm sóc sức khỏe'),
('LT47', 'Toa du lịch'),
('LT48', 'Toa thể thao'),
('LT49', 'Toa sinh hoạt cộng đồng'),
('LT50', 'Toa đa năng');

INSERT INTO ToaTau (MaToa, LoaiToaMaLoai, ChuyenTauMaChuyen, TenToa, SoGhe, ThuTu) VALUES
('TT01', 'LT01', 'CT01', 'Toa 1', 60, 1),
('TT02', 'LT02', 'CT01', 'Toa 2', 50, 2),
('TT03', 'LT03', 'CT01', 'Toa 3', 45, 3),
('TT04', 'LT04', 'CT01', 'Toa 4', 40, 4),
('TT05', 'LT05', 'CT01', 'Toa 5', 35, 5),
('TT06', 'LT06', 'CT02', 'Toa 1', 50, 1),
('TT07', 'LT07', 'CT02', 'Toa 2', 55, 2),
('TT08', 'LT08', 'CT02', 'Toa 3', 45, 3),
('TT09', 'LT09', 'CT02', 'Toa 4', 50, 4),
('TT10', 'LT10', 'CT02', 'Toa 5', 60, 5),
('TT11', 'LT11', 'CT03', 'Toa 1', 55, 1),
('TT12', 'LT12', 'CT03', 'Toa 2', 40, 2),
('TT13', 'LT13', 'CT03', 'Toa 3', 45, 3),
('TT14', 'LT14', 'CT03', 'Toa 4', 50, 4),
('TT15', 'LT15', 'CT03', 'Toa 5', 65, 5),
('TT16', 'LT16', 'CT04', 'Toa 1', 70, 1),
('TT17', 'LT17', 'CT04', 'Toa 2', 55, 2),
('TT18', 'LT18', 'CT04', 'Toa 3', 45, 3),
('TT19', 'LT19', 'CT04', 'Toa 4', 50, 4),
('TT20', 'LT20', 'CT04', 'Toa 5', 60, 5),
('TT21', 'LT21', 'CT05', 'Toa 1', 55, 1),
('TT22', 'LT22', 'CT05', 'Toa 2', 50, 2),
('TT23', 'LT23', 'CT05', 'Toa 3', 60, 3),
('TT24', 'LT24', 'CT05', 'Toa 4', 45, 4),
('TT25', 'LT25', 'CT05', 'Toa 5', 40, 5),
('TT26', 'LT26', 'CT06', 'Toa 1', 35, 1),
('TT27', 'LT27', 'CT06', 'Toa 2', 60, 2),
('TT28', 'LT28', 'CT06', 'Toa 3', 50, 3),
('TT29', 'LT29', 'CT06', 'Toa 4', 45, 4),
('TT30', 'LT30', 'CT06', 'Toa 5', 40, 5),
('TT31', 'LT31', 'CT07', 'Toa 1', 55, 1),
('TT32', 'LT32', 'CT07', 'Toa 2', 65, 2),
('TT33', 'LT33', 'CT07', 'Toa 3', 45, 3),
('TT34', 'LT34', 'CT07', 'Toa 4', 40, 4),
('TT35', 'LT35', 'CT07', 'Toa 5', 35, 5),
('TT36', 'LT36', 'CT08', 'Toa 1', 30, 1),
('TT37', 'LT37', 'CT08', 'Toa 2', 55, 2),
('TT38', 'LT38', 'CT08', 'Toa 3', 60, 3),
('TT39', 'LT39', 'CT08', 'Toa 4', 45, 4),
('TT40', 'LT40', 'CT08', 'Toa 5', 50, 5),
('TT41', 'LT41', 'CT09', 'Toa 1', 60, 1),
('TT42', 'LT42', 'CT09', 'Toa 2', 55, 2),
('TT43', 'LT43', 'CT09', 'Toa 3', 45, 3),
('TT44', 'LT44', 'CT09', 'Toa 4', 50, 4),
('TT45', 'LT45', 'CT09', 'Toa 5', 65, 5),
('TT46', 'LT46', 'CT10', 'Toa 1', 70, 1),
('TT47', 'LT47', 'CT10', 'Toa 2', 55, 2),
('TT48', 'LT48', 'CT10', 'Toa 3', 60, 3),
('TT49', 'LT49', 'CT10', 'Toa 4', 45, 4),
('TT50', 'LT50', 'CT10', 'Toa 5', 50, 5);

INSERT INTO LoaiCho (MaLoai, TenLoai) VALUES
('LC01', 'Ghế ngồi cứng'),
('LC02', 'Ghế ngồi mềm'),
('LC03', 'Ghế nằm cứng'),
('LC04', 'Ghế nằm mềm'),
('LC05', 'Ghế ngồi có điều hòa'),
('LC06', 'Ghế nằm có điều hòa'),
('LC07', 'Ghế ngồi hạng thường'),
('LC08', 'Ghế ngồi hạng sang'),
('LC09', 'Ghế nằm hạng thường'),
('LC10', 'Ghế nằm hạng sang');

INSERT INTO ChoNgoi (MaCho, LoaiChoMaLoai, LoaiToaMaToa, TenCho, TinhTrang, GiaTien) VALUES
('CN01', 'LC01', 'TT01', 'Chỗ 1A', 1, 200000),
('CN02', 'LC01', 'TT01', 'Chỗ 1B', 1, 200000),
('CN03', 'LC02', 'TT01', 'Chỗ 1C', 1, 250000),
('CN04', 'LC02', 'TT02', 'Chỗ 2A', 1, 250000),
('CN05', 'LC03', 'TT02', 'Chỗ 2B', 1, 300000),
('CN06', 'LC03', 'TT03', 'Chỗ 3A', 0, 300000),
('CN07', 'LC04', 'TT03', 'Chỗ 3B', 1, 350000),
('CN08', 'LC04', 'TT04', 'Chỗ 4A', 1, 350000),
('CN09', 'LC05', 'TT04', 'Chỗ 4B', 0, 400000),
('CN10', 'LC05', 'TT05', 'Chỗ 5A', 1, 400000);

INSERT INTO VeTau (MaVe, ChoNgoiMaCho, TenKH, GiayTo, NgayDi, DoiTuong, GiaVe) VALUES
('VT001', 'CN01', 'Nguyễn Văn A', 'CCCD123456789', '2024-10-10', 'Người lớn', 200000),
('VT002', 'CN02', 'Trần Thị B', 'CCCD987654321', '2024-10-11', 'Người lớn', 200000),
('VT003', 'CN03', 'Lê Văn C', 'CCCD123123123', '2024-10-12', 'Người lớn', 250000),
('VT004', 'CN04', 'Phạm Thị D', 'CCCD321321321', '2024-10-13', 'Người lớn', 250000),
('VT005', 'CN05', 'Nguyễn Văn E', 'CCCD456456456', '2024-10-14', 'Người lớn', 300000),
('VT006', 'CN06', 'Trần Thị F', 'CCCD654654654', '2024-10-15', 'Người lớn', 300000),
('VT007', 'CN07', 'Lê Văn G', 'CCCD789789789', '2024-10-16', 'Người lớn', 350000),
('VT008', 'CN08', 'Phạm Thị H', 'CCCD321456987', '2024-10-17', 'Người lớn', 350000),
('VT009', 'CN09', 'Nguyễn Văn I', 'CCCD654123789', '2024-10-18', 'Người lớn', 400000),
('VT010', 'CN10', 'Trần Thị J', 'CCCD987321654', '2024-10-19', 'Người lớn', 400000);

INSERT INTO NhanVien (MaNV, TenNV, SoDT, TrangThai, CCCD, DiaChi, NgayThamGia, ChucVu) VALUES
('NV001', 'Nguyễn Văn A', '0123456789', 'Đang làm việc', 'CCCD123456789', 'Sài Gòn', '2023-01-01', 'Nhân viên'),
('QL001', 'Trần Thị B', '0987654321', 'Đang làm việc', 'CCCD987654321', 'Hà Nội', '2023-02-01', 'Quản lý'),
('NV003', 'Lê Văn C', '0345678901', 'Đang nghỉ', 'CCCD123123123', 'Đà Nẵng', '2023-03-01', 'Nhân viên'),
('NV004', 'Phạm Thị D', '0765432189', 'Đang làm việc', 'CCCD321321321', 'Nha Trang', '2023-04-01', 'Nhân viên'),
('QL002', 'Nguyễn Văn E', '0123456780', 'Đang làm việc', 'CCCD456456456', 'Huế', '2023-05-01', 'Quản lý'),
('NV006', 'Trần Thị F', '0987654310', 'Đang nghỉ', 'CCCD654654654', 'Cần Thơ', '2023-06-01', 'Nhân viên'),
('NV007', 'Lê Văn G', '0345678902', 'Đang làm việc', 'CCCD789789789', 'Hải Phòng', '2023-07-01', 'Nhân viên'),
('QL003', 'Phạm Thị H', '0765432198', 'Đang làm việc', 'CCCD321456987', 'Biên Hòa', '2023-08-01', 'Quản lý'),
('NV009', 'Nguyễn Văn I', '0123456781', 'Đang nghỉ', 'CCCD654123789', 'Vinh', '2023-09-01', 'Nhân viên'),
('NV010', 'Trần Thị J', '0987654322', 'Đang làm việc', 'CCCD987321654', 'Hà Nội', '2023-10-01', 'Nhân viên');

INSERT INTO LoaiKhachHang (MaLoaiKH, TenLoaiKH) VALUES
('KH001', 'Khách hàng thường'),
('KH002', 'Khách hàng VIP'),
('KH003', 'Khách hàng doanh nghiệp'),
('KH004', 'Khách hàng thân thiết'),
('KH005', 'Khách hàng khuyến mãi');

INSERT INTO KhachHang (MaKH, LoaiKhachHangMaLoaiKH, SoDT, TenKH, DiaChi, CCCD, DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien) VALUES
('KH001', 'KH001', '0901234567', 'Nguyễn Văn A', 'Sài Gòn', 'CCCD001', 100, '1990-01-01', '2023-01-01', 'Bạc'),
('KH002', 'KH002', '0901234568', 'Trần Thị B', 'Hà Nội', 'CCCD002', 200, '1985-02-01', '2023-02-01', 'Vàng'),
('KH003', 'KH001', '0901234569', 'Lê Văn C', 'Đà Nẵng', 'CCCD003', 150, '1992-03-01', '2023-03-01', 'Bạc'),
('KH004', 'KH003', '0901234570', 'Phạm Thị D', 'Nha Trang', 'CCCD004', 300, '1995-04-01', '2023-04-01', 'Kim Cương'),
('KH005', 'KH004', '0901234571', 'Nguyễn Văn E', 'Huế', 'CCCD005', 50, '1988-05-01', '2023-05-01', 'Bạc'),
('KH006', 'KH001', '0901234572', 'Trần Thị F', 'Cần Thơ', 'CCCD006', 80, '1993-06-01', '2023-06-01', 'Thân thiết'),
('KH007', 'KH002', '0901234573', 'Lê Văn G', 'Hải Phòng', 'CCCD007', 120, '1987-07-01', '2023-07-01', 'Vàng'),
('KH008', 'KH003', '0901234574', 'Phạm Thị H', 'Biên Hòa', 'CCCD008', 400, '1982-08-01', '2023-08-01', 'Kim Cương'),
('KH009', 'KH001', '0901234575', 'Nguyễn Văn I', 'Vinh', 'CCCD009', 70, '1991-09-01', '2023-09-01', 'Bạc'),
('KH010', 'KH002', '0901234576', 'Trần Thị J', 'Hà Nội', 'CCCD010', 250, '1986-10-01', '2023-10-01', 'Vàng');

INSERT INTO KhuyenMai (MaKM, ThoiGianBatDau, ThoiGianKetThuc, NoiDungKM, ChietKhau, DoiTuongApDung) VALUES
('KM001', '2024-01-01', '2024-01-31', 'Giảm 10% cho khách hàng thân thiết', 10.0, 'Khách hàng thân thiết'),
('KM002', '2024-02-01', '2024-02-28', 'Giảm 15% cho khách hàng doanh nghiệp', 15.0, 'Khách hàng doanh nghiệp'),
('KM003', '2024-03-01', '2024-03-31', 'Giảm 5% cho khách hàng thường', 5.0, 'Khách hàng thường'),
('KM004', '2024-04-01', '2024-04-30', 'Giảm 20% cho đơn hàng trên 500.000 VNĐ', 20.0, 'Tất cả khách hàng'),
('KM005', '2024-05-01', '2024-05-31', 'Mua 2 tặng 1 cho vé tàu', 100.0, 'Tất cả khách hàng');

INSERT INTO TaiKhoan (MaNV, Password) VALUES
('NV001', 'password123'),
('QL001', 'admin123'),
('NV003', 'password456'),
('NV004', 'password789'),
('QL002', 'admin456'),
('NV006', 'password321'),
('NV007', 'password654'),
('QL003', 'admin789'),
('NV009', 'password987'),
('NV010', 'password234');

INSERT INTO LichLamViec (MaLichLamViec, MaNhanVien, GioBatDau, GioKetThuc, TrangThai, TenCa) VALUES
('LLV001', 'NV001', '08:00:00', '17:00:00', 'Đang làm việc', 'Ca sáng'),
('LLV002', 'QL001', '09:00:00', '18:00:00', 'Đang làm việc', 'Ca chiều'),
('LLV003', 'NV003', '08:00:00', '17:00:00', 'Đang nghỉ', 'Ca sáng'),
('LLV004', 'NV004', '08:00:00', '17:00:00', 'Đang làm việc', 'Ca sáng'),
('LLV005', 'QL002', '09:00:00', '18:00:00', 'Đang làm việc', 'Ca chiều'),
('LLV006', 'NV006', '08:00:00', '17:00:00', 'Đang nghỉ', 'Ca sáng'),
('LLV007', 'NV007', '08:00:00', '17:00:00', 'Đang làm việc', 'Ca sáng'),
('LLV008', 'QL003', '09:00:00', '18:00:00', 'Đang làm việc', 'Ca chiều'),
('LLV009', 'NV009', '08:00:00', '17:00:00', 'Đang nghỉ', 'Ca sáng'),
('LLV010', 'NV010', '08:00:00', '17:00:00', 'Đang làm việc', 'Ca sáng');

INSERT INTO LoaiHoaDon (MaLoai, TenLoaiHoaDon) VALUES
('LH001', 'Hóa đơn bán hàng'),
('LH002', 'Hóa đơn dịch vụ'),
('LH003', 'Hóa đơn thanh toán'),
('LH004', 'Hóa đơn xuất khẩu'),
('LH005', 'Hóa đơn nội địa');

INSERT INTO HoaDon (MaHD, MaKH, KhuyenMaiMaKM, NhanVienMaNV, MaLoai, NgayHoaDon, TienKhuyenMai, TongTien) VALUES
('HD001', 'KH001', 'KM001', 'NV001', 'LH001', '2024-01-01', 10.0, 90.0),
('HD002', 'KH002', 'KM002', 'QL001', 'LH002', '2024-02-01', 15.0, 85.0),
('HD003', 'KH003', NULL, 'NV003', 'LH003', '2024-03-01', 0.0, 150.0),
('HD004', 'KH004', 'KM003', 'QL002', 'LH004', '2024-04-01', 5.0, 295.0),
('HD005', 'KH005', NULL, 'NV004', 'LH005', '2024-05-01', 0.0, 50.0);

INSERT INTO ChiTietHoaDon (MaVe, MaHD, SoLuong, VAT, ThanhTien, TenThue) VALUES
('VT001', 'HD001', 1, 10.0, 220000, 'Thuế GTGT 10%'),
('VT002', 'HD001', 2, 10.0, 440000, 'Thuế GTGT 10%'),
('VT003', 'HD002', 1, 10.0, 275000, 'Thuế GTGT 10%'),
('VT004', 'HD002', 1, 10.0, 275000, 'Thuế GTGT 10%');
