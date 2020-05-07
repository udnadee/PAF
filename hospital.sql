-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2020 at 04:24 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hos_id` int(11) NOT NULL,
  `hospital_reg_no` varchar(50) NOT NULL,
  `hos_name` varchar(30) NOT NULL,
  `hos_type` varchar(30) NOT NULL,
  `AddressLine1` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `district` varchar(50) NOT NULL,
  `province` varchar(20) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `hospital_fee` double(8,2) NOT NULL,
  `hos_password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hos_id`, `hospital_reg_no`, `hos_name`, `hos_type`, `AddressLine1`, `city`, `district`, `province`, `telephone`, `hospital_fee`, `hos_password`) VALUES
(9, 'H11233', 'Central Hospital colombo', 'Government', 'central', 'colombo', 'colombo', 'western', '0989876543', 0.00, 'central'),
(18, 'H2222222222', 'Badulla central Hospital', 'Government', 'central', 'colombo', 'colombo', 'western', '0989876543', 500.00, '1234ertyhgfds'),
(21, 'H2222222222000', 'Nawaloka', 'Private', 'central', 'colombo', 'colombo', 'western', '0989876543', 0.00, 'central'),
(22, 'H226577760', 'Nawaloka+2', 'Private', 'central', 'colombo', 'Mannar', 'Eastern', '0989876543', 0.00, 'central'),
(23, 'NewHospital12345', 'TestHospital', 'private', 'Test address', 'test city', 'test', 'test province', '0000000000', 1000.00, 'test123'),
(29, 'NewHospital123452jh', 'TestHospital37', 'private', 'Test address', 'test city', 'test', 'test province', '0000000000', 1000.00, 'test123'),
(34, 'New1234567', 'New+Hospital', 'Government', 'Test+address', 'test+city', 'Mannar', 'North+Western', '0111111111', 2000.00, 'test123'),
(35, 'New123456767', 'New test hospital', 'private', 'Test address', 'test city', 'test', 'test province', '0000000000', 1000.00, 'test123'),
(53, 'Hos0987654', 'Today', 'Private', 'new todY', 'NU', 'Kandy', 'Central', '12222222', 1000.00, '111'),
(62, 'NewRe1', 'Namw', 'Government', 'SSS', 'SSS', 'Matale', 'Eastern', '111111111111', 1111.00, '1111');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`hos_id`),
  ADD UNIQUE KEY `hospital_reg_no` (`hospital_reg_no`),
  ADD UNIQUE KEY `hos_name` (`hos_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `hos_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
