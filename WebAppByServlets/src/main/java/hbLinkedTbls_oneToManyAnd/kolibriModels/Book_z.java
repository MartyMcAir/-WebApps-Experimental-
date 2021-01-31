package hbLinkedTbls_oneToManyAnd.kolibriModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Book_z {
    @Id
    @GeneratedValue
    Integer id;
    private String name;

    @OneToOne(optional = false, mappedBy = "book")
    public Author_z author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author_z getAuthor() {
        return author;
    }

    public void setAuthor(Author_z author) {
        this.author = author;
    }
}