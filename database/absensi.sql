-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Jun 2024 pada 09.23
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `absensi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_presensi`
--

CREATE TABLE `list_presensi` (
  `id` int(11) NOT NULL,
  `id_lokasi` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `waktu` varchar(255) NOT NULL,
  `feel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `list_presensi`
--

INSERT INTO `list_presensi` (`id`, `id_lokasi`, `id_user`, `waktu`, `feel`) VALUES
(4, 10, 9, '2024-06-04 07:16:05', 'sedih');

-- --------------------------------------------------------

--
-- Struktur dari tabel `lokasi`
--

CREATE TABLE `lokasi` (
  `id` int(11) NOT NULL,
  `namaLokasi` varchar(255) NOT NULL,
  `kodeLokasi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `lokasi`
--

INSERT INTO `lokasi` (`id`, `namaLokasi`, `kodeLokasi`) VALUES
(9, 'Gerbang Sekolah', 'Lok_61a099dac38824'),
(10, 'Auditorium', 'Lok_61a0b1e6573bbc');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `role` int(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  `nohp` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `role`, `username`, `nohp`, `email`, `password`, `alamat`) VALUES
(7, 1, 'admin', '1234567890', 'admin@gmail.com', '123', 'Bambu Apus'),
(8, 0, 'Kallisya', '08283777299', 'kal@gmail.com', '123', 'Ciracas'),
(9, 0, 'user', '0866637777', 'user@gmail.com', '123', 'Bambu Apus');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `list_presensi`
--
ALTER TABLE `list_presensi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `lokasi`
--
ALTER TABLE `lokasi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `list_presensi`
--
ALTER TABLE `list_presensi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `lokasi`
--
ALTER TABLE `lokasi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
