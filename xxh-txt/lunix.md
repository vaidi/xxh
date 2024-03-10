jstack https://blog.51cto.com/u_12195/6502464

1，top
2，top -Hp pid
3，jstack pid
4，jstack -l [PID] >/tmp/log.txt

#转成16进制
printf '%x\n' 21350
分析堆栈信息


ps aux|head -1;ps aux|grep -v PID|sort -rn -k +4|head
或者top （然后按下M，注意这里是大写

常用的命令
1、netstat   是监控tcp/ip 网络非常有用的工具
netstat -anp |grep 端口号  查看端口号的占用情况


2、jmap 命令打印dump文件的

3、jstack 打印堆栈信息的

4、losf
lsof  -i:22  显示22端口被哪些ip使用


5、top 整机 

6、vmstat 用来查看cpu的

7、df 磁盘

8、free 内存



ps H -eo pid,tid,%cpu|grep 进程id
