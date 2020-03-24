-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 24, 2020 at 03:04 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `insurance`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `admin_id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(256) NOT NULL,
  `invalid_count` smallint(6) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`admin_id`, `email`, `password`, `invalid_count`, `status`) VALUES
(1, 'myselfsujan67@gmail.com', '$2a$10$DdoaAw5jLMW0GNk/dEB/NewBNirlWEuCoEUj29DmHB7cWwGSv1bAa', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_code_verification`
--

CREATE TABLE `tbl_code_verification` (
  `id` int(11) NOT NULL,
  `code` varchar(30) NOT NULL,
  `session_id` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_family`
--

CREATE TABLE `tbl_family` (
  `family_code` bigint(20) NOT NULL,
  `family_head` varchar(50) NOT NULL,
  `reg_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `no_of_members` smallint(6) NOT NULL,
  `amount` float NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `applied_month` varchar(15) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_family`
--

INSERT INTO `tbl_family` (`family_code`, `family_head`, `reg_date`, `expiry_date`, `no_of_members`, `amount`, `contact_no`, `applied_month`, `status`) VALUES
(1234, 'Tulsi Das Koju', '2072-01-01', '2072-01-01', 7, 4000, '9841637124', 'Jesth', 1),
(1235, 'Ram suwal', '2075-04-22', '2076-04-21', 3, 2500, '9841675658', 'Bhadra', 1),
(3274, 'Hari Ram Tuitui', '2075-01-05', '2076-01-04', 4, 2500, '9842568975', 'Jesth', 1),
(5658, 'Tulsi Das Koju', '2072-01-01', '2072-01-01', 1, 6000, '9841637124', 'Jesth', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_family_members`
--

CREATE TABLE `tbl_family_members` (
  `member_code` bigint(20) NOT NULL,
  `family_code` bigint(20) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_family_members`
--

INSERT INTO `tbl_family_members` (`member_code`, `family_code`, `member_name`, `status`) VALUES
(1234, 5658, 'hello', 1),
(1235, 5658, 'asdfsdf', 1),
(1236, 5658, 'hello 1', 1),
(3285, 3274, 'Ram Sundar Tuitui', 1),
(3286, 3274, 'Shree Krishna tuitui', 1),
(3287, 3274, 'Ritu Tuitui', 1),
(4567, 5658, 'test koju', 1),
(4568, 5658, 'Tyson Koju', 1),
(4569, 5658, 'Don Koju', 0),
(5651, 5658, 'Krishna Kumari Koju', 0),
(5652, 5658, 'Sunita Koju', 0),
(5653, 5658, 'Suman Koju', 1),
(5654, 5658, 'aasfdsf', 1),
(5856, 5658, 'Jack Suwal', 1),
(6788, 1235, 'Rogina suwal', 1),
(6789, 1235, 'Manju Suwal', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `tbl_code_verification`
--
ALTER TABLE `tbl_code_verification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_family`
--
ALTER TABLE `tbl_family`
  ADD PRIMARY KEY (`family_code`),
  ADD UNIQUE KEY `family_code` (`family_code`);

--
-- Indexes for table `tbl_family_members`
--
ALTER TABLE `tbl_family_members`
  ADD PRIMARY KEY (`member_code`),
  ADD UNIQUE KEY `member_code` (`member_code`,`family_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
