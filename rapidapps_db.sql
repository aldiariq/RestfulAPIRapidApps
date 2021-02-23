-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 22, 2021 at 05:14 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rapidapps_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(89);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_konfirmasipendaftaran`
--

CREATE TABLE `tbl_konfirmasipendaftaran` (
  `id_konfirmasipendaftaran` int(11) NOT NULL,
  `email_konfirmasipendaftaran` varchar(200) NOT NULL,
  `kodeunik_konfirmasipendaftaran` varchar(200) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_lokasirapid`
--

CREATE TABLE `tbl_lokasirapid` (
  `id_lokasirapid` int(11) NOT NULL,
  `nama_lokasirapid` varchar(200) NOT NULL,
  `notel_lokasirapid` varchar(200) NOT NULL,
  `biaya_lokasirapid` int(11) NOT NULL,
  `alamat_lokasirapid` varchar(200) NOT NULL,
  `longitude_lokasirapid` varchar(200) NOT NULL,
  `latitude_lokasirapid` varchar(200) NOT NULL,
  `id_user` int(11) NOT NULL,
  `tgl_dibuat_lokasirapid` date NOT NULL,
  `tgl_diupdate_lokasirapid` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_resetpassword`
--

CREATE TABLE `tbl_resetpassword` (
  `id_resetpassword` int(11) NOT NULL,
  `email_resetpassword` varchar(200) NOT NULL,
  `kodeunik_resetpassword` varchar(200) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `email_user` varchar(200) NOT NULL,
  `nama_user` varchar(200) NOT NULL,
  `password_user` varchar(200) NOT NULL,
  `status_user` enum('AKTIF','TIDAKAKTIF') NOT NULL,
  `akses_user` enum('USER','ADMIN') NOT NULL,
  `tgl_dibuat_user` date NOT NULL,
  `tgl_diupdate_user` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_konfirmasipendaftaran`
--
ALTER TABLE `tbl_konfirmasipendaftaran`
  ADD PRIMARY KEY (`id_konfirmasipendaftaran`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `tbl_lokasirapid`
--
ALTER TABLE `tbl_lokasirapid`
  ADD PRIMARY KEY (`id_lokasirapid`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `tbl_resetpassword`
--
ALTER TABLE `tbl_resetpassword`
  ADD PRIMARY KEY (`id_resetpassword`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_konfirmasipendaftaran`
--
ALTER TABLE `tbl_konfirmasipendaftaran`
  MODIFY `id_konfirmasipendaftaran` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_lokasirapid`
--
ALTER TABLE `tbl_lokasirapid`
  MODIFY `id_lokasirapid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_resetpassword`
--
ALTER TABLE `tbl_resetpassword`
  MODIFY `id_resetpassword` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_konfirmasipendaftaran`
--
ALTER TABLE `tbl_konfirmasipendaftaran`
  ADD CONSTRAINT `tbl_konfirmasipendaftaran_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tbl_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_lokasirapid`
--
ALTER TABLE `tbl_lokasirapid`
  ADD CONSTRAINT `tbl_lokasirapid_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tbl_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_resetpassword`
--
ALTER TABLE `tbl_resetpassword`
  ADD CONSTRAINT `tbl_resetpassword_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tbl_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
