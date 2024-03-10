epoll 和poll扥区别？
1、select的模型，使用数组存储socket文件，容量是固定的，需要通过轮询来判断是否发生io事件
2、poll模型，使用的是链接来存储socket，容量是不固定的，同样需要轮询来判断是否生io事件
3、epoll模型，epoll和poll是完全不同的，epoll是一种事件通知模型，当发生了io事件，程序才进行io操作，不用轮询


spi机制的优缺点：
1，需要遍历加载所有的实现类，不能做到按需加载
2，当多个ServiceLoader同时load时会有并发问题
3，spi缺少实例的维护，作用域没有定义singleton和prototype的定义，不利于用户自由定制
4，ServiceLoader不像spring，只能一次获取所有接口实例，不支持排序，随着新的实列加入，会出现
不稳定的情况

tcp:面向链接，可靠，字节流

rpc和http协议：
rpc:远程过程调用
rpc是一种进程内通信机制，http是一种网络应用协议
rpc采用tcp或udp，http使用的tcp
rpc可以使用自定义数据格式
rpc是持续链接，http是短链接
rpc用于内部集成，http用于web


dubbo支持的协议
1，dubbo协议：dubbo默认使用的协议，采用长链接和nio通讯协议，适合小数据量大并发的服务调用
2，rmi协议：基于javarmi实现，支持跨语言调用
3，hessian协议：基于hessian序列支持跨语言diao
4，webService协议



以后写随笔的日记
https://www.cnblogs.com/tuyang1129/p/12866484.html