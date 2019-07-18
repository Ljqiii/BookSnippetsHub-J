# BookSnippetsHub-J
一个分享读书笔记的社交 App
![](https://raw.githubusercontent.com/Ljqiii/BookSnippetsHub-J/master/img/icon.png)

## 技术栈
- Spring Boot
- MySQL
- MyBatis
- Spring Security
- Redis
- java-JWT

## 前端地址
- Android: [booksnippetshub/BookSnippetsHub-android](https://github.com/booksnippetshub/BookSnippetsHub-android)
- 微信小程序: [Ljqiii/BookSnippetsHub-wechat](https://github.com/Ljqiii/BookSnippetsHub-wechat)
    > 在 [18e885d](https://github.com/Ljqiii/BookSnippetsHub-J/commit/18e885d90354dd8993a37990fcd530f7faf449f6) 之后不再更新

## 整体效果
用户注册页面

![](https://raw.githubusercontent.com/Ljqiii/BookSnippetsHub-J/master/img/regeister.png)


首页：显示推荐的分享和我关注的人的分享

![](https://raw.githubusercontent.com/Ljqiii/BookSnippetsHub-J/master/img/home.png)

通知页：其他人对我的分享点赞/评论/转发后会收到通知

![](https://raw.githubusercontent.com/Ljqiii/BookSnippetsHub-J/master/img/notification.png)

我的页面：显示我的相关信息，选择头像更换头像，设置是否启用启动画面，退出登录
![](https://raw.githubusercontent.com/Ljqiii/BookSnippetsHub-J/master/img/me.png)

## 快速部署
1. clone 项目到本地 ```git clone https://github.com/Ljqiii/BookSnippetsHub-J.git```
2. 在 MySQL 中执行在 resources 目录中的数据库脚本
3. 在 ```application.properties``` 中修改 MySQL 数据库用户名 密码
4. 在 IntelliJ IDEA 中运行此项目

**后端项目运行完成，克隆 Android 项目后在 Android Studio 中运行就可以了**








