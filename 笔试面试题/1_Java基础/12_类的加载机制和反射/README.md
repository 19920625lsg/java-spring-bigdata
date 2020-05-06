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

### 2.从内存实现或者反射的角度来看，关于继承的说法正确的是（`A`）
> 注：此处的继承不代表能调用
+ A.子类将继承父类的所有的数据域和方法
+ B.子类将继承父类的其可见的数据域和方法
+ C.子类只继承父类public方法和数据域
+ D.子类只继承父类的方法，而不继承数据域

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/4361002

在一个子类被创建的时候，首先会在内存中创建一个父类对象，然后在父类对象外部放上子类独有的属性，两者合起来形成一个子类的对象。所以所谓的继承使子类拥有父类所有的属性和方法其实可以这样理解，子类对象确实拥有父类对象中所有的属性和方法，但是父类对象中的私有属性和方法，子类是无法访问到的，只是拥有，但不能使用。就像有些东西你可能拥有，但是你并不能使用。所以子类对象是绝对大于父类对象的，所谓的子类对象只能继承父类非私有的属性及方法的说法是错误的。可以继承，只是无法访问到而已

### 3.命令javac-d参数的用途是？（`A`）
+ A.指定编译后类层次的根目录
+ B.指定编译时需要依赖类的路径
+ C.指定编译时的编码
+ D.没有这一个参数

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3590568

![Java编译参数](https://uploadfiles.nowcoder.com/images/20171210/1806883_1512918622871_6ACA09654A2C36B065ECC04F7746E3B3)

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

### 2.下列说法正确的有（ ）
+ A.环境变量可在编译source code时指定
+ B.在编译程序时，所能指定的环境变量不包括class path
+ C.javac一次可同时编译数个Java源文件
+ D.javac.exe能指定编译结果要置于哪个目录（directory）

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/3507444

C选项：使用 javac *.java，可编译当前目录下的所有java文件

### 3.JAVA反射机制主要提供了以下哪些功能？(`ABCD`)
+ A.在运行时判断一个对象所属的类
+ B.在运行时构造一个类的对象
+ C.在运行时判断一个类所具有的成员变量和方法
+ D.在运行时调用一个对象的方法

> 解答：https://www.nowcoder.com/profile/934336/myFollowings/detail/2287407

## 五、问答题