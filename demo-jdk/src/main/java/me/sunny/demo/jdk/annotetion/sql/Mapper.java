package me.sunny.demo.jdk.annotetion.sql;

@Table("t_user")
public class Mapper {
    @Column("id")
    private int id;

    @Column("username")
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
