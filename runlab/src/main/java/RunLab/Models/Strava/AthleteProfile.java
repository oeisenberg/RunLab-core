package RunLab.Models.Strava;

public class AthleteProfile {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String bio;
    private String city;
    private String state;
    private String country;
    private String sex;
    private Boolean premium;
    private Boolean summit;
    private String created_at;
    private String updated_at;
    private float weight;
    private String profile;

    public AthleteProfile() {

    }

    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getBio(){
        return this.bio;
    }

    public String getCity(){
        return this.city;
    }

    public String getState(){
        return this.state;
    }

    public String getCountry(){
        return this.country;
    }

    public String getSex(){
        return this.sex;
    }

    public Boolean getPremium(){
        return this.premium;
    }

    public Boolean getSummit(){
        return this.summit;
    }

    public String getCreatedAt(){
        return this.created_at;
    }

    public String getUpdatedAt(){
        return this.updated_at;
    }

    public float getWeight(){
        return this.weight;
    }

    public String getProfile(){
        return this.profile;
    }

}