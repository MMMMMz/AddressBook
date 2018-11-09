# AddressBook
一个用Java Swing实现的通讯录,其中使用到了JDBC链接数据库,从数据库中读取数据以及进行操作.

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(255) NOT NULL COMMENT '用户学号',
  `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `sex` varchar(10) NOT NULL DEFAULT '' COMMENT '用户性别',
  `work_address` varchar(255) NOT NULL DEFAULT '' COMMENT '工作单位',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '电话号码',
  `E-mail` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

这个是创建数据库的语句,其中因为学号不能重复,将其设置为了unique key,在使用时需要注意DbUtil中的配置需要更改.
