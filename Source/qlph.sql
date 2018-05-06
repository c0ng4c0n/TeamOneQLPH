-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2018 at 04:52 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlph`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `username` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `type` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`username`, `password`, `type`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3', 'Nhân viên'),
('B10D47', '2280a49ca1babd7cf79ab12b9d8fd5c1', 'Lớp học'),
('B10D49', '8cc814f39bf5c2e2650d91a0b07cc43c', 'Lớp học'),
('B11D48', '22f147e4453ce9e7dbef36a850341000', 'Lớp học'),
('B14D47', 'e5769a14ae36f87939ea55141d80b60', 'Lớp học'),
('B16D47', '787e7c762d64931d023b885542252306', 'Lớp học'),
('B17D47', 'e4f7c227dd6e1318dc80607e43101993', 'Lớp học'),
('B20D47', '47cc893b83fa9c4868075d272f75f4de', 'Lớp học'),
('GV009', 'bee0471cf52632b41b51b59fd6f6ef5f', 'Giáo viên'),
('GV022', '7fcabbb347a7b1e0d228c93627301152', 'Giáo viên'),
('GV028', '7e858b7b21d70a1d4d11906af82843a9', 'Giáo viên'),
('GV045', '55d26aa629febe0d0d22fc02f6f7f90b', 'Giáo viên'),
('GV099', '815dfaad611c043efd8c37a9265dbb41', 'Giáo viên'),
('GV113', 'b85db86dcf56df7fd95c56e7a0909d8b', 'Giáo viên'),
('GV169', '89e64c200b99c41e6dc50a550449c0f', 'Giáo viên'),
('GV266', 'a2150b3d1f24cc72792ceb09d39f4001', 'Giáo viên'),
('NV023', '1888d0e56d739cd7c7c28bdfc1565dbf', 'Nhân viên'),
('NV211', 'f4831b919cdad4efd7751f07155c616b', 'Nhân viên'),
('NV226', 'a0474636fd9e04874fa3031bd8926d74', 'Nhân viên'),
('NV341', 'dbdbc22a6716630cd1cb0c50b401e97f', 'Nhân viên'),
('NV412', '719e02e74c9304299d7b1c84e532100f', 'Nhân viên'),
('NV451', '6965b69a6e6f39b6518088938112dbd6', 'Nhân viên');

-- --------------------------------------------------------

--
-- Table structure for table `day_hoc`
--

CREATE TABLE `day_hoc` (
  `MaLop` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaGV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MonHoc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `YCMayChieu` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL,
  `YCMicro` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `day_hoc`
--

INSERT INTO `day_hoc` (`MaLop`, `MaGV`, `MonHoc`, `YCMayChieu`, `YCMicro`) VALUES
('B12D46', '412', 'Toán cao cấp A1', 'Có', 'Không'),
('B15D47', '256', 'Lý luận chung', 'Không', 'Có'),
('B16D47', '123', 'Cơ sở dữ liệu', 'Có', 'Không'),
('B17D47', '321', 'Tin học cơ sở', 'Không', 'Có'),
('B1D46', '120', 'Tâm lý học', 'Có', 'Có');

-- --------------------------------------------------------

--
-- Table structure for table `giang_day`
--

CREATE TABLE `giang_day` (
  `MaGV` varchar(10) COLLATE utf8_vietnamese_ci NOT NULL,
  `MaLop` varchar(10) COLLATE utf8_vietnamese_ci NOT NULL,
  `MonHoc` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `giang_day`
--

INSERT INTO `giang_day` (`MaGV`, `MaLop`, `MonHoc`) VALUES
('GV007', 'B16D47', 'PTTKHTTT'),
('GV009', 'B20D47', 'Luật tố tụng hình sự'),
('GV022', 'B11D48', 'Tin học cơ sở'),
('GV028', 'B20D47', 'Triết học Mác Lê-nin'),
('GV169', 'B16D47', 'Cơ sở dữ liệu');

-- --------------------------------------------------------

--
-- Table structure for table `giao_vien`
--

CREATE TABLE `giao_vien` (
  `MaGV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `HoTenGV` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` enum('Nam','Nữ') COLLATE utf8_unicode_ci NOT NULL,
  `NamSinh` year(4) NOT NULL,
  `Email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Khoa` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `giao_vien`
--

INSERT INTO `giao_vien` (`MaGV`, `HoTenGV`, `GioiTinh`, `NamSinh`, `Email`, `Khoa`) VALUES
('GV007', 'James Born', 'Nữ', 1971, 'jamesborn@gmail.com', 'Ngoại ngữ'),
('GV009', 'Nguyễn Văn Trọng', 'Nam', 1981, 'trongnguyen@gmail.com', 'Trinh sát ngoại tuyến'),
('GV022', 'Tạ Minh Chính', 'Nam', 1983, 'minhta@gmail.com', 'Công nghệ và an ninh thông tin'),
('GV028', 'Lê Thị Minh Thư', 'Nữ', 1995, 'minhlethu@yahoo.com', 'Triết học'),
('GV045', 'Nguyễn Trọng Tài', 'Nam', 1994, 'taichodien@gmail.com', 'Quản lý nhà nước'),
('GV099', 'Vũ Thị Hà', 'Nữ', 1999, 'bahavu@yahoo.com', 'Tham mưu chỉ huy'),
('GV113', 'Lê Văn Bảo Bình', 'Nam', 1980, 'binhlevan@gmail.com', 'Công nghệ và an toàn thông tin'),
('GV169', 'Nguyễn Văn Chí', 'Nam', 1982, 'chivan@gmail.com', 'Chống gián điệp'),
('GV266', 'Phù Văn Quất', 'Nam', 1990, 'quatphu97mdbg@gmial.com', 'Công Nghệ thông tin');

-- --------------------------------------------------------

--
-- Table structure for table `giu_chia_khoa`
--

CREATE TABLE `giu_chia_khoa` (
  `MaGV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaPhong` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TGBatDau` datetime NOT NULL,
  `TGKetThuc` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `giu_chia_khoa`
--

INSERT INTO `giu_chia_khoa` (`MaGV`, `MaPhong`, `TGBatDau`, `TGKetThuc`) VALUES
('233', '609-N7', '2017-09-10 09:30:00', '2017-09-15 10:20:00');

-- --------------------------------------------------------

--
-- Table structure for table `lop_hoc`
--

CREATE TABLE `lop_hoc` (
  `MaLop` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `TenLop` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SiSo` int(11) NOT NULL,
  `HoTenLT` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinhLT` enum('Nam','Nữ') COLLATE utf8_unicode_ci NOT NULL,
  `NamSinhLT` year(4) NOT NULL,
  `HoTenCN` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinhCN` enum('Nam','Nữ') COLLATE utf8_unicode_ci NOT NULL,
  `NamSinhCN` year(4) NOT NULL,
  `ChucVuCN` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lop_hoc`
--

INSERT INTO `lop_hoc` (`MaLop`, `TenLop`, `Email`, `SiSo`, `HoTenLT`, `GioiTinhLT`, `NamSinhLT`, `HoTenCN`, `GioiTinhCN`, `NamSinhCN`, `ChucVuCN`) VALUES
('B10D47', 'Bảo vệ nội bộ', 'bvnb49@gmail.com', 32, 'Bùi Gia Long', 'Nam', 1998, 'Nguyễn Thị Nguyệt', 'Nữ', 1990, 'Chủ nhiệm trung đội'),
('B10D49', 'Bảo vệ nội bộ', 'attt1747@gmail.com', 42, 'Trần Việt Nhật', 'Nam', 1992, 'Nguyễn Văn Long', 'Nam', 1991, 'Phó phòng QLHV'),
('B11D48', 'Quản lý nhà nước', 'qlnnd48@gmail.com', 44, 'Nguyễn Trí Thái', 'Nữ', 1998, 'Lương Văn Liễu', 'Nam', 1970, 'Chủ nhiệm trung đội'),
('B14D47', 'Tham mưu chỉ huy', 'tmch472@gmail.com', 45, 'Triệu Tử Long', 'Nữ', 1992, 'Nguyễn Xuân Dương', 'Nam', 1980, 'Trưởng phòng QLHV CK3'),
('B16D47', 'Công nghệ thông tin', 'b16d47hvan@gmail.com', 23, 'Hoàng Minh Thông', 'Nam', 1997, 'Lương Tuấn Anh', 'Nam', 1991, 'Trưởng phòng quản lý học viên'),
('B17D47', 'An toàn thông tin', 'antt47@gmail.com', 20, 'Nguyễn Thế Hoàng', 'Nam', 1991, 'Lương Tuấn Anh', 'Nam', 1991, 'Trưởng phòng quản lý học viên'),
('B20D47', 'Pháp luật', 'luat47hvan@gmail.com', 33, 'Nguyễn Anh Tú', 'Nam', 1993, 'Vũ Thị Hương', 'Nữ', 1988, 'Chủ nhiệm trung đội');

-- --------------------------------------------------------

--
-- Table structure for table `muon_phong`
--

CREATE TABLE `muon_phong` (
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaLop` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaPhong` varchar(10) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `TGBatDau` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TGKetThuc` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `HoTenNM` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SDTNM` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `MayChieu` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL,
  `Micro` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL,
  `DKDieuHoa` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `muon_phong`
--

INSERT INTO `muon_phong` (`MaNV`, `MaLop`, `MaPhong`, `TGBatDau`, `TGKetThuc`, `HoTenNM`, `SDTNM`, `MayChieu`, `Micro`, `DKDieuHoa`) VALUES
('admin', 'B11D48', '210-N6', '2018-04-11 09:07:54', '2000-12-31 17:00:00', 'Như Thị Linh', '0987654565', 'Có', 'Có', 'Có'),
('admin', 'B16D47', '102-N6', '2018-04-06 09:09:33', '2018-04-06 09:10:37', 'Phù Văn Quất', '0987654321', 'Có', 'Có', 'Có'),
('admin', 'B16D47', '102-N6', '2018-04-11 09:19:28', '2000-12-31 17:00:00', 'Tạ Hồng Giang', '0962600693', 'Có', 'Không', 'Không'),
('admin', 'B16D47', '102-N7', '2018-04-05 01:51:41', '2018-04-05 02:09:16', 'Lê Văn Long', '01658741236', 'Có', 'Có', 'Không'),
('admin', 'B16D47', '102-N7', '2018-04-11 12:56:14', '2000-12-31 17:00:00', 'Nguyễn Văn Long', '01963424223', 'Không', 'Có', 'Có'),
('admin', 'B16D47', '103-N7', '2018-04-11 09:05:06', '2000-12-31 17:00:00', 'Phù Văn Quất', '0987654321', 'Có', 'Không', 'Không'),
('admin', 'B16D47', '105-N6', '2018-04-06 17:02:47', '2018-04-11 09:03:44', 'Sái Văn Tray', '0698332485', 'Có', 'Có', 'Có'),
('admin', 'B16D47', '106-N7', '2018-05-01 05:39:30', '2018-05-01 05:40:04', 'Hhhh', 'asdsa', 'Có', 'Không', 'Có'),
('NV211', 'B20D47', '105-N6', '2018-04-23 16:53:34', '2000-12-31 17:00:00', 'Lê Trọng Hùng', '05562546261', 'Có', 'Có', 'Không');

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `HoTenNV` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` enum('Nam','Nữ') COLLATE utf8_unicode_ci NOT NULL,
  `NamSinh` year(4) NOT NULL,
  `ChucVu` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MaNVLanhDao` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`MaNV`, `HoTenNV`, `GioiTinh`, `NamSinh`, `ChucVu`, `MaNVLanhDao`) VALUES
('NV023', 'Nguyễn Ngọc Long', 'Nam', 1992, 'Quản lý mượn trả phòng', 'NV211'),
('NV211', 'Hoàng Thị Hoa', 'Nữ', 1987, 'Quản lý nhân viên', 'NV211'),
('NV226', 'Tạ Thị Tú Minh', 'Nữ', 1990, 'Vệ sinh', 'NV341'),
('NV341', 'Lê Thị Thu Cúc', 'Nữ', 1990, 'Vệ sinh', 'NV341'),
('NV412', 'Kim Binh Chính', 'Nam', 1987, 'Quản lý', 'NV211'),
('NV451', 'Trần Văn Thế', 'Nam', 1992, 'Vệ sinh', 'NV211');

-- --------------------------------------------------------

--
-- Table structure for table `phong_hoc`
--

CREATE TABLE `phong_hoc` (
  `MaPhong` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `LoaiPhong` enum('Phòng thường','Phòng máy','Hội trường') COLLATE utf8_unicode_ci NOT NULL,
  `TinhTrang` enum('Đang học','Trống','Đang sửa') COLLATE utf8_unicode_ci NOT NULL,
  `SucChua` int(3) NOT NULL,
  `MayChieu` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL,
  `DieuHoa` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL,
  `Micro` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL,
  `Camera` enum('Có','Không') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phong_hoc`
--

INSERT INTO `phong_hoc` (`MaPhong`, `LoaiPhong`, `TinhTrang`, `SucChua`, `MayChieu`, `DieuHoa`, `Micro`, `Camera`) VALUES
('102-N6', 'Phòng thường', 'Đang học', 23, 'Có', 'Không', 'Có', 'Có'),
('102-N7', 'Phòng thường', 'Đang học', 60, 'Có', 'Có', 'Có', 'Không'),
('103-N7', 'Phòng máy', 'Đang học', 45, 'Có', 'Không', 'Có', 'Không'),
('105-N6', 'Phòng thường', 'Đang học', 45, 'Có', 'Có', 'Có', 'Có'),
('106-N7', 'Phòng máy', 'Trống', 34, 'Có', 'Không', 'Có', 'Có'),
('107-HV5', 'Phòng thường', 'Trống', 44, 'Không', 'Có', 'Có', 'Không'),
('107-N6', 'Phòng thường', 'Trống', 24, 'Không', 'Có', 'Có', 'Không'),
('107-N7', 'Hội trường', 'Đang sửa', 65, 'Không', 'Không', 'Không', 'Có'),
('203-HV5', 'Hội trường', 'Trống', 60, 'Có', 'Không', 'Có', 'Có'),
('203-N7', 'Phòng máy', 'Trống', 45, 'Có', 'Không', 'Không', 'Có'),
('204-HV5', 'Phòng thường', 'Đang sửa', 34, 'Không', 'Có', 'Có', 'Có'),
('210-N6', 'Phòng thường', 'Đang học', 23, 'Có', 'Không', 'Không', 'Không'),
('309-N6', 'Phòng thường', 'Trống', 34, 'Có', 'Có', 'Có', 'Có'),
('404-N6', 'Hội trường', 'Đang sửa', 55, 'Không', 'Không', 'Không', 'Không'),
('404-N7', 'Phòng thường', 'Trống', 48, 'Có', 'Không', 'Có', 'Có');

-- --------------------------------------------------------

--
-- Table structure for table `quan_ly`
--

CREATE TABLE `quan_ly` (
  `MaPhong` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TGBatDau` datetime NOT NULL,
  `TGKetThuc` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `quan_ly`
--

INSERT INTO `quan_ly` (`MaPhong`, `MaNV`, `TGBatDau`, `TGKetThuc`) VALUES
('306-N6', '23', '2017-06-26 07:30:00', '2017-06-26 09:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `sdt_chu_nhiem`
--

CREATE TABLE `sdt_chu_nhiem` (
  `MaLop` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `SDTCN` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sdt_chu_nhiem`
--

INSERT INTO `sdt_chu_nhiem` (`MaLop`, `SDTCN`) VALUES
('B16D47', '098737414');

-- --------------------------------------------------------

--
-- Table structure for table `sdt_giao_vien`
--

CREATE TABLE `sdt_giao_vien` (
  `MaGV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SDTGV` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sdt_giao_vien`
--

INSERT INTO `sdt_giao_vien` (`MaGV`, `SDTGV`) VALUES
('233', '0123456789'),
('233', '0987654321'),
('244', '01653628121');

-- --------------------------------------------------------

--
-- Table structure for table `sdt_khoa`
--

CREATE TABLE `sdt_khoa` (
  `Khoa` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SDTKhoa` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sdt_khoa`
--

INSERT INTO `sdt_khoa` (`Khoa`, `SDTKhoa`) VALUES
('Công nghệ và an toàn thông tin', '0987346237'),
('Luật', '0834789515'),
('Nghiệp vụ cơ sở', '0182459712');

-- --------------------------------------------------------

--
-- Table structure for table `sdt_lop_truong`
--

CREATE TABLE `sdt_lop_truong` (
  `MaLop` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `SDTLT` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sdt_lop_truong`
--

INSERT INTO `sdt_lop_truong` (`MaLop`, `SDTLT`) VALUES
('1', '1655229426'),
('1', '962600693'),
('2', '1645373813'),
('2', '898989891'),
('2', '987654321');

-- --------------------------------------------------------

--
-- Table structure for table `sdt_nhan_vien`
--

CREATE TABLE `sdt_nhan_vien` (
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SDTNV` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sdt_nhan_vien`
--

INSERT INTO `sdt_nhan_vien` (`MaNV`, `SDTNV`) VALUES
('333', '01348963894');

-- --------------------------------------------------------

--
-- Table structure for table `so_dien_thoai`
--

CREATE TABLE `so_dien_thoai` (
  `MaSo` varchar(10) COLLATE utf8_vietnamese_ci NOT NULL,
  `SoDienThoai` varchar(20) COLLATE utf8_vietnamese_ci NOT NULL,
  `PhanLoai` enum('Lớp trưởng','GV chủ nhiệm','Giáo viên','Nhân viên') COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `so_dien_thoai`
--

INSERT INTO `so_dien_thoai` (`MaSo`, `SoDienThoai`, `PhanLoai`) VALUES
('B11D48', '06548555456', 'Lớp trưởng'),
('B16D47', '0255656665', 'GV chủ nhiệm'),
('B16D47', '0987654333', 'Lớp trưởng'),
('B20D47', '0237893043723', 'Lớp trưởng'),
('GV266', '01696805432', 'Giáo viên'),
('GV266', '02163786372', 'Giáo viên');

-- --------------------------------------------------------

--
-- Table structure for table `su_dung_phong`
--

CREATE TABLE `su_dung_phong` (
  `MaGV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaLop` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `MaPhong` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TGBatDau` datetime NOT NULL,
  `TGKetThuc` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `day_hoc`
--
ALTER TABLE `day_hoc`
  ADD PRIMARY KEY (`MaLop`,`MaGV`),
  ADD KEY `dạy_gv_idx` (`MaGV`);

--
-- Indexes for table `giang_day`
--
ALTER TABLE `giang_day`
  ADD PRIMARY KEY (`MaGV`,`MaLop`,`MonHoc`);

--
-- Indexes for table `giao_vien`
--
ALTER TABLE `giao_vien`
  ADD PRIMARY KEY (`MaGV`);

--
-- Indexes for table `giu_chia_khoa`
--
ALTER TABLE `giu_chia_khoa`
  ADD PRIMARY KEY (`MaGV`,`MaPhong`);

--
-- Indexes for table `lop_hoc`
--
ALTER TABLE `lop_hoc`
  ADD PRIMARY KEY (`MaLop`),
  ADD KEY `Lớp-Lớp trưởng_idx` (`TenLop`);

--
-- Indexes for table `muon_phong`
--
ALTER TABLE `muon_phong`
  ADD PRIMARY KEY (`MaNV`,`MaLop`,`MaPhong`,`TGBatDau`);

--
-- Indexes for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `phong_hoc`
--
ALTER TABLE `phong_hoc`
  ADD PRIMARY KEY (`MaPhong`);

--
-- Indexes for table `quan_ly`
--
ALTER TABLE `quan_ly`
  ADD PRIMARY KEY (`MaPhong`,`MaNV`);

--
-- Indexes for table `sdt_chu_nhiem`
--
ALTER TABLE `sdt_chu_nhiem`
  ADD PRIMARY KEY (`MaLop`,`SDTCN`);

--
-- Indexes for table `sdt_giao_vien`
--
ALTER TABLE `sdt_giao_vien`
  ADD PRIMARY KEY (`MaGV`,`SDTGV`);

--
-- Indexes for table `sdt_khoa`
--
ALTER TABLE `sdt_khoa`
  ADD PRIMARY KEY (`Khoa`);

--
-- Indexes for table `sdt_lop_truong`
--
ALTER TABLE `sdt_lop_truong`
  ADD PRIMARY KEY (`MaLop`,`SDTLT`);

--
-- Indexes for table `sdt_nhan_vien`
--
ALTER TABLE `sdt_nhan_vien`
  ADD PRIMARY KEY (`MaNV`,`SDTNV`);

--
-- Indexes for table `so_dien_thoai`
--
ALTER TABLE `so_dien_thoai`
  ADD PRIMARY KEY (`MaSo`,`SoDienThoai`);

--
-- Indexes for table `su_dung_phong`
--
ALTER TABLE `su_dung_phong`
  ADD PRIMARY KEY (`MaGV`,`MaLop`,`MaPhong`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
