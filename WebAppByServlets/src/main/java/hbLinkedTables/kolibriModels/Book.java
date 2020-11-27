package hbLinkedTables.kolibriModels;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    Integer id;
    private String name;

    @OneToOne(optional = false, mappedBy = "book")
    public Author author;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}