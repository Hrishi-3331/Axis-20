package axis.hrishi3331studio.vnit.axis20.Objects;

public class Event {

    private String title;
    private String image;

    public Event() {
    }

    public Event(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
