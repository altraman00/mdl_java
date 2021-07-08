-- CREATE DATABASE seckill;

CREATE TABLE seckill (
	seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存表',
	NAME VARCHAR ( 120 ) NOT NULL COMMENT '商品名称',
	number INT NOT NULL COMMENT '库存数量',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
	end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
	PRIMARY KEY ( seckill_id ),
	KEY idx_start_time ( start_time ),
	KEY idx_end_time ( end_time ),
	KEY idx_create_time ( create_time ) 
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀库存表';
CREATE TABLE success_killed (
	seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
	user_phone BIGINT NOT NULL COMMENT '用户手机号',
	state TINYINT NOT NULL NOT NULL DEFAULT - 1 COMMENT '标识状态 -1 无效 0成功 1已付款 2 已发货 ',
	create_time TIMESTAMP NOT NULL COMMENT '创建时间',
	PRIMARY KEY ( seckill_id, user_phone ),
	KEY idx_create_time ( create_time ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功明细';


INSERT INTO seckill ( NAME, number, start_time, end_time )
VALUES
	( '1000元秒杀iphone6', 100, '2016-11-23 00:00:00', '2016-11-24 00:00:00' ),
	( '500元秒杀ipad2', 200, '2016-11-23 00:00:00', '2016-11-24 00:00:00' ),
	( '300元秒杀小米4', 300, '2016-11-23 00:00:00', '2016-11-24 00:00:00' ),
	( '2000元秒杀iphone6s', 400, '2016-11-23 00:00:00', '2016-11-24 00:00:00' );
