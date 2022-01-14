# Springboot+Spring Security+Mybatis
## 所有可供前端调用的接口已经放入postman文件中。
### 系统有3种角色，超级管理员和普通管理员已经内置。后面每次注册的用户都默认为普通用户。
### 账号密码： 
### 超级管理员 admin admin 负责维护广告。其他不管。权限名：ROLE_SUPER
### 航空管理员 air air 负责维护飞机页面信息    ROLE_AIR_ADMIN
### 海轮管理员 sea sea 负责维护轮渡页面信息    ROLE_SEA_ADMIN
### 地勤管理员 ground ground 负责维护汽车，火车页面信息   ROLE_GROUND_ADMIN
### 普通用户 内置了一个 user user 可收藏路线，评论。  ROLE_USER
用户评论，收藏时需要判断是否登录。
* 1.登录接口:/login
* 2.注册接口:/register
* 3.修改密码接口:/modifyPass
* 4.获取所有城市接口，该接口海陆空通用，用于让用户选择目的地（目的地只能是省会，接口会返回所有省会城市），出发地固定为大连 /train/getAllCity
=====================================================================
* 5.查询火车列表：/train/getTicketList
* 6.收藏列车：/train/like
* 7.取消收藏列车：/train/dislike
* 8.评论列车：/train/insertComment
* 9.删除评论列车：/train/deleteComment  该功能需要登陆人和评论人一致才可以删除。
* 10.新增列车：/train/insertTrain 
* 11.删除列车：/train/deleteTrain
* 12.修改列车：/train/updateTrain
=======================================================================
* 13.汽车同火车差不多。都在postman里了

## 后台在增删改火车、汽车接口处加了注解，不是对应管理员无法进入 会报错。
## 登录成功后会返回登陆人信息，权限等