package axis.hrishi3331studio.vnit.axis20.Objects;

public class Exhibition {
    private String title;
    private String image;
    private String date;
    private String venue;
    private String description;
    private String register;

    public Exhibition() {
    }

    public Exhibition(String title, String image, String date, String venue, String description, String register) {
        this.title = title;
        this.image = image;
        this.date = date;
        this.venue = venue;
        this.description = description;
        this.register = register;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
