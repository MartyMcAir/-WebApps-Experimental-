package hbLinkedTbls_oneToManyAnd.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // (POJO объект _ сущность)
public class SimpleEntity {
    @Id
    @GeneratedValue // strategy - сам выбирает
    private int id;
    private String note;
    private String description;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}