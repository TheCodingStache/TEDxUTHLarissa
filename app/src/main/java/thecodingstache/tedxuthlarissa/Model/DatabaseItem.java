package thecodingstache.tedxuthlarissa.Model;

public class DatabaseItem {
    private int id;
    private byte[] photo;

    public DatabaseItem(int id, byte[] photo) {
        this.id = id;
    }
    public DatabaseItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
