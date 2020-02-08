package thecodingstache.tedxuthlarissa.Model;

public class Team {
    private String name;
    private String title;
    private int photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Team(String name, String title, int photo) {
        this.name = name;
        this.title = title;
        this.photo = photo;
    }
    public Team(){

    }
}