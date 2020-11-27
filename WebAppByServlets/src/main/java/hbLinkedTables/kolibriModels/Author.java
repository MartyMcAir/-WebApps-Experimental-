package hbLinkedTables.kolibriModels;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String lastName;

    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", unique = true, nullable = false, updatable = false)
    private Book book;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}