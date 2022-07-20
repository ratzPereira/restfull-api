DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`
(
    `id`         bigint NOT NULL,
    `address`    varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `gender`     varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
