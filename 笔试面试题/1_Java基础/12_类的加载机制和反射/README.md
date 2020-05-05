# 12_类的加载机制和反射

## 一、填空题

## 二、判断题

## 三、单选题
### 1.有以下一个对象：
```java
public class DataObject implements Serializable {
    private static int i = 0;
    private String word = " ";

    public void setWord(String word) {
        this.word = word;
    }

    public void setI(int i) {
        Data0bject.i = I;
    }
}
```
创建一个如下方式的DataObject:
```java
DataObject object=new Data0bject ( );
object. setWord("123");
object. setI(2);
```
将此对象序列化为文件，并在另外一个JVM中读取文件，进行反序列化，请问此时读出的Data0bject对象中的word和i的值分别为：(`D`)
+ A."", 0
+ B."", 2
+ C."123", 2
+ D."123", 0

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741662

`readObject()`方法在反射破坏单例模式那一节有讲

序列化保存的是对象的状态，静态变量属于类的状态，因此，序列化并不保存静态变量。所以i是没有改变的

## 四、多选题
### 1.Java是一门支持反射的语言,基于反射为Java提供了丰富的动态性支持，下面关于Java反射的描述，哪些是错误的：(`ADF`)
+ A.Java反射主要涉及的类如Class, Method, Filed,等，他们都在java.lang.reflet包下
+ B.通过反射可以动态的实现一个接口，形成一个新的类，并可以用这个类创建对象，调用对象方法
+ C.通过反射，可以突破Java语言提供的对象成员、类成员的保护机制，访问一般方式不能访问的成员
+ D.Java反射机制提供了字节码修改的技术，可以动态的修剪一个类
+ E.Java的反射机制会给内存带来额外的开销。例如对永生堆的要求比不通过反射要求的更多
+ F.Java反射机制一般会带来效率问题，效率问题主要发生在查找类的方法和字段对象，因此通过缓存需要反射类的字段和方法就能达到与之间调用类的方法和访问类的字段一样的效率


> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/12741600

+ A.Class类在java.lang包
+ B.动态代理技术可以动态创建一个代理对象，反射不行
+ C.反射访问私有成员时，Field调用setAccessible可解除访问符限制
+ D.CGLIB实现了字节码修改，反射不行
+ E.反射会动态创建额外的对象，比如每个成员方法只有一个Method对象作为root，他不胡直接暴露给用户。调用时会返回一个Method的包装类
+ F.反射带来的效率问题主要是动态解析类，JVM没法对反射代码优化


## 五、问答题