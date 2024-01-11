-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2024 at 09:03 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `car_rental_resevation_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `caruser`
--

CREATE TABLE `caruser` (
  `UserID` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` blob DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `caruser`
--

INSERT INTO `caruser` (`UserID`, `username`, `password`, `salt`, `Status`) VALUES
(1, 'WenDEVLIFE', '1f73d6235ebe0d2168020889b530250637a490911a1b0f6970ba68deb8bcb3c5', 0x4990c6db7406ce566c8b778b20ee635d, 'Admin'),
(2, 'WenDEVLIFE4', 'feca609d9f76f69c4be89c5212e7856e2ad57407a21c15d10827117790b21240', 0xeabca9f5dc961b9919b51e045551ed69, 'Admin'),
(3, 'Wetest', 'f414414bc7cd215c4629db0443843c5fdff183a21e6ec8a48bb24ebd10ae99c7', 0xc137a84ff75689c0e77bce77411398af, 'Admin'),
(5, 'Wenwernwe', '6a3ef7b357a1aedcca22834b2f97077e40ab0412a211c5cf2ee8d7b72c057eed', 0xe20cec18d1d65ebdc66700ec2c050dad, 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `tasktable`
--

CREATE TABLE `tasktable` (
  `TaskID` int(255) NOT NULL,
  `TaskInfo` varchar(255) NOT NULL,
  `Date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasktable`
--

INSERT INTO `tasktable` (`TaskID`, `TaskInfo`, `Date`) VALUES
(1, 'NULLGERS', '2024-1-11'),
(2, 'NULLGERS', '2024-1-11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caruser`
--
ALTER TABLE `caruser`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `tasktable`
--
ALTER TABLE `tasktable`
  ADD PRIMARY KEY (`TaskID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `caruser`
--
ALTER TABLE `caruser`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tasktable`
--
ALTER TABLE `tasktable`
  MODIFY `TaskID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
