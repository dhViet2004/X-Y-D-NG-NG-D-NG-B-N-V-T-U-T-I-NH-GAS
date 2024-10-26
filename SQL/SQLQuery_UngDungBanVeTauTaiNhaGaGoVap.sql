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

--DONE
select * from TuyenTau

--DONE
INSERT INTO Tau (MaTau, MaTuyen, TenTau, SoToa) VALUES
('HNSG1001', 'HN-SG-1', 'SE18', 10),
('HNSG1002', 'HN-SG-1', 'SE19', 11),
('HNSG1003', 'HN-SG-1', 'SE20', 9),
('SGHN1001', 'SG-HN-1', 'SE01', 10),
('SGHN1002', 'SG-HN-1', 'SE02', 9),
('SGNT1001', 'SG-NT-1', 'SE03', 10),
('SGNT1002', 'SG-NT-1', 'SE04', 9);
select * from Tau

--DONE
INSERT INTO LoaiToa (MaLoai, TenLoai) VALUES
('LT01', N'Toa giường nằm'),
('LT02', N'Toa ghế ngồi'),
select * from LoaiToa

--DONE
INSERT INTO ToaTau (MaToa, LoaiToaMaLoai, TauMaTau, TenToa, SoGhe, ThuTu) VALUES
('SGNT1001-1', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 1),
('SGNT1001-2', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 2),
('SGNT1001-3', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 3),
('SGNT1001-4', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 4),
('SGNT1001-5', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 5),
('SGNT1001-6', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 6),
('SGNT1001-7', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 7),
('SGNT1001-8', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 8),
('SGNT1001-9', 'LT02', 'SGNT1001', 'Toa giường nằm', 64, 9);
select * from ToaTau

--DONE
INSERT INTO LoaiCho (MaLoai, TenLoai) VALUES
('LC01', N'Ghế ngồi cứng'),
('LC02', N'Ghế ngồi mềm'),
('LC03', N'Giường nằm mềm'),
('LC04', N'Ghế nằm mềm'),
('LC05', N'Ghế ngồi có điều hòa'),
('LC06', N'Ghế nằm có điều hòa'),
('LC07', N'Ghế ngồi hạng thường'),
('LC08', N'Ghế ngồi hạng sang'),
('LC09', N'Ghế nằm hạng thường'),
('LC10', N'Ghế nằm hạng sang');
select * from LoaiCho

--DONE
select * from ChoNgoi

--DONE
INSERT INTO VeTau (MaVe, LichTrinhTauMaLich, ChoNgoiMaCho, TenKH, GiayTo, NgayDi, DoiTuong, GiaVe, TrangThai) VALUES
('SGNT100120241024034048-001', 'SGNT100124102601', 'CN01', 'a', 'a', '2024-10-26', 'Người lớn', 2000000, 'Đã thanh toán'),
('SGNT100120241024034721-001', 'SGNT100124102601', 'CN02', 'a', 'a', '2024-10-26', 'Sinh viên', 2000000, 'Đã thanh toán'),
('SGNT100120241024035601-001', 'SGNT100124102601', 'CN03', 'a', 'a', '2024-10-26', 'Sinh viên', 200000, 'Đã thanh toán'),
('SGNT100120241024040321-001', 'SGNT100124102601', 'CN04', 'a', 'a', '2024-10-26', 'Sinh viên', 200000, 'Đã thanh toán'),
('SGNT100120241024040738-002', 'SGNT100124102601', 'CN05', 'a', 'a', '2024-10-26', 'Người lớn', 200000, 'Đã thanh toán'),
('SGNT100120241024041740-001', 'SGNT100124102601', 'CN06', 'a', 'a', '2024-10-26', 'Người lớn', 200000, 'Đã thanh toán'),
('SGNT100120241024042835-001', 'SGNT100124102601', 'CN07A', 'a', 'a', '2024-10-26', 'Sinh viên', 200000, 'Đã thanh toán'),
('SGNT100120241024042835-002', 'SGNT100124102601', 'CN10ASGNT1001-2', 'a', 'a', '2024-10-26', 'Sinh viên', 200000, 'Đã thanh toán'),
('SGNT100120241024091751-001', 'SGNT100124102601', 'CN06B', 'a', 'a', '2024-10-26', 'Người lớn', 200000, 'Đã thanh toán'),
('SGNT100120241024091751-002', 'SGNT100124102601', 'CN07B', 'a', 'a', '2024-10-26', 'Người lớn', 200000, 'Đã thanh toán'),
('SGNT100120241024094438-001', 'SGNT100124102601', 'CN10B', 'Hoang Viet', '12345678', '2024-10-26', 'Sinh viên', 200000, 'Đã thanh toán'),
('SGNT100120241024095315-001', 'SGNT100124102601', 'CN08B', 'Tan', '12345', '2024-10-26', 'Người lớn', 200000, 'Đã thanh toán');
select * from VeTau
--DONE
INSERT INTO LichTrinhTau (MaLich, MaTau, GioDi, NgayDi, TrangThai) VALUES
('HNSG100124102401', 'HNSG1001', '08:00:00', '2024-10-24', 'Hoạt động'),
('HNSG100224102401', 'HNSG1002', '08:30:00', '2024-10-24', 'Hoạt động'),
('HNSG100324102401', 'HNSG1003', '09:00:00', '2024-10-24', 'Hoạt động'),
('SGHN100124102501', 'SGHN1001', '07:00:00', '2024-10-25', 'Hoạt động'),
('SGHN100224102501', 'SGHN1002', '07:30:00', '2024-10-25', 'Hoạt động'),
('SGNT100124102601', 'SGNT1001', '10:00:00', '2024-10-26', 'Hoạt động'),
('SGNT100124102603', 'SGNT1001', '11:00:00', '2024-10-26', 'Hoạt động'),
('SGNT100224102601', 'SGNT1002', '10:30:00', '2024-10-26', 'Hoạt động');
select * from LichTrinhTau

-- DONE
INSERT INTO NhanVien (MaNV, TenNV, SoDT, TrangThai, CCCD, DiaChi, NgayThamGia, ChucVu) VALUES
('NV001', N'Võ Phước Việt', '0123456789', N'Đang làm việc', '023456278635', N'Bến Tre', '2024-10-20', N'Nhân viên')
select * from NhanVien

--DONE
INSERT INTO LoaiKhachHang (MaLoaiKH, TenLoaiKH) VALUES
('KH001', N'Khách hàng thường'),
('KH002', N'Khách hàng VIP'),
('KH003', N'Khách hàng doanh nghiệp'),
('KH004', N'Khách hàng thân thiết'),
('KH005', N'Khách hàng khuyến mãi');
select * from LoaiKhachHang

--DONE
select * from KhachHang
delete from KhachHang where MaKH not in ('KH2024102404173335',
'KH2024102404283619',
'KH2024102409175125',
'KH2024102409443676',
'KH2024102409535155',
'KH2610240364601530',
'KH26102024150000',
'KH261024154715')
 -- DONE
INSERT INTO KhuyenMai (MaKM, ThoiGianBatDau, ThoiGianKetThuc, NoiDungKM, ChietKhau, DoiTuongApDung) VALUES
('KM001', '2024-01-01', '2024-01-31', N'Giảm 10% cho khách hàng thân thiết', 10.0, N'Khách hàng thân thiết'),
('KM002', '2024-02-01', '2024-02-28', N'Giảm 15% cho khách hàng doanh nghiệp', 15.0, N'Khách hàng doanh nghiệp'),
('KM003', '2024-03-01', '2024-03-31', N'Giảm 5% cho khách hàng thường', 5.0, N'Khách hàng thường'),
('KM004', '2024-04-01', '2024-04-30', N'Giảm 20% cho đơn hàng trên 500.000 VNĐ', 20.0, N'Tất cả khách hàng'),
('KM005', '2024-05-01', '2024-05-31', N'Mua 2 tặng 1 cho vé tàu', 100.0, N'Tất cả khách hàng');
select * from KhuyenMai

--DONE
INSERT INTO TaiKhoan (MaNV, Password) VALUES
('NV001', '123'),
select * from TaiKhoan

--CHƯA CÓ DỮ LIỆU
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

--DONE
INSERT INTO LoaiHoaDon (MaLoai, TenLoaiHoaDon) VALUES
('LHD01', N'Thanh toán'),
('LHD02', N'Trả vé'),
('LHD03', N'Đổi vé')
select * from LoaiHoaDon

--DONE
INSERT INTO HoaDon (MaHD, MaKH, KhuyenMaiMaKM, NhanVienMaNV, MaLoai, NgayHoaDon, TienKhuyenMai, TongTien) VALUES
('HD2024102404176796', 'KH2024102404173335', NULL, NULL, 'LHD01', '2024-10-24', 0, 200000),
('HD2024102404282322', 'KH2024102404283619', NULL, NULL, 'LHD01', '2024-10-24', 0, 220000),
('HD2024102409176770', 'KH2024102409175125', NULL, NULL, 'LHD01', '2024-10-24', 0, 400000),
('HD2024102409449115', 'KH2024102409443676', NULL, NULL, 'LHD01', '2024-10-24', 0, 110000),
('HD2024102409533459', 'KH2024102409535155', NULL, NULL, 'LHD01', '2024-10-24', 0, 200000);
select * from HoaDon


--DONE
INSERT INTO ChiTietHoaDon (MaVe, MaHD, SoLuong, VAT, ThanhTien, TenThue) VALUES
('SGNT100120241024041740-001', 'HD2024102404176796', 1, 0, 200000, 0),
('SGNT100120241024042835-001', 'HD2024102404282322', 1, 0, 110000, 0),
('SGNT100120241024042835-002', 'HD2024102404282322', 1, 0, 110000, 0),
('SGNT100120241024091751-001', 'HD2024102409176770', 1, 0, 200000, 0),
('SGNT100120241024091751-002', 'HD2024102409176770', 1, 0, 200000, 0),
('SGNT100120241024094438-001', 'HD2024102409449115', 1, 0, 110000, 0),
('SGNT100120241024095315-001', 'HD2024102409533459', 1, 0, 200000, 0);
select * from ChiTietHoaDon




