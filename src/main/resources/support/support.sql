select  d.id, d.create_date,
        concat_ws(' ', u.firstname, u.lastname) as user,
        d.object, d.adress, d.powcur, d.powdec,
        s.code, v.code, d.load1c, d.rewrite
		from demand as d
		left join volt as v on d.volt_id = v.id
		left join safe s on d.safe_id = s.id
        left join user_entity as u on d.user_id = u.id
		where d.load1c=1 or d.rewrite=1;

CREATE TABLE `history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client` bit(1) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `event` varchar(2048) DEFAULT NULL,
  `load1c` bit(1) DEFAULT NULL,
  `demand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `demand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object` varchar(255) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `adress_fact` varchar(255) DEFAULT NULL,
  `adress_ur` varchar(255) DEFAULT NULL,
  `consent` bit(1) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `contract` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `declarant` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `load1c` bit(1) DEFAULT NULL,
  `mod_date` date DEFAULT NULL,
  `powcur` double DEFAULT NULL,
  `powdec` double DEFAULT NULL,
  `powmax` double DEFAULT NULL,
  `requisite` varchar(255) DEFAULT NULL,
  `rewrite` bit(1) DEFAULT NULL,
  `sum` double DEFAULT NULL,
  `update1c` bit(1) DEFAULT NULL,
  `garant_id` bigint(20) DEFAULT NULL,
  `plan_id` bigint(20) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) NOT NULL,
  `safe_id` bigint(20) DEFAULT NULL,
  `send_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `volt_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwog0c1p9kcf0iil6bsdtprd0` (`garant_id`),
  KEY `FKj6a7myqy9oq0g4psue6or0ukp` (`plan_id`),
  CONSTRAINT `FKbwog0c1p9kcf0iil6bsdtprd0` FOREIGN KEY (`garant_id`) REFERENCES `garant` (`id`),
  CONSTRAINT `FKj6a7myqy9oq0g4psue6or0ukp` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

