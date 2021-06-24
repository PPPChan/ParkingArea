create database `parking_area`;
use parking_area;

create table `user_info` (
	`openid` varchar(64) NOT NULL,
	`user_name` varchar(64) not null comment '用户名',
	`user_phone` varchar(32) not null comment '用户电话',
	`user_type` tinyint(3) not null default '0' comment '用户类型，默认0普通用户，1为月卡用户',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key(`openid`),
	unique key `uqe_user_name` (`user_name`)
) comment '用户表';

create table `parking` (
	`parking_id` int not null auto_increment,
	`parking_name` varchar(64) not null comment '停车场名称',
	`parking_address` varchar(128) not null comment '停车场地址',
	`hour_price` decimal(8,2) not null comment '每小时价格',
	`parking_total` int not null comment '停车位总数',
	`parking_used` int not null comment '已停车位',
	`parking_available` int not null comment '可用车位',
	`parking_status` tinyint(3) not null default '1' comment '停车场状态，默认1开启，0为关闭',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key(`parking_id`),
	unique key `uqe_parking_name` (`parking_name`)
) comment '车位表';

CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `parking_id` int not null,
  `parking_name` varchar(64) not null comment '停车场名字',
  `hour_price` decimal(8,2) not null comment '每小时价格',
  `user_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `license_plate_number` varchar(64) not null comment '车牌号',
  `order_amount` decimal(8,2) NOT NULL default 0 COMMENT '订单总金额',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp not null default current_timestamp comment '创建时间',
  `end_time` timestamp not null default current_timestamp on update current_timestamp comment '结束时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_user_openid` (`user_openid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

CREATE TABLE `employee` (
	`employee_id` int not null auto_increment,
	`employee_password` varchar(64) not null comment '员工密码',
	`employee_name` varchar(32) not null comment '员工姓名',
	`employee_phone` varchar(32) not null comment '员工电话', 
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key(`employee_id`)
)comment '员工表';

create table `license_plate` (
	`license_plate_number` varchar(64) not null,
	`openid` varchar(64) not null comment 'openid',
	primary key(`license_plate_number`)
) comment '车牌表';


