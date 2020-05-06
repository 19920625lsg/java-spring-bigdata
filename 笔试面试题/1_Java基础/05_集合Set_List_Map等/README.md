# 05_集合Set_List_Map等

## 一、填空题

## 二、判断题

## 三、单选题
### 1.以下程序的运行结果是：(`C`)
+ 编译失败
+ 发生运行时异常
+ [606, 608, 610, 612, 629] [608, 610]
+ [606, 608, 610, 612, 629] [608, 610,629]

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12778238

```java
class StaticStuff {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        TreeSet<Integer> subSet = new TreeSet<Integer>();
        for (int i = 606; i < 613; i++) {
            if (i % 2 == 0) {
                if (i % 2 == 0) {
                    set.add(i);
                }
            }
            subSet = (TreeSet) set.subSet(608, true, 611, true);  //此时的subSet的值为[606,608,610,612] set.add(629);
            System.out.println(set + " " + subSet);
        }
    }
}
```

subset方法是求set的范围内的子集，两个true是表示是否包含端点（608和611），故subSet的值为[608,610]


### 2.下面那个选项有关java.util.ArrayList是正确的（`A`）
+ A.集合中的元素是有序的
+ B.集合不可改变
+ C.集合中的元素必须唯一
+ D.集合中元素的键是唯一的
+ E.集合中的元素是线程同步的

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5314135

### 3.在java7中,下列哪个说法是正确的:(`D`)
+ A.ConcurrentHashMap使用synchronized关键字保证线程安全
+ B.HashMap实现了Collection接口
+ C.Arrays.asList方法返回java.util.ArrayList对象
+ D.SimpleDateFormat对象是线程不安全的

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5249137

+ A选项：hashMap在单线程中使用大大提高效率，在多线程的情况下使用hashTable来确保安全。hashTable中使用synchronized关键字来实现安全机制，但是synchronized是对整张hash表进行锁定即让线程独享整张hash表，在安全同时造成了浪费。concurrentHashMap采用分段加锁的机制来确保安全，ConcurrentHashMap使用segment来分段和管理锁，segment继承自ReentrantLock，因此ConcurrentHashMap使用ReentrantLock来保证线程安全。
+ B选项：`public class HashMap<K,V> extends AbstractMap <K,V>  implements Map <K,V>, Cloneable , Serializable`
+ C选项：Arrays.asList()将一个数组转化为一个List对象，这个方法返回一个ArrayList类型的对象， 这个ArrayList类并非java.util.ArrayList类，而是Arrays类的静态内部类！用这个对象对列表进行添加删除更新操作，就会报UnsupportedOperationException异常。
+ D选项：
  ```java
  public abstract class DateFormat extends Format 
  public abstract class Format extends Object implements Serializable, Cloneable
  public class SimpleDateFormat extends DateFormat
  ```
  再查其方法api，也没见到说SimpleDateFormat有实现同步的方法。（一般要是一个类是线程同步的，第一句话应该就会说了）

### 4.下列关于容器集合类的说法正确的是？（`C`）
+ A.LinkedList继承自List
+ B.AbstractSet继承自Set
+ C.HashSet继承自AbstractSet
+ D.WeakMap继承自HashMap

> 解答：https://www.nowcoder.com/questionTerminal/f32600cdc2ad413082ea503f9496cadd

+ A.LinkedList是继承自AbstractSequentialList（抽象类，实现了List接口）的，并且实现了List接口。所以A错误。
+ B.AbstractSet是实现了Set接口的，本身是一个抽象类。继承自AbstractCollection（抽象类，实现了Collection接口）。所以B错误。
+ C.HashSet是继承自AbstractSet，实现了Set接口。所以C正确。
+ D.WeakMap不存在于java集合框架的。只有一个叫做WeakHashMap（继承自AbstractMap）。
最后附上java集合框架图。
![java集合框架图](https://uploadfiles.nowcoder.com/images/20160801/740942_1470042423855_86F5A9F9F791DD7EA7C96F158F0FEA87)

### 5.下面有关java hashmap的说法错误的是？(`C`)
+ A.HashMap 的实例有两个参数影响其性能：“初始容量” 和 “加载因子”。
+ B.HashMap 的实现不是同步的，意味着它不是线程安全的
+ C.HashMap通过开放地址法解决哈希冲突
+ D.HashMap中的key-value都是存储在Entry数组中的

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3507828

 hashmap采用拉链法解决冲突

 1. 开放定址法：线性探测再散列、二次探测再散列、再随机探测再散列；

2. 再哈希法：换一种哈希函数；

3. 链地址法 ：在数组中冲突元素后面拉一条链路，存储重复的元素；

4. 建立一个公共溢出区：其实就是建立一个表，存放那些冲突的元素。

什么时候会产生冲突

HashMap中调用 hashCode() 方法来计算hashCode。
![HashMap的原理](https://uploadfiles.nowcoder.com/images/20170326/9020120_1490497244985_63AB9B420F558D068D1E823264FC73C0)
由于在Java中两个不同的对象可能有一样的hashCode,所以不同的键可能有一样hashCode，从而导致冲突的产升。  

HashMap底层是 数组和链表 的结合体。底层是一个线性数组结构，数组中的每一项又是一个链表。当新建一个HashMap的时候，就会初始化一个数组。数组是 Entry[] 数组，静态内部类。 
E ntry就是数组中的元素，每个 Map.Entry 其实就是一个key-value对，它持有一个指向下一个元素的引用 next ，这就构成了链表。所以 很明显是链地址法。
具体过程：
当我们往HashMap中put元素的时候：当程序试图将一个key-value对放入HashMap中时，
+ 1.程序首先根据该 key 的 hashCode() 返回值决定该 Entry 的存储位置；
+ 2.若Entry 的存储位置上为 null ，直接存储该对象；若不为空，两个 Entry 的 key 的 hashCode() 返回值相同，那它们的存储位置相同，
+ 3.循环遍历链表，如果这两个 Entry 的 key 通过 equals 比较返回 true，新添加 Entry 的 value 将覆盖集合中原有 Entry 的 value，但key不会覆盖；如果这两个 Entry 的 key 通过 equals 比较返回 false，新添加的 Entry 将与集合中原有 Entry 形成 Entry 链，而且新添加的 Entry 位于 Entry 链的头部

## 四、多选题
### 1.关于java集合下列说法不正确的有哪些（`ABD`）
+ A.HashSet 它是线程安全的，不允许存储相同的对象
+ B.ConcurrentHashMap 它是线程安全的，其中存储的键对象可以重复，值对象不能重复
+ C.Collection接口是List接口和Set接口的父接口，通常情况下不被直接使用
+ D.ArrayList线程安全的，允许存放重复对象

> 解答: https://www.nowcoder.com/profile/934336/myFollowings/detail/12779913

+ 线程安全(Thread-safe)的集合对象：
  + Vector
  +  HashTable 
  + StringBuffer
+ 非线程安全的集合对象：
  + ArrayList
  + LinkedList
  + HashMap
  + HashSet
  + TreeMap
  + TreeSet
  + StringBulider

### 2.关于HashMap类的描述，以下正确的是(`ACD`)
+ A.HashMap使用键/值得形式保存数据
+ B.HashMap 能够保证其中元素的顺序
+ C.HashMap允许将null用作键
+ D.HashMap允许将null用作值

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777658

HashMap允许一个key为null，多个value为null，而Hashtable不允许有null值

### 3.java中Hashtable, Vector, TreeSet, LinkedList哪些线程是安全的？(`AB`)
+ A.Hashtable
+ B.Vector
+ C.TreeSet
+ D.LinkedList

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777562

参考第1题

### 4.关于HashMap和Hashtable正确的说法有（`AC`）
+ A.都实现了Map接口
+ B.Hashtable类不是同步的，而HashMap类是同步的
+ C.Hashtable不允许null键或值
+ D.HashMap不允许null或值

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/5361552

总结：
```txt
Collection
    -----List 
        -----LinkedList  非同步
        ----ArrayList    非同步，实现了可变大小的元素数组
        ----Vector       同步           
    -----Set
 Map
    -----HashTable   同步，实现一个key--value映射的哈希表，key和value都不允许出现null值
    -----HashMap     非同步，
    -----WeakHashMap 改进的HashMap，实现了“弱引用”，如果一个key不被引用，则被GC回收
注：
List接口中的对象按一定顺序排列，允许重复 
Set接口中的对象没有顺序，但是不允许重复 
Map接口中的对象是key、value的映射关系，key不允许重复
```

具体到Hashtable和HashMap的区别
+ 1、继承不同
  + Hashtable: `public class Hashtable extends Dictionary implements Map`
  + HashMap: `public class HashMap extends AbstractMap implements Map`

+ 2、Hashtable 中的方法是同步的，而HashMap中的方法在缺省情况下是非同步的。在多线程并发的环境下，可以直接使用Hashtable，但是要使用HashMap的话就要自己增加同步处理了。
+ 3、Hashtable中，key和value都不允许出现null值。在HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当get()方法返回null值时，即可以表示 HashMap中没有该键，也可以表示该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键， 而应该用containsKey()方法来判断。

+ 4、两个遍历方式的内部实现上不同：Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。
+ 5、哈希值的使用不同，HashTable直接使用对象的hashCode。而HashMap重新计算hash值。
+ 6、Hashtable和HashMap它们两个内部实现方式的数组的初始大小和扩容的方式。HashTable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数 

### 5.ArrayLists和LinkedList的区别，下述说法正确的有？(`ABCD`)
+ A.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
+ B.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
+ C.对于新增和删除操作add和remove，LinkedList比较占优势，因为ArrayList要移动数据。
+ D.ArrayList的空间浪费主要体现在在list列表的结尾预留一定的容量空间，而LinkedList的空间花费则体现在它的每一个元素都需要消耗相当的空间。

> 解答： https://www.nowcoder.com/profile/934336/myFollowings/detail/3524990

+ A.正确，这里的所谓动态数组并不是那个“ 有多少元素就申请多少空间 ”的意思，通过查看源码，可以发现，这个动态数组是这样实现的，如果没指定数组大小，则申请默认大小为10的数组，当元素个数增加，数组无法存储时，系统会另个申请一个长度为当前长度1.5倍的数组，然后，把之前的数据拷贝到新建的数组。
+ B.正确，ArrayList是数组，所以，直接定位到相应位置取元素，LinkedLIst是链表，所以需要从前往后遍历。
+ C.正确，ArrayList的新增和删除就是数组的新增和删除，LinkedList与链表一致。
+ D.正确，因为ArrayList空间的增长率为1.5倍，所以，最后很可能留下一部分空间是没有用到的，因此，会造成浪费的情况。对于LInkedList的话，由于每个节点都需要额外的指针，所以，你懂的。

### 6.下面几个关于Java里queue的说法哪些是正确的（`AC`）？
+ A.LinkedBlockingQueue是一个可选有界队列，不允许null值
+ B.PriorityQueue，LinkedBlockingQueue都是线程不安全的
+ C.PriorityQueue是一个无界队列，不允许null值，入队和出队的时间复杂度是O（log(n)）
+ D.PriorityQueue，ConcurrentLinkedQueue都遵循FIFO原则 // 优先队列和普通队列不同地

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3513990

+ A、LinkedBlockingQueue是一个基于节点链接的可选是否有界的阻塞队列，不允许null值
+ B、LinkedBlockingQueue是一个线程安全的阻塞队列，实现了先进先出等特性
+ C、PriorityQueue是一个***队列，不允许null值，入队和出队的时间复杂度是O（log(n)）
+ D、PriorityQueue是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。ConcurrentLinkedQueue是一个基于链接节点的***线程安全队列，该队列的元素遵循FIFO原则。

### 7.实现或继承了Collection接口的是（`BCE`）
+ A.Map
+ B.List
+ C.Vector
+ D.Iterator
+ E.Set

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3513828

![集合的继承关系1](http://uploadfiles.nowcoder.com/images/20150904/458054_1441352361202_2B9A4774B9C5C489A9E9564854FFCD6C)
![集合的继承关系2](http://uploadfiles.nowcoder.com/images/20150928/458054_1443399626006_AA40C8919CE65157D5F251E7622A75FB)

## 五、问答题