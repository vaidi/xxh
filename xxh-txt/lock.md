
syn的关键字
https://blog.csdn.net/xyy1028/article/details/106042888
syn锁：
* 如果对象处于无锁状态（即`mark word`中没有其他线程的锁记录），则CAS操作成功，该线程获得轻量级锁。  
* 如果当前线程已经持有该锁，这是一个锁重入的情况。此时，（2此锁记录）`Lock Record`的`Displaced Mark Word`部分会被设置为`null`，
作为重入计数器的功能（对象信息《-》markward锁信息）

Monitor结构（jvm级别的对象锁）
1,waitset  关联调用wait方法的线程，处于waiting状态的线程
2,entryList 关联抢到锁的线程，处于blocked状态的线程
3,owner 存储当前线程获取锁的线程，只要有一个线程获取




偏向锁->轻量级锁->重量级锁
对方法加锁
1、对普通方法加锁，即为当前实例对象加锁，同一个类创建的不同对象调用该方法所获取的是不同的锁，不会有影响
2、对静态方法加锁，静态方法属于类，同一个类创建的不同对象调用该方法是互斥的，此时锁对向是.class对象
a、线程解锁前，必须把变脸的最新值刷新到主内存中
b、线程加锁时，先清空工作内存中的变量值，从主内存中重新获取到最新的工作内存

markWork:存储对象自身的运行时数据，如对象的hashcode，gc分代年龄，偏向时间，偏向时间戳，锁状态，线程持有的锁
偏向时间戳，而且mark word中的lockwork存储了指向monitor的起始地址。
monitor：在java中每个对象天生就带了一把内部锁或者monitor锁，monitor是线程私有的数据结构，每一个线程
都有一个可用的monitor record列表，同事还有一个全局的可用列表，每一个被锁住的对象都会和一个monitor关联


为什么有偏向锁：
大多数情况下锁是不存在多线程竞争的，而且总是同一线程获取，因此为了减少同意线程加锁解锁的状态而进入偏向锁。
线程a访问并获取锁对象时通过cas在mark word中记录偏向的锁的threadid，

为什么有轻量级锁？
轻量级锁：指向栈中锁记录的指针
因为阻塞线程需要cpu从用户态转到内核态。轻量级锁就是通过自旋。自旋次数是10，自适应自旋锁，次数不是固定的

线程8锁
1、synchronized作用于成员变量和非静态方法时，锁住的是对象的实例，即this对象
2、synchronized作用于静态方法时，锁住的是Class实例
3、synchronized作用于一个代码块时，锁住的是所有代码块中配置的对象

qps 平常都是 50
订单的qps  都是500左右
高峰  2000

CPU密集型核心线程数：CPU核数（逻辑核） + 1
IO密集型核心线程数：2 * CPU核数（逻辑核） 


syn可重入锁原理：每一个锁关联一个线程持有者和计数器，当计数器为0时表示该锁没有被任何线程持有，那么任何线程都可能
获取该锁而调用相应的方法，当某一个线程请求成功后，jvm会记下锁持有的线程并将计数加1，



