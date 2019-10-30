##下面是使用linux训练模型，在windows的webui上展示训练结果的步骤
如果用的是Linux远程GPU服务器，本地打不开网页，怎么办？别慌，可以重定向。在本地命令行输入
ssh -p <remote_port> -L 8888:127.0.0.1:8888 <username>@<remote_ip>，
remote_port是服务器端口号，
127.0.0.1代表localhost，
前面的8888是第四步-p后面写的端口号，
后面的8888是你要重定向到本机的端口号，可以随意填写
username是服务器的用户名，remote_ip是服务器的ip地址
然后在自己电脑上打开浏览器，输入127.0.0.1:8888，就ok了
————————————————
版权声明：本文为CSDN博主「这个月亮不太亮」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43653494/article/details/101039198
