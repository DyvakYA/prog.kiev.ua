package mysql1.entity;

public class Client implements Identified {
    private long id;
    private String name;
    private int age;

    public static class Builder {

        Client instance = new Client();

        public Builder setId(long id) {
            instance.id = id;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setAge(int age) {
            instance.age = age;
            return this;
        }

        public Client build() {
            return instance;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
