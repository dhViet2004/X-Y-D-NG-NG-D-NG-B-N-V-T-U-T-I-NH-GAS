USE [master]
GO
/****** Object:  Database [UngDungQuanLyBanVeTaiGaGoVap]    Script Date: 12/13/2024 4:52:34 PM ******/
CREATE DATABASE [UngDungQuanLyBanVeTaiGaGoVap]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'UngDungQuanLyBanVeTaiGaGoVap_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\UngDungQuanLyBanVeTaiGaGoVap.mdf' , SIZE = 3136KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'UngDungQuanLyBanVeTaiGaGoVap_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\UngDungQuanLyBanVeTaiGaGoVap.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UngDungQuanLyBanVeTaiGaGoVap].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET ARITHABORT OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET  ENABLE_BROKER 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET  MULTI_USER 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET DB_CHAINING OFF 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [UngDungQuanLyBanVeTaiGaGoVap]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[MaVe] [varchar](255) NOT NULL,
	[MaHD] [varchar](255) NOT NULL,
	[SoLuong] [int] NULL,
	[VAT] [float] NULL,
	[ThanhTien] [float] NULL,
	[TenThue] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaVe] ASC,
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChoNgoi]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChoNgoi](
	[MaCho] [varchar](255) NOT NULL,
	[LoaiChoMaLoai] [varchar](255) NULL,
	[LoaiToaMaToa] [varchar](255) NULL,
	[TenCho] [nvarchar](255) NULL,
	[TinhTrang] [bit] NULL,
	[GiaTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaCho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [varchar](255) NOT NULL,
	[MaKH] [varchar](255) NULL,
	[KhuyenMaiMaKM] [varchar](255) NULL,
	[NhanVienMaNV] [varchar](255) NULL,
	[MaLoai] [varchar](255) NULL,
	[TienKhuyenMai] [float] NULL,
	[TongTien] [float] NULL,
	[NgayHoaDon] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKH] [varchar](255) NOT NULL,
	[LoaiKhachHangMaLoaiKH] [nvarchar](255) NULL,
	[SoDT] [varchar](255) NULL,
	[TenKH] [nvarchar](255) NULL,
	[CCCD] [varchar](255) NULL,
	[DiaChi] [nvarchar](255) NULL,
	[DiemTichLuy] [int] NULL,
	[NgaySinh] [date] NULL,
	[NgayThamGia] [date] NULL,
	[HangThanhVien] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[MaKM] [varchar](255) NOT NULL,
	[ThoiGianBatDau] [date] NULL,
	[ThoiGianKetThuc] [date] NULL,
	[NoiDungKM] [nvarchar](255) NULL,
	[ChietKhau] [float] NULL,
	[DoiTuongApDung] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LichLamViec]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LichLamViec](
	[MaLichLamViec] [varchar](255) NOT NULL,
	[MaNhanVien] [varchar](255) NULL,
	[GioBatDau] [datetime] NULL,
	[GioKetThuc] [datetime] NULL,
	[TrangThai] [nvarchar](255) NULL,
	[TenCa] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLichLamViec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LichTrinhTau]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LichTrinhTau](
	[MaLich] [nvarchar](255) NOT NULL,
	[MaTau] [varchar](255) NULL,
	[GioDi] [time](0) NULL,
	[NgayDi] [date] NULL,
	[TrangThai] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLich] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiCho]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiCho](
	[MaLoai] [varchar](255) NOT NULL,
	[TenLoai] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiHoaDon]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiHoaDon](
	[MaLoai] [varchar](255) NOT NULL,
	[TenLoaiHoaDon] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiKhachHang]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiKhachHang](
	[MaLoaiKH] [nvarchar](255) NOT NULL,
	[TenLoaiKH] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LoaiToa]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiToa](
	[MaLoai] [varchar](255) NOT NULL,
	[TenLoai] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [varchar](255) NOT NULL,
	[TenNV] [nvarchar](255) NOT NULL,
	[SoDT] [varchar](255) NULL,
	[TrangThai] [nvarchar](255) NULL,
	[CCCD] [varchar](255) NULL,
	[DiaChi] [nvarchar](255) NULL,
	[NgayThamGia] [date] NULL,
	[ChucVu] [nvarchar](50) NULL,
	[Avata] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[MaNV] [varchar](255) NOT NULL,
	[Password] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Tau]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tau](
	[MaTau] [varchar](255) NOT NULL,
	[MaTuyen] [varchar](255) NULL,
	[TenTau] [nvarchar](255) NULL,
	[SoToa] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ToaTau]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ToaTau](
	[MaToa] [varchar](255) NOT NULL,
	[LoaiToaMaLoai] [varchar](255) NULL,
	[TauMaTau] [varchar](255) NULL,
	[TenToa] [nvarchar](255) NULL,
	[SoGhe] [int] NULL,
	[ThuTu] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaToa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TuyenTau]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TuyenTau](
	[MaTuyen] [varchar](255) NOT NULL,
	[TenTuyen] [nvarchar](255) NULL,
	[GaDi] [nvarchar](255) NULL,
	[GaDen] [nvarchar](255) NULL,
	[DiaDiemDi] [nvarchar](255) NULL,
	[DiaDiemDen] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[VeTau]    Script Date: 12/13/2024 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[VeTau](
	[MaVe] [varchar](255) NOT NULL,
	[LichTrinhTauMaLich] [nvarchar](255) NULL,
	[ChoNgoiMaCho] [varchar](255) NULL,
	[TenKH] [nvarchar](255) NULL,
	[GiayTo] [nvarchar](255) NULL,
	[NgayDi] [date] NULL,
	[DoiTuong] [nvarchar](255) NULL,
	[GiaVe] [float] NULL,
	[TrangThai] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaVe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([MaVe])
REFERENCES [dbo].[VeTau] ([MaVe])
GO
ALTER TABLE [dbo].[ChoNgoi]  WITH CHECK ADD FOREIGN KEY([LoaiChoMaLoai])
REFERENCES [dbo].[LoaiCho] ([MaLoai])
GO
ALTER TABLE [dbo].[ChoNgoi]  WITH CHECK ADD FOREIGN KEY([LoaiToaMaToa])
REFERENCES [dbo].[ToaTau] ([MaToa])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([KhuyenMaiMaKM])
REFERENCES [dbo].[KhuyenMai] ([MaKM])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKH])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([MaLoai])
REFERENCES [dbo].[LoaiHoaDon] ([MaLoai])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([NhanVienMaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD FOREIGN KEY([LoaiKhachHangMaLoaiKH])
REFERENCES [dbo].[LoaiKhachHang] ([MaLoaiKH])
GO
ALTER TABLE [dbo].[LichLamViec]  WITH CHECK ADD FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[LichTrinhTau]  WITH CHECK ADD FOREIGN KEY([MaTau])
REFERENCES [dbo].[Tau] ([MaTau])
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[Tau]  WITH CHECK ADD FOREIGN KEY([MaTuyen])
REFERENCES [dbo].[TuyenTau] ([MaTuyen])
GO
ALTER TABLE [dbo].[ToaTau]  WITH CHECK ADD FOREIGN KEY([LoaiToaMaLoai])
REFERENCES [dbo].[LoaiToa] ([MaLoai])
GO
ALTER TABLE [dbo].[ToaTau]  WITH CHECK ADD FOREIGN KEY([TauMaTau])
REFERENCES [dbo].[Tau] ([MaTau])
GO
ALTER TABLE [dbo].[VeTau]  WITH CHECK ADD FOREIGN KEY([ChoNgoiMaCho])
REFERENCES [dbo].[ChoNgoi] ([MaCho])
GO
ALTER TABLE [dbo].[VeTau]  WITH CHECK ADD FOREIGN KEY([LichTrinhTauMaLich])
REFERENCES [dbo].[LichTrinhTau] ([MaLich])
GO
USE [master]
GO
ALTER DATABASE [UngDungQuanLyBanVeTaiGaGoVap] SET  READ_WRITE 
GO
