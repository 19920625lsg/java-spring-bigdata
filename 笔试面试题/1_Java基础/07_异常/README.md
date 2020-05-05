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

## 四、多选题

## 五、问答题