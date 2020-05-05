# 07_异常

## 一、填空题

## 二、判断题

## 三、单选题
### 1.以下对异常的描述不正确的有（`C`）
> 这个题目的异常包含了Error和Exception
+ A.异常分为Error和Exception
+ B.Throwable是所有异常类的父类
+ C.Exception是所有异常类父类
+ D.Exception包括RuntimeException和RuntimeException之外的异常

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779867

![异常的体系结构](images/异常的体系结构.png)

### 2.以下代码执行的结果显示是多少？（`B`）
> 虽然finally会在return之前执行，但是return的值是在进finally之前决定地
```java
public class Demo {
    public static void main(String[] args) {
        System.out.print(getNumber(0));
        System.out.print(getNumber(1));
        System.out.print(getNumber(2));
        System.out.print(getNumber(4));
    }

    public static int getNumber(int num) {
        try {
            int result = 2 / num;
            return result;
        } catch (Exception exception) {
            return 0;
        } finally {
            if (num == 0) {
                return -1;
            }
            if (num == 1) {
                return 1;
            }
        }
    }
}
```

+ A.0110
+ B.-1110
+ C.0211
+ D.-1211

> 解读：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779851

+ finally在return之前执行，但是trycatch中的return值是在进finally之前决定地
+ finally在return之前执行，但是如果finally使用了return或者throw语句，将会使trycatch中的return或者throw失效

### 3.以下代码段执行后的输出结果为(`D`)
```java
public class Test {
    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test() {
        int temp = 1;
        try {
            System.out.println(temp);
            return ++temp;
        } catch (Exception e) {
            System.out.println(temp);
            return ++temp;
        } finally {
            ++temp;
            System.out.println(temp);
        }
    }
}
```
+ A.`1,2,2`
+ B.`1,2,3`
+ C.`1,3,3`
+ D.`1,3,2`

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12779705

finally在return之前执行，但是trycatch中的return值是在进finally之前决定地。和上面的题很类似

### 4.对于Java中异常的描述正确的是（`D`）
+ A.用throws定义了方法可能抛出的异常，那么调用此方法时一定会抛出此异常。
+ B.如果try块中没有抛出异常，finally块中的语句将不会被执行。
+ C.抛出异常意味着程序发生运行时错误，需要调试修改
+ D.Java中的unchecked异常可能来自RuntimeException类或其子类。

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12777621

+ A错 在调用此方法的时候 也可以再次申明以将异常交由更高一级处理。
+ B错 finally块中的语句一定会被执行。除非catch块中有System.exit(0)。
+ C错 抛出异常不一定是运行时异常，也有可能是编译时异常。
+ D对 运行时异常的特点是Java编译器不会检查它。

## 四、多选题

## 五、问答题