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

## 五、问答题