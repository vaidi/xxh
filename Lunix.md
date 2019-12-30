记录对应的lunix命令：
top -Hp 9  查看进程id为9 的各个线程的运行情况
printf "%x\n" 10  把线程为10转化为16进制的
jstat -gcutil 9 查看gc的情况  9是pid也就是进程  命令打印下打个各参数如下
S0    S1    E  O  M  CCS  YGC  YGCT   FGC：这里指次数 FGCT：这里是时间  GCT






