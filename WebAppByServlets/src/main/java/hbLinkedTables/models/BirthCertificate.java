package hbLinkedTables.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BirthCertificate {
    @Id
    @GeneratedValue
    private int id;

    private String date_of_birth_day; // дата рождения

    // у каждого свидетельства о рождении, свой гражданин, в единственном экземпляре
    @OneToOne
    private Citizen citizen;

    public String getDate_of_birth_day() {
        return date_of_birth_day;
    }

    public void setDate_of_birth_day(String date_of_birth_day) {
        this.date_of_birth_day = date_of_birth_day;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}