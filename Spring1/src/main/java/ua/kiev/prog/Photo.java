package ua.kiev.prog;

/**
 * Created by User on 12/30/2017.
 */
public class Photo {

    private long id;
    private String name;
    private byte[] file;

    public static class Builder {

        Photo instance = new Photo();

        public Builder setId(long id) {
            instance.id = id;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setFile(byte[] file) {
            instance.file = file;
            return this;
        }

        public Photo build() {
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
