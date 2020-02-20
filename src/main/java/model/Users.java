package model;

import java.util.Objects;

public class Users {
    private Long id;
    private String name;
    private String password;
    private String email;

    public Users() {};
    public Users(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public Users(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPass(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPass() { return password; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return name.equals(users.name) &&
                password.equals(users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
