package hbLinkedTables.models;

import javax.persistence.*;

@Entity
public class Citizen { // гражданин
    @Id
    @GeneratedValue
    private int id;

    private String citizen_name;
    private String citizen_surname;
    private String citizen_middleName;

    // у каждого гражданина, своё персональное свидетельство о рождении, в единственном экземпляре

    // orphanRemoval = true - сообщаем о том что существование свидетельства о рождении в базе данных, без гражданина
    // бессмысленно и потому, при удалении гражданина из БД, следует и удалять его свидетельство
    @OneToOne(orphanRemoval = true)
    private BirthCertificate birth_certificate;

    public String getCitizen_name() {
        return citizen_name;
    }

    public void setCitizen_name(String citizen_name) {
        this.citizen_name = citizen_name;
    }

    public String getCitizen_surname() {
        return citizen_surname;
    }

    public void setCitizen_surname(String citizen_surname) {
        this.citizen_surname = citizen_surname;
    }

    public String getCitizen_middleName() {
        return citizen_middleName;
    }

    public void setCitizen_middleName(String citizen_middleName) {
        this.citizen_middleName = citizen_middleName;
    }

    public BirthCertificate getBirth_certificate() {
        return birth_certificate;
    }

    public void setBirth_certificate(BirthCertificate birth_certificate) {
        this.birth_certificate = birth_certificate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}