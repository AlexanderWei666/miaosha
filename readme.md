## 高并发秒杀项目

### 项目简介：
该项目使用SSM（Spring + SpringBoot + Mybatis）架构实现限购商品的抢购。
### 系统架构：
springboot2.0.2  +  mybatis + mysql + tomcat + docker + redis
### 实现功能：
#### 登录：
采用明文密码两次 Md5 入库，使用 Redis 实现分布式 Session，使用 JSR303 在后端对登录参数实现校验 
#### 数据库降压：
优化秒杀接口，基于 Redis 做库存预判并利用 RabbitMQ 实现异步处理 
#### 安全优化、限流：
实现秒杀地址隐藏、算术图形验证码和接口限流防刷
#### 待完成：
利用 Nginx 和 Tomcat 实现负载均衡和动静分离