
## member
drop table if exists `member`;
create table `member` (
    `member_id` bigint not null auto_increment,
    `email` varchar(255) not null,
    `password` varchar(255) not null,
    `name` varchar(255),
    `phone` varchar(255),
    `role` varchar(50),
    `created_at` timestamp,
    `created_by` varchar(255),
    `update_at` timestamp,
    `updated_by` varchar(255),
    primary key (`member_id`),
    index `IDX_1` (`member_id`),
    unique index `UK_1` (`email`)
);

## role
drop table if exists `role`;
CREATE TABLE `role`
(
    `role_id` bigint NOT NULL auto_increment,
    `name`    varchar(255) NOT NULL ,

    PRIMARY KEY (`role_id`)
);


## member_role
drop table if exists `member_role`;
CREATE TABLE `member_role`
(
    `member_id` bigint NOT NULL ,
    `role_id`   bigint NOT NULL ,

    PRIMARY KEY (`member_id`, `role_id`),
    KEY `FK_2` (`member_id`),
    CONSTRAINT `FK_1` FOREIGN KEY `FK_2` (`member_id`) REFERENCES `member` (`member_id`),
    KEY `FK_3` (`role_id`),
    CONSTRAINT `FK_2` FOREIGN KEY `FK_3` (`role_id`) REFERENCES `role` (`role_id`)
);

## aroma
drop table if exists `aroma`;
CREATE TABLE `aroma`
(
    `aroma_id` bigint NOT NULL auto_increment,
    `name`     varchar(255) NOT NULL ,
    `point`    double NOT NULL ,

    PRIMARY KEY (`aroma_id`)
);

## color
drop table if exists `color`;
CREATE TABLE `color`
(
    `color_id` bigint NOT NULL auto_increment,
    `name`     varchar(255) NOT NULL ,

    PRIMARY KEY (`color_id`)
);

## finish
drop table if exists `finish`;
CREATE TABLE `finish`
(
    `finish_id` bigint NOT NULL auto_increment,
    `notes`     text NOT NULL ,
    `rates`     double NOT NULL ,

    PRIMARY KEY (`finish_id`)
);

## palate
drop table if exists `palate`;
CREATE TABLE `palate`
(
    `palate_id` bigint NOT NULL auto_increment,
    `name`      varchar(255) NOT NULL ,
    `point`     double NOT NULL ,

    PRIMARY KEY (`palate_id`)
);


## note
drop table if exists `note`;
CREATE TABLE `note`
(
    `note_id`      bigint NOT NULL auto_increment,
    `aroma_id`     bigint NOT NULL ,
    `palate_id`    bigint NOT NULL ,
    `color_id`     bigint NOT NULL ,
    `finish_id`    bigint NOT NULL ,
    `whiskey_name` varchar(255) NOT NULL ,
    `age`          int NULL ,
    `country`      varchar(255) NOT NULL ,
    `volume`       double NOT NULL ,
    `region`       varchar(255) NOT NULL ,
    `type`         varchar(255) NOT NULL ,
    `distiller`    varchar(255) NULL ,
    `total_score`  double NOT NULL ,
    `notes`        longtext NULL ,
    PRIMARY KEY (`note_id`),
    KEY `FK_2` (`finish_id`),
    CONSTRAINT `FK_3` FOREIGN KEY `FK_2` (`finish_id`) REFERENCES `finish` (`finish_id`),
    KEY `FK_3` (`color_id`),
    CONSTRAINT `FK_4` FOREIGN KEY `FK_3` (`color_id`) REFERENCES `color` (`color_id`),
    KEY `FK_4` (`palate_id`),
    CONSTRAINT `FK_5` FOREIGN KEY `FK_4` (`palate_id`) REFERENCES `palate` (`palate_id`),
    KEY `FK_5` (`aroma_id`),
    CONSTRAINT `FK_6` FOREIGN KEY `FK_5` (`aroma_id`) REFERENCES `aroma` (`aroma_id`)
);

## member_note
drop table if exists `member_note`;
CREATE TABLE `member_note`
(
    `member_note_id` bigint NOT NULL auto_increment,
    `member_id`      bigint NOT NULL ,
    `note_id`        bigint NOT NULL ,
    `publish_type`   varchar(50) NOT NULL ,
    `created_by`     varchar(255) NOT NULL ,
    `created_at`     timestamp NOT NULL ,
    `update_by`      varchar(255) NOT NULL ,
    `updated_at`     timestamp NOT NULL ,

    PRIMARY KEY (`member_note_id`),
    KEY `FK_2` (`member_id`),
    CONSTRAINT `FK_7` FOREIGN KEY `FK_2` (`member_id`) REFERENCES `member` (`member_id`),
    KEY `FK_3` (`note_id`),
    CONSTRAINT `FK_8` FOREIGN KEY `FK_3` (`note_id`) REFERENCES `note` (`note_id`)
);

