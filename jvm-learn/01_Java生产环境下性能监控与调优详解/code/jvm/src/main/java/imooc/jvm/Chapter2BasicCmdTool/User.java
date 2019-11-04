package imooc.jvm.Chapter2BasicCmdTool;

/***********************************************************
 * @note      : 测试实体类
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/13 14:31
 ***********************************************************/
public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
