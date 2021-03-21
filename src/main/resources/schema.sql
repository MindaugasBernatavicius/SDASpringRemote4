DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `count` int(11) DEFAULT NULL,
   `price` double DEFAULT NULL,
   `rating` double DEFAULT NULL,
   `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
