package hbLinkedTbls_oneToManyAnd.kolibriModels;

import javax.persistence.*;

@Entity
public class Author_z {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String lastName;

    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", unique = true, nullable = false, updatable = false)
    private Book_z book;

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

    public Book_z getBook() {
        return book;
    }

    public void setBook(Book_z book) {
        this.book = book;
    }
}