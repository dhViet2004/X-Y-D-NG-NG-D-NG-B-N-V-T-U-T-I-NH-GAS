CREATE DATABASE UngDungQuanLyBanVeTaiGaGoVap;

CREATE TABLE NhanVien (
    MaNV VARCHAR(255) PRIMARY KEY,
    TenNV NVARCHAR(255) NOT NULL,
    SoDT VARCHAR(255),
	TrangThai NVARCHAR(255),
	CCCD VARCHAR(255),
    DiaChi NVARCHAR(255),
    NgayThamGia DATE,
    ChucVu NVARCHAR(50)
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
    TrangThai NVARCHAR(255),
    TenCa NVARCHAR(255),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNV)
);

CREATE TABLE LoaiKhachHang (
    MaLoaiKH NVARCHAR(255) PRIMARY KEY,
    TenLoaiKH NVARCHAR(255)
);



CREATE TABLE KhachHang (
    MaKH VARCHAR(255) PRIMARY KEY,
    LoaiKhachHangMaLoaiKH NVARCHAR(255),
    SoDT VARCHAR(255),
    TenKH NVARCHAR(255),
	CCCD VARCHAR(255),
    DiaChi NVARCHAR(255),
    DiemTichLuy INTEGER,
    NgaySinh DATE,
    NgayThamGia DATE,
    HangThanhVien NVARCHAR(255),
    FOREIGN KEY (LoaiKhachHangMaLoaiKH) REFERENCES LoaiKhachHang(MaLoaiKH)
);

CREATE TABLE KhuyenMai (
    MaKM VARCHAR(255) PRIMARY KEY,
    ThoiGianBatDau DATE,
    ThoiGianKetThuc DATE,
    NoiDungKM NVARCHAR(255),
    ChietKhau FLOAT,
    DoiTuongApDung NVARCHAR(255)
);

CREATE TABLE TuyenTau (
    MaTuyen VARCHAR(255) PRIMARY KEY,
	TenTuyen NVARCHAR(255),
    GaDi NVARCHAR(255),
    GaDen NVARCHAR(255),
    DiaDiemDi NVARCHAR(255),
    DiaDiemDen NVARCHAR(255)
);

CREATE TABLE Tau (
    MaTau VARCHAR(255) PRIMARY KEY,
    MaTuyen VARCHAR(255),
	TenTau NVARCHAR(255),
	SoToa Integer,
    FOREIGN KEY (MaTuyen) REFERENCES TuyenTau(MaTuyen)
);

CREATE TABLE LoaiToa (
    MaLoai VARCHAR(255) PRIMARY KEY,
    TenLoai NVARCHAR(255)
);

CREATE TABLE ToaTau (
    MaToa VARCHAR(255) PRIMARY KEY,
    LoaiToaMaLoai VARCHAR(255),
    TauMaTau VARCHAR(255),
    TenToa NVARCHAR(255),
    SoGhe INTEGER,
    ThuTu INTEGER,
    FOREIGN KEY (LoaiToaMaLoai) REFERENCES LoaiToa(MaLoai),
    FOREIGN KEY (TauMaTau) REFERENCES Tau(MaTau)
);

CREATE TABLE LoaiCho (
    MaLoai VARCHAR(255) PRIMARY KEY,
    TenLoai NVARCHAR(255)
);

CREATE TABLE ChoNgoi (
    MaCho VARCHAR(255) PRIMARY KEY,
    LoaiChoMaLoai VARCHAR(255),
	LoaiToaMaToa VARCHAR(255),
	TenCho NVARCHAR(255),
    TinhTrang BIT,
    GiaTien float,
    FOREIGN KEY (LoaiChoMaLoai) REFERENCES LoaiCho(MaLoai),
	FOREIGN KEY (LoaiToaMaToa) REFERENCES ToaTau(MaToa)
);

CREATE TABLE LichTrinhTau(
	MaLich nvarchar(255) not null primary key,
	MaTau varchar(255) foreign key(MaTau) references Tau(MaTau),
	GioDi varchar(20),
	NgayDi date	
)

CREATE TABLE VeTau (
    MaVe VARCHAR(255) PRIMARY KEY,
	LichTrinhTauMaLich NVARCHAR(255) FOREIGN KEY(LichTrinhTauMaLich) REFERENCES LichTrinhTau(MaLich),
    ChoNgoiMaCho VARCHAR(255),
	TenKH NVARCHAR(255),
	GiayTo NVARCHAR(255),
	NgayDi date,
	DoiTuong NVARCHAR(255),
	GiaVe float,
	TrangThai nvarchar(255),
    FOREIGN KEY (ChoNgoiMaCho) REFERENCES ChoNgoi(MaCho),
);


CREATE TABLE LoaiHoaDon (
    MaLoai VARCHAR(255) PRIMARY KEY,
    TenLoaiHoaDon NVARCHAR(255)
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
    TenThue NVARCHAR(255),
    PRIMARY KEY (MaVe, MaHD),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaVe) REFERENCES VeTau(MaVe)
);

INSERT INTO TuyenTau (MaTuyen, TenTuyen, GaDi, GaDen, DiaDiemDi, DiaDiemDen) VALUES
('TT01', 'SG-HN', N'Ga Sài Gòn', N'Ga Hà Nội', N'Sài Gòn', N'Hà Nội'),
('TT02', 'SG-NT', N'Ga Sài Gòn', N'Ga Nha Trang', N'Sài Gòn', N'Nha Trang'),
('TT03', 'SG-DN', N'Ga Sài Gòn', N'Ga Đà Nẵng', N'Sài Gòn', N'Đà Nẵng'),
('TT04', 'SG-PT', N'Ga Sài Gòn', N'Ga Phan Thiết', N'Sài Gòn', N'Phan Thiết'),
('TT05', 'SG-QN', N'Ga Sài Gòn', N'Ga Quy Nhơn', N'Sài Gòn', N'Quy Nhơn'),
('TT06', 'SG-QN', N'Ga Sài Gòn', N'Ga Quảng Ngãi', N'Sài Gòn', N'Quảng Ngãi'),
('TT07', 'SG-H', N'Ga Sài Gòn', N'Ga Huế', N'Sài Gòn', N'Huế'),
('TT08', 'SG-ND', N'Ga Sài Gòn', N'Ga Nam Định', N'Sài Gòn', N'Nam Định'),
('TT09', 'SG-V', N'Ga Sài Gòn', N'Ga Vinh', N'Sài Gòn', N'Vinh'),
('TT10', 'SG-TH', N'Ga Sài Gòn', N'Ga Thanh Hóa', N'Sài Gòn', N'Thanh Hóa'),
('TT11', 'SG-HP', N'Ga Sài Gòn', N'Ga Hải Phòng', N'Sài Gòn', N'Hải Phòng'),
('TT12', 'SG-DH', N'Ga Sài Gòn', N'Ga Đông Hà', N'Sài Gòn', N'Đông Hà'),
('TT13', 'SG-DH', N'Ga Sài Gòn', N'Ga Đồng Hới', N'Sài Gòn', N'Đồng Hới'),
('TT14', 'SG-TC', N'Ga Sài Gòn', N'Ga Tháp Chàm', N'Sài Gòn', N'Tháp Chàm'),
('TT15', 'SG-BH', N'Ga Sài Gòn', N'Ga Biên Hòa', N'Sài Gòn', N'Biên Hòa'),
('TT16', 'SG-ST', N'Ga Sài Gòn', N'Ga Sóng Thần', N'Sài Gòn', N'Sóng Thần'),
('TT17', 'SG-DA', N'Ga Sài Gòn', N'Ga Dĩ An', N'Sài Gòn', N'Dĩ An'),
('TT18', 'SG-SL', N'Ga Sài Gòn', N'Ga Sông Lũy', N'Sài Gòn', N'Sông Lũy'),
('TT19', 'SG-BT', N'Ga Sài Gòn', N'Ga Bình Thuận', N'Sài Gòn', N'Bình Thuận'),
('TT20', 'SG-PR', N'Ga Sài Gòn', N'Ga Phan Rang', N'Sài Gòn', N'Phan Rang'),
('TT21', 'SG-DL', N'Ga Sài Gòn', N'Ga Đà Lạt', N'Sài Gòn', N'Đà Lạt'),
('TT22', 'SG-DL', N'Ga Sài Gòn', N'Ga Đà Lạt', N'Sài Gòn', N'Đà Lạt'),
('TT23', 'SG-BMT', N'Ga Sài Gòn', N'Ga Buôn Ma Thuột', N'Sài Gòn', N'Buôn Ma Thuột'),
('TT24', 'SG-PL', N'Ga Sài Gòn', N'Ga Pleiku', N'Sài Gòn', N'Pleiku'),
('TT25', 'SG-KT', N'Ga Sài Gòn', N'Ga Kon Tum', N'Sài Gòn', N'Kon Tum'),
('TT26', 'SG-LS', N'Ga Sài Gòn', N'Ga Lạng Sơn', N'Sài Gòn', N'Lạng Sơn'),
('TT27', 'SG-CB', N'Ga Sài Gòn', N'Ga Cao Bằng', N'Sài Gòn', N'Cao Bằng'),
('TT28', 'SG-BK', N'Ga Sài Gòn', N'Ga Bắc Kạn', N'Sài Gòn', N'Bắc Kạn'),
('TT29', 'SG-HG', N'Ga Sài Gòn', N'Ga Hà Giang', N'Sài Gòn', N'Hà Giang'),
('TT30', 'SG-TQ', N'Ga Sài Gòn', N'Ga Tuyên Quang', N'Sài Gòn', N'Tuyên Quang'),
('TT31', 'SG-YB', N'Ga Sài Gòn', N'Ga Yên Bái', N'Sài Gòn', N'Yên Bái'),
('TT32', 'SG-LC', N'Ga Sài Gòn', N'Ga Lào Cai', N'Sài Gòn', N'Lào Cai'),
('TT33', 'SG-PT', N'Ga Sài Gòn', N'Ga Phú Thọ', N'Sài Gòn', N'Phú Thọ'),
('TT34', 'SG-HB', N'Ga Sài Gòn', N'Ga Hòa Bình', N'Sài Gòn', N'Hòa Bình'),
('TT35', 'SG-SL', N'Ga Sài Gòn', N'Ga Sơn La', N'Sài Gòn', N'Sơn La'),
('TT36', 'SG-LC', N'Ga Sài Gòn', N'Ga Lai Châu', N'Sài Gòn', N'Lai Châu'),
('TT37', 'SG-DB', N'Ga Sài Gòn', N'Ga Điện Biên', N'Sài Gòn', N'Điện Biên'),
('TT38', 'SG-TN', N'Ga Sài Gòn', N'Ga Thái Nguyên', N'Sài Gòn', N'Thái Nguyên'),
('TT39', 'SG-BN', N'Ga Sài Gòn', N'Ga Bắc Ninh', N'Sài Gòn', N'Bắc Ninh'),
('TT40', 'SG-BG', N'Ga Sài Gòn', N'Ga Bắc Giang', N'Sài Gòn', N'Bắc Giang'),
('TT41', 'SG-HY', N'Ga Sài Gòn', N'Ga Hưng Yên', N'Sài Gòn', N'Hưng Yên'),
('TT42', 'SG-HN', N'Ga Sài Gòn', N'Ga Hà Nam', N'Sài Gòn', N'Hà Nam'),
('TT43', 'SG-NB', N'Ga Sài Gòn', N'Ga Ninh Bình', N'Sài Gòn', N'Ninh Bình'),
('TT44', 'SG-ND', N'Ga Sài Gòn', N'Ga Nam Định', N'Sài Gòn', N'Nam Định'),
('TT45', 'SG-HD', N'Ga Sài Gòn', N'Ga Hải Dương', N'Sài Gòn', N'Hải Dương'),
('TT46', 'SG-TB', N'Ga Sài Gòn', N'Ga Thái Bình', N'Sài Gòn', N'Thái Bình'),
('TT47', 'SG-VP', N'Ga Sài Gòn', N'Ga Vĩnh Phúc', N'Sài Gòn', N'Vĩnh Phúc'),
('TT48', 'SG-BG', N'Ga Sài Gòn', N'Ga Bắc Giang', N'Sài Gòn', N'Bắc Giang'),
('TT49', 'SG-HN', N'Ga Sài Gòn', N'Ga Hà Nội', N'Sài Gòn', N'Hà Nội'),
('TT50', 'SG-QB', N'Ga Sài Gòn', N'Ga Quảng Bình', N'Sài Gòn', N'Quảng Bình');
select * from TuyenTau
INSERT INTO Tau (MaTau, MaTuyen, TenTau, SoToa) VALUES
('T01', 'TT01', 'SE1', 11),
('T02', 'TT02', 'SE2', 10),
('T03', 'TT03', 'SE3', 11),
('T04', 'TT04', 'SE4', 10),
('T05', 'TT05', 'SE5', 10),
('T06', 'TT06', 'SE6', 11),
('T07', 'TT07', 'SE7', 10),
('T08', 'TT08', 'SE8', 10),
('T09', 'TT09', 'SE9', 11),
('T10', 'TT10', 'SE10', 10),
('T11', 'TT11', 'SE11', 11),
('T12', 'TT12', 'SE12', 10),
('T13', 'TT13', 'SE13', 10),
('T14', 'TT14', 'SE14', 11),
('T15', 'TT15', 'SE15', 10),
('T16', 'TT16', 'SE16', 10),
('T17', 'TT17', 'SE17', 11),
('T18', 'TT18', 'SE18', 10),
('T19', 'TT19', 'SE19', 11),
('T20', 'TT20', 'SE20', 10),
('T21', 'TT21', 'SE21', 10),
('T22', 'TT22', 'SE22', 11),
('T23', 'TT23', 'SE23', 10),
('T24', 'TT24', 'SE24', 10),
('T25', 'TT25', 'SE25', 11),
('T26', 'TT26', 'SE26', 10),
('T27', 'TT27', 'SE27', 11),
('T28', 'TT28', 'SE28', 10),
('T29', 'TT29', 'SE29', 10),
('T30', 'TT30', 'SE30', 11),
('T31', 'TT31', 'SE31', 10),
('T32', 'TT32', 'SE32', 10),
('T33', 'TT33', 'SE33', 11),
('T34', 'TT34', 'SE34', 10),
('T35', 'TT35', 'SE35', 11),
('T36', 'TT36', 'SE36', 10),
('T37', 'TT37', 'SE37', 10),
('T38', 'TT38', 'SE38', 11),
('T39', 'TT39', 'SE39', 10),
('T40', 'TT40', 'SE40', 10),
('T41', 'TT41', 'SE41', 11),
('T42', 'TT42', 'SE42', 10),
('T43', 'TT43', 'SE43', 11),
('T44', 'TT44', 'SE44', 10),
('T45', 'TT45', 'SE45', 10),
('T46', 'TT46', 'SE46', 11),
('T47', 'TT47', 'SE47', 10),
('T48', 'TT48', 'SE48', 10),
('T49', 'TT49', 'SE49', 11),
('T50', 'TT50', 'SE50', 10);
select * from Tau
INSERT INTO LoaiToa (MaLoai, TenLoai) VALUES
('LT01', N'Ghế ngồi cứng'),
('LT02', N'Ghế ngồi mềm'),
('LT03', N'Ghế ngồi mềm điều hòa'),
('LT04', N'Giường nằm cứng'),
('LT05', N'Giường nằm mềm'),
('LT06', N'Ghế VIP'),
('LT07', N'Toa Thường loại 1'),
('LT08', N'Toa Thường loại 2'),
('LT09', N'Toa Thường loại 3'),
('LT10', N'Toa Thường loại 4'),
('LT11', N'Toa Thường loại 5'),
('LT12', N'Toa VIP loại 1'),
('LT13', N'Toa VIP loại 2'),
('LT14', N'Toa VIP loại 3'),
('LT15', N'Toa VIP loại 4'),
('LT16', N'Toa VIP loại 5'),
('LT17', N'Ghế ngồi cứng tầng 1'),
('LT18', N'Ghế ngồi cứng tầng 2'),
('LT19', N'Ghế ngồi mềm tầng 1'),
('LT20', N'Ghế ngồi mềm tầng 2'),
('LT21', N'Giường nằm cứng tầng 1'),
('LT22', N'Giường nằm cứng tầng 2'),
('LT23', N'Giường nằm mềm tầng 1'),
('LT24', N'Giường nằm mềm tầng 2'),
('LT25', N'Ghế ngồi đôi'),
('LT26', N'Ghế ngồi đơn'),
('LT27', N'Giường nằm đôi'),
('LT28', N'Giường nằm đơn'),
('LT29', N'Ghế ngồi phòng riêng'),
('LT30', N'Giường nằm phòng riêng'),
('LT31', N'Toa hành lý'),
('LT32', N'Toa hàng hóa'),
('LT33', N'Toa bưu điện'),
('LT34', N'Toa ăn uống'),
('LT35', N'Toa giải trí'),
('LT36', N'Toa điều hòa'),
('LT37', N'Toa nghỉ ngơi'),
('LT38', N'Toa đọc sách'),
('LT39', N'Toa chơi game'),
('LT40', N'Toa karaoke'),
('LT41', N'Toa gia đình'),
('LT42', N'Toa công tác'),
('LT43', N'Toa học tập'),
('LT44', N'Toa tập thể dục'),
('LT45', N'Toa yoga'),
('LT46', N'Toa chăm sóc sức khỏe'),
('LT47', N'Toa du lịch'),
('LT48', N'Toa thể thao'),
('LT49', N'Toa sinh hoạt cộng đồng'),
('LT50', N'Toa đa năng');
select * from LoaiToa

INSERT INTO ToaTau (MaToa, LoaiToaMaLoai, TauMaTau, TenToa, SoGhe, ThuTu) VALUES
('TT01', 'LT01', 'T01', 'Toa 1', 60, 1),
('TT02', 'LT02', 'T01', 'Toa 2', 50, 2),
('TT03', 'LT03', 'T01', 'Toa 3', 45, 3),
('TT04', 'LT04', 'T01', 'Toa 4', 40, 4),
('TT05', 'LT05', 'T01', 'Toa 5', 35, 5),
('TT06', 'LT06', 'T02', 'Toa 1', 50, 1),
('TT07', 'LT07', 'T02', 'Toa 2', 55, 2),
('TT08', 'LT08', 'T02', 'Toa 3', 45, 3),
('TT09', 'LT09', 'T02', 'Toa 4', 50, 4),
('TT10', 'LT10', 'T02', 'Toa 5', 60, 5),
('TT11', 'LT11', 'T03', 'Toa 1', 55, 1),
('TT12', 'LT12', 'T03', 'Toa 2', 40, 2),
('TT13', 'LT13', 'T03', 'Toa 3', 45, 3),
('TT14', 'LT14', 'T03', 'Toa 4', 50, 4),
('TT15', 'LT15', 'T03', 'Toa 5', 65, 5),
('TT16', 'LT16', 'T04', 'Toa 1', 70, 1),
('TT17', 'LT17', 'T04', 'Toa 2', 55, 2),
('TT18', 'LT18', 'T04', 'Toa 3', 45, 3),
('TT19', 'LT19', 'T04', 'Toa 4', 50, 4),
('TT20', 'LT20', 'T04', 'Toa 5', 60, 5),
('TT21', 'LT21', 'T05', 'Toa 1', 55, 1),
('TT22', 'LT22', 'T05', 'Toa 2', 50, 2),
('TT23', 'LT23', 'T05', 'Toa 3', 60, 3),
('TT24', 'LT24', 'T05', 'Toa 4', 45, 4),
('TT25', 'LT25', 'T05', 'Toa 5', 40, 5),
('TT26', 'LT26', 'T06', 'Toa 1', 35, 1),
('TT27', 'LT27', 'T06', 'Toa 2', 60, 2),
('TT28', 'LT28', 'T06', 'Toa 3', 50, 3),
('TT29', 'LT29', 'T06', 'Toa 4', 45, 4),
('TT30', 'LT30', 'T06', 'Toa 5', 40, 5),
('TT31', 'LT31', 'T07', 'Toa 1', 55, 1),
('TT32', 'LT32', 'T07', 'Toa 2', 65, 2),
('TT33', 'LT33', 'T07', 'Toa 3', 45, 3),
('TT34', 'LT34', 'T07', 'Toa 4', 40, 4),
('TT35', 'LT35', 'T07', 'Toa 5', 35, 5),
('TT36', 'LT36', 'T08', 'Toa 1', 30, 1),
('TT37', 'LT37', 'T08', 'Toa 2', 55, 2),
('TT38', 'LT38', 'T08', 'Toa 3', 60, 3),
('TT39', 'LT39', 'T08', 'Toa 4', 45, 4),
('TT40', 'LT40', 'T08', 'Toa 5', 50, 5),
('TT41', 'LT41', 'T09', 'Toa 1', 60, 1),
('TT42', 'LT42', 'T09', 'Toa 2', 55, 2),
('TT43', 'LT43', 'T09', 'Toa 3', 45, 3),
('TT44', 'LT44', 'T09', 'Toa 4', 50, 4),
('TT45', 'LT45', 'T09', 'Toa 5', 65, 5),
('TT46', 'LT46', 'T10', 'Toa 1', 70, 1),
('TT47', 'LT47', 'T10', 'Toa 2', 55, 2),
('TT48', 'LT48', 'T10', 'Toa 3', 60, 3),
('TT49', 'LT49', 'T10', 'Toa 4', 45, 4),
('TT50', 'LT50', 'T10', 'Toa 5', 50, 5);
select * from ToaTau
INSERT INTO LoaiCho (MaLoai, TenLoai) VALUES
('LC01', N'Ghế ngồi cứng'),
('LC02', N'Ghế ngồi mềm'),
('LC03', N'Ghế nằm cứng'),
('LC04', N'Ghế nằm mềm'),
('LC05', N'Ghế ngồi có điều hòa'),
('LC06', N'Ghế nằm có điều hòa'),
('LC07', N'Ghế ngồi hạng thường'),
('LC08', N'Ghế ngồi hạng sang'),
('LC09', N'Ghế nằm hạng thường'),
('LC10', N'Ghế nằm hạng sang');
select * from LoaiCho
INSERT INTO ChoNgoi (MaCho, LoaiChoMaLoai, LoaiToaMaToa, TenCho, TinhTrang, GiaTien) VALUES
('CN01', 'LC01', 'TT01', N'Chỗ 1A', 1, 200000),
('CN02', 'LC01', 'TT01', N'Chỗ 1B', 1, 200000),
('CN03', 'LC02', 'TT01', N'Chỗ 1C', 1, 250000),
('CN04', 'LC02', 'TT02', N'Chỗ 2A', 1, 250000),
('CN05', 'LC03', 'TT02', N'Chỗ 2B', 1, 300000),
('CN06', 'LC03', 'TT03', N'Chỗ 3A', 0, 300000),
('CN07', 'LC04', 'TT03', N'Chỗ 3B', 1, 350000),
('CN08', 'LC04', 'TT04', N'Chỗ 4A', 1, 350000),
('CN09', 'LC05', 'TT04', N'Chỗ 4B', 0, 400000),
('CN10', 'LC05', 'TT05', N'Chỗ 5A', 1, 400000);
select * from ChoNgoi
INSERT INTO VeTau (MaVe, LichTrinhTauMaLich, ChoNgoiMaCho, TenKH, GiayTo,NgayDi, DoiTuong, 	GiaVe ,TrangThai) VALUES
('TAU001-20241023-0269', 'LT002', 'CN11', N'Hoang Viet', '083204001968', '2024-10-05', N'Người lớn', 250000, N'Đã thanh toán'),
('TAU001-20241023-0475', 'LT002', 'CN29', N'A', 'A', '2024-10-05', N'Sinh viên', 250000, N'Đã thanh toán'),
('TAU001-20241023-2850', 'LT002', 'CN68', N'b', 'b', '2024-10-05', N'Trẻ nhỏ', 300000, N'Đã thanh toán'),
('TAU001-20241023-5335', 'LT002', 'CN30', N'B', 'B', '2024-10-05', N'Trẻ nhỏ', 250000, N'Đã thanh toán'),
('TAU001-20241023-5515', 'LT002', 'CN13', N'a', 'a', '2024-10-05', N'Sinh viên', 250000, N'Đã thanh toán'),
('TAU001-20241023-7005', 'LT002', 'CN12', N'My Nhan', '0123455678', '2024-10-05', N'Sinh viên', 250000, N'Đã thanh toán'),
('TAU001-20241023-7078', 'LT051', 'CN12', N'M N', '321', '2024-10-05', N'Sinh viên', 250000, N'Đã thanh toán'),
('TAU001-20241023-7467', 'LT051', 'CN11', N'H V', '123456', '2024-10-05', N'Trẻ nhỏ', 250000, N'Đã thanh toán');

INSERT INTO VeTau (MaVe, LichTrinhTauMaLich, ChoNgoiMaCho, TenKH, GiayTo,NgayDi, DoiTuong, 	GiaVe ,TrangThai) VALUES
('VT001', 'CN01', N'Nguyễn Văn An', '079123456789', '2024-10-10', N'Người lớn', 200000),
('VT002', 'CN02', N'Trần Thị Bình', '079146756789', '2024-10-11', N'Người lớn', 200000),
('VT003', 'CN03', N'Lê Văn Cẩn', '079123423489', '2024-10-12', N'Người lớn', 250000),
('VT004', 'CN04', N'Phạm Thị Dương', '076783456789', '2024-10-13', N'Người lớn', 250000),
('VT005', 'CN05', N'Nguyễn Văn Em', '079123456765', '2024-10-14', N'Người lớn', 300000),
('VT006', 'CN06', N'Trần Thị Phúc', '079123456159', '2024-10-15', N'Người lớn', 300000),
('VT007', 'CN07', N'Lê Văn Giang', '079123458369', '2024-10-16', N'Người lớn', 350000),
('VT008', 'CN08', N'Phạm Thị Huyền', '079123426889', '2024-10-17', N'Người lớn', 350000),
('VT009', 'CN09', N'Nguyễn Văn Nam', '071253456789', '2024-10-18', N'Người lớn', 400000),
('VT010', 'CN10', N'Trần Thị Loan', '018723456789', '2024-10-19', N'Người lớn', 400000);


select * from VeTau
INSERT INTO LichTrinhTau (MaLich, MaTau, GioDi, NgayDi) VALUES
('LT001', 'T01', '06:00:00', '2024-10-05'),
('LT002', 'T02', '08:30:00', '2024-10-05'),
('LT003', 'T03', '09:15:00', '2024-10-06'),
('LT004', 'T04', '07:45:00', '2024-10-06'),
('LT005', 'T05', '13:00:00', '2024-10-07'),
('LT006', 'T06', '15:30:00', '2024-10-08'),
('LT007', 'T07', '11:00:00', '2024-10-09'),
('LT008', 'T08', '05:45:00', '2024-10-10'),
('LT009', 'T09', '10:00:00', '2024-10-11'),
('LT010', 'T10', '14:00:00', '2024-10-12'),
('LT011', 'T11', '07:30:00', '2024-10-13'),
('LT012', 'T12', '06:15:00', '2024-10-14'),
('LT013', 'T13', '08:45:00', '2024-10-15'),
('LT014', 'T14', '17:00:00', '2024-10-16'),
('LT015', 'T15', '16:30:00', '2024-10-17'),
('LT016', 'T16', '05:30:00', '2024-10-18'),
('LT017', 'T17', '10:15:00', '2024-10-19'),
('LT018', 'T18', '12:30:00', '2024-10-20'),
('LT019', 'T19', '15:00:00', '2024-10-21'),
('LT020', 'T20', '09:45:00', '2024-10-22'),
('LT021', 'T21', '14:15:00', '2024-10-23'),
('LT022', 'T22', '16:45:00', '2024-10-24'),
('LT023', 'T23', '11:30:00', '2024-10-25'),
('LT024', 'T24', '13:45:00', '2024-10-26'),
('LT025', 'T25', '10:30:00', '2024-10-27'),
('LT026', 'T26', '18:15:00', '2024-10-28'),
('LT027', 'T27', '09:00:00', '2024-10-29'),
('LT028', 'T28', '06:30:00', '2024-10-30'),
('LT029', 'T29', '07:15:00', '2024-10-31'),
('LT030', 'T30', '11:00:00', '2024-11-01'),
('LT031', 'T31', '12:45:00', '2024-11-02'),
('LT032', 'T32', '06:30:00', '2024-11-03'),
('LT033', 'T33', '14:15:00', '2024-11-04'),
('LT034', 'T34', '13:00:00', '2024-11-05'),
('LT035', 'T35', '08:00:00', '2024-11-06'),
('LT036', 'T36', '07:45:00', '2024-11-07'),
('LT037', 'T37', '16:00:00', '2024-11-08'),
('LT038', 'T38', '09:30:00', '2024-11-09'),
('LT039', 'T39', '17:00:00', '2024-11-10'),
('LT040', 'T40', '10:30:00', '2024-11-11'),
('LT041', 'T41', '11:15:00', '2024-11-12'),
('LT042', 'T42', '15:45:00', '2024-11-13'),
('LT043', 'T43', '18:00:00', '2024-11-14'),
('LT044', 'T44', '14:30:00', '2024-11-15'),
('LT045', 'T45', '09:15:00', '2024-11-16'),
('LT046', 'T46', '07:30:00', '2024-11-17'),
('LT047', 'T47', '13:45:00', '2024-11-18'),
('LT048', 'T48', '06:00:00', '2024-11-19'),
('LT049', 'T49', '15:15:00', '2024-11-20'),
('LT050', 'T50', '16:30:00', '2024-11-21');
select * from LichTrinhTau
INSERT INTO NhanVien (MaNV, TenNV, SoDT, TrangThai, CCCD, DiaChi, NgayThamGia, ChucVu) VALUES
('NV001', N'Nguyễn Văn An', '0123456789', N'Đang làm việc', 'CCCD123456789', N'Sài Gòn', '2023-01-01', N'Nhân viên'),
('QL001', N'Trần Thị BÌnh', '0987654321', N'Đang làm việc', 'CCCD987654321', N'Hà Nội', '2023-02-01', N'Quản lý'),
('NV003', N'Lê Văn Cần', '0345678901', N'Đang nghỉ', 'CCCD123123123', N'Đà Nẵng', '2023-03-01', N'Nhân viên'),
('NV004', N'Phạm Thị Diễm My', '0765432189', N'Đang làm việc', 'CCCD321321321', N'Nha Trang', '2023-04-01', N'Nhân viên'),
('QL002', N'Nguyễn Văn Tùng', '0123456780', N'Đang làm việc', 'CCCD456456456', N'Huế', '2023-05-01', N'Quản lý'),
('NV006', N'Trần Thị Tư', '0987654310', N'Đang nghỉ', 'CCCD654654654', N'Cần Thơ', '2023-06-01', N'Nhân viên'),
('NV007', N'Lê Văn Gần', '0345678902', N'Đang làm việc', 'CCCD789789789', N'Hải Phòng', '2023-07-01', N'Nhân viên'),
('QL003', N'Phạm Thị Hiền', '0765432198', N'Đang làm việc', 'CCCD321456987', N'Biên Hòa', '2023-08-01', N'Quản lý'),
('NV009', N'Nguyễn Văn Mai', '0123456781', N'Đang nghỉ', 'CCCD654123789', N'Vinh', '2023-09-01', N'Nhân viên'),
('NV010', N'Trần Thị Mẫn', '0987654322', N'Đang làm việc', 'CCCD987321654', N'Hà Nội', '2023-10-01', N'Nhân viên');
select * from NhanVien
INSERT INTO LoaiKhachHang (MaLoaiKH, TenLoaiKH) VALUES
('KH001', N'Khách hàng thường'),
('KH002', N'Khách hàng VIP'),
('KH003', N'Khách hàng doanh nghiệp'),
('KH004', N'Khách hàng thân thiết'),
('KH005', N'Khách hàng khuyến mãi');
select * from LoaiKhachHang
INSERT INTO KhachHang (MaKH, LoaiKhachHangMaLoaiKH, SoDT, TenKH, DiaChi, CCCD, DiemTichLuy, NgaySinh, NgayThamGia, HangThanhVien) VALUES
('KH001', 'KH001', '0901234567', N'Nguyễn Văn An', N'Sài Gòn', 'CCCD001', 100, '1990-01-01', '2023-01-01', N'Bạc'),
('KH002', 'KH002', '0901234568', N'Trần Thị Bình', N'Hà Nội', 'CCCD002', 200, '1985-02-01', '2023-02-01', N'Vàng'),
('KH003', 'KH001', '0901234569', N'Lê Văn Tiền', N'Đà Nẵng', 'CCCD003', 150, '1992-03-01', '2023-03-01', N'Bạc'),
('KH004', 'KH003', '0901234570', N'Phạm Thị Cẩm Giang', N'Nha Trang', 'CCCD004', 300, '1995-04-01', '2023-04-01', N'Kim Cương'),
('KH005', 'KH004', '0901234571', N'Nguyễn Văn Mừng', N'Huế', 'CCCD005', 50, '1988-05-01', '2023-05-01', N'Bạc'),
('KH006', 'KH001', '0901234572', N'Trần Thị Bé Bốn', N'Cần Thơ', 'CCCD006', 80, '1993-06-01', '2023-06-01', N'Thân thiết'),
('KH007', 'KH002', '0901234573', N'Lê Văn Lai', N'Hải Phòng', 'CCCD007', 120, '1987-07-01', '2023-07-01', N'Vàng'),
('KH008', 'KH003', '0901234574', N'Phạm Thị Hồng', N'Biên Hòa', 'CCCD008', 400, '1982-08-01', '2023-08-01', N'Kim Cương'),
('KH009', 'KH001', '0901234575', N'Nguyễn Văn Tài', N'Vinh', 'CCCD009', 70, '1991-09-01', '2023-09-01', N'Bạc'),
('KH010', 'KH002', '0901234576', N'Trần Thị Diễm My', N'Hà Nội', 'CCCD010', 250, '1986-10-01', '2023-10-01', N'Vàng');
select * from KhachHang


INSERT INTO KhuyenMai (MaKM, ThoiGianBatDau, ThoiGianKetThuc, NoiDungKM, ChietKhau, DoiTuongApDung) VALUES
('KM001', '2024-01-01', '2024-01-31', N'Giảm 10% cho khách hàng thân thiết', 10.0, N'Khách hàng thân thiết'),
('KM002', '2024-02-01', '2024-02-28', N'Giảm 15% cho khách hàng doanh nghiệp', 15.0, N'Khách hàng doanh nghiệp'),
('KM003', '2024-03-01', '2024-03-31', N'Giảm 5% cho khách hàng thường', 5.0, N'Khách hàng thường'),
('KM004', '2024-04-01', '2024-04-30', N'Giảm 20% cho đơn hàng trên 500.000 VNĐ', 20.0, N'Tất cả khách hàng'),
('KM005', '2024-05-01', '2024-05-31', N'Mua 2 tặng 1 cho vé tàu', 100.0, N'Tất cả khách hàng');
select * from KhuyenMai
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
select * from TaiKhoan
INSERT INTO LichLamViec (MaLichLamViec, MaNhanVien, GioBatDau, GioKetThuc, TrangThai, TenCa) VALUES
('LLV001', 'NV001', '08:00:00', '17:00:00', N'Đang làm việc', N'Ca sáng'),
('LLV002', 'QL001', '09:00:00', '18:00:00', N'Đang làm việc', N'Ca chiều'),
('LLV003', 'NV003', '08:00:00', '17:00:00', N'Đang nghỉ', N'Ca sáng'),
('LLV004', 'NV004', '08:00:00', '17:00:00', N'Đang làm việc', N'Ca sáng'),
('LLV005', 'QL002', '09:00:00', '18:00:00', N'Đang làm việc', N'Ca chiều'),
('LLV006', 'NV006', '08:00:00', '17:00:00', N'Đang nghỉ', N'Ca sáng'),
('LLV007', 'NV007', '08:00:00', '17:00:00', N'Đang làm việc', N'Ca sáng'),
('LLV008', 'QL003', '09:00:00', '18:00:00', N'Đang làm việc', N'Ca chiều'),
('LLV009', 'NV009', '08:00:00', '17:00:00', N'Đang nghỉ', N'Ca sáng'),
('LLV010', 'NV010', '08:00:00', '17:00:00', N'Đang làm việc', N'Ca sáng');
select * from LichLamViec
INSERT INTO LoaiHoaDon (MaLoai, TenLoaiHoaDon) VALUES
('LH001', N'Hóa đơn bán hàng'),
('LH002', N'Hóa đơn dịch vụ'),
('LH003', N'Hóa đơn thanh toán'),
('LH004', N'Hóa đơn xuất khẩu'),
('LH005', N'Hóa đơn nội địa');
select * from LoaiHoaDon
INSERT INTO HoaDon (MaHD, MaKH, KhuyenMaiMaKM, NhanVienMaNV, MaLoai, NgayHoaDon, TienKhuyenMai, TongTien) VALUES
('HD001', 'KH001', 'KM001', 'NV001', 'LH001', '2024-01-01', 10.0, 90.0),
('HD002', 'KH002', 'KM002', 'QL001', 'LH002', '2024-02-01', 15.0, 85.0),
('HD003', 'KH003', NULL, 'NV003', 'LH003', '2024-03-01', 0.0, 150.0),
('HD004', 'KH004', 'KM003', 'QL002', 'LH004', '2024-04-01', 5.0, 295.0),
('HD005', 'KH005', NULL, 'NV004', 'LH005', '2024-05-01', 0.0, 50.0);
select * from HoaDon
INSERT INTO ChiTietHoaDon (MaVe, MaHD, SoLuong, VAT, ThanhTien, TenThue) VALUES
('VT001', 'HD001', 1, 10.0, 220000, N'Thuế GTGT 10%'),
('VT002', 'HD001', 2, 10.0, 440000, N'Thuế GTGT 10%'),
('VT003', 'HD002', 1, 10.0, 275000, N'Thuế GTGT 10%'),
('VT004', 'HD002', 1, 10.0, 275000, N'Thuế GTGT 10%');
select * from ChiTietHoaDon