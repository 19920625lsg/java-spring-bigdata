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

## 2.关于HashMap类的描述，以下正确的是(`ACD`)
+ A.HashMap使用键/值得形式保存数据
+ B.HashMap 能够保证其中元素的顺序
+ C.HashMap允许将null用作键
+ D.HashMap允许将null用作值

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777658

HashMap允许一个key为null，多个value为null，而Hashtable不允许有null值

## 五、问答题