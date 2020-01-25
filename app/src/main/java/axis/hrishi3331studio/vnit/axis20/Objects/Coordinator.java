package axis.hrishi3331studio.vnit.axis20.Objects;

public class Coordinator {

    private String name;
    private String email;
    private String number;
    private String image;
    private String social;

    public Coordinator() {
    }

    public Coordinator(String name, String email, String number, String image, String social) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.social = social;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
