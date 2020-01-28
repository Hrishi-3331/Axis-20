package axis.hrishi3331studio.vnit.axis20.Objects;

public class TeamMember {

    private String name;
    private String image;
    private String mail;
    private String contact;
    private String social;

    public TeamMember() {
    }

    public TeamMember(String name, String image, String mail, String contact, String social) {
        this.name = name;
        this.image = image;
        this.mail = mail;
        this.contact = contact;
        this.social = social;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }
}
