package spring;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/29 9:24
 * @Description
 */
public class User {
    private String name;
    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
