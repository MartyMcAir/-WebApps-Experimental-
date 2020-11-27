package hbLinkedTables.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany // одну книгу писали несколько авторов
    private List<Author> authorList;

    private String book_name;
    private String amount_of_pages;
    private String publishing_house;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAmount_of_pages() {
        return amount_of_pages;
    }

    public void setAmount_of_pages(String amount_of_pages) {
        this.amount_of_pages = amount_of_pages;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}