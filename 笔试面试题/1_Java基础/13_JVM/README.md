# 13_JVM

## 填空题

## 判断题

## 单选题
### 1.JVM内存不包含如下哪个部分(`D`)
+ A.Stacks
+ B.PC寄存器
+ C.Heap
+ D.Heap Frame

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779837

JVM内存五大区域：
![JVM内存五大区域](images/JVM内存五大区域.png)

## 多选题
### 1.关于运行时常量池，下列哪个说法是正确的？(`BCD`)
+ 运行时常量池大小受栈区大小的影响
+ 运行时常量池大小受方法区大小的影响
+ 存放了编译时期生成的各种字面量
+ 存放编译时期生成的符号引用

> 解读：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779917

运行时常量池（Runtime Constant Pool）是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池（Constant Pool Table）,也就是说，每个class都有一个运行时常量池，用于存放编译器生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放


## 问答题

