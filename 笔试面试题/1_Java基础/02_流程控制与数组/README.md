# 02_流程控制与数组

## 一、填空题

## 二、判断题

## 三、单选题

## 四、多选题
### 1.关于Java中的数组，下面的一些描述，哪些描述是准确的：（`ACF`）
+ A.数组是一个对象，不同类型的数组具有不同的类
+ B.数组长度是可以动态调整的
+ C.数组是一个连续的存储结构
+ D.一个固定长度的数组可类似这样定义: int array[100]
+ E.两个数组用equals方法比较时，会逐个便利其中的元素，对每个元素进行比较
+ F.可以二维数组，且可以有多维数组，都是在Java中合法的

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12778276

+ D:数组的长度是固定的，`int[] array =new int[100]`就对了
+ E:数组是一种引用数据类型  那么他肯定是继承Object类的  所以里面有equals() 方法 但是肯定没有重写过 因为他并不是比较数组内的内容  
使用Arrays.equals()  是比较两个数组中的内容

### 2.在Java中,下列说法错误的有（`BCD`）
+ A.数组是一种对象
+ B.数组属于一种原生类
+ C.`int number = []{31,23,33,43,35,63};`
+ D.数组的大小可以任意改变

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741599

+ A.Java中的那些基本类型属于原生类，而数组是引用类型，不属于原生类，可以看成是一种对象。
+ C.C中的数组声明和初始化的格式不对
+ D.数组的大小一旦指定，就不可以进行改变

### 3.在java7中，下列不能做switch()的参数类型是？（`D`）
+ A.int型
+ B.枚举类型
+ C.字符串
+ D.浮点型

> 解答：switch语句后的控制表达式只能是short、char、int、long整数类型和枚举类型，不能是float，double和boolean类型。String类型是java7开始支持。

switch语句后的控制表达式只能是short、char、int、long整数类型和枚举类型，不能是float，double和boolean类型。String类型是java7开始支持。即switch支持int及以下（char， short， byte）、String、Enum
## 五、问答题