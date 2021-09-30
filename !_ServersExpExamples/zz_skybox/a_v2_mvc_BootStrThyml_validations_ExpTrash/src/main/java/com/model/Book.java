package com.model;

import com.validator.TitleAnnotation;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Serializable, need  for Validation !?
//public class Book implements Serializable {
//    private static final long serialVersionUID = -8582553475226281591L;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Exception encountered during context initialization
    // Closing JPA EntityManagerFactory for persistence unit 'UsersPU'
    // {book.author.invalid} - use msg from validation.properties
    // ERR BeanCreationException
    //    @Size(max = 20, min = 3, message = "book.author.invalid")
    //    @Size(max = 20, min = 3, message = "{book.author.invalid}")

    //    @NotEmpty(message = "Please enter author")
    // {from validation.properties} - don't work
    @NotEmpty(message = "{book.author.invalid2}")
    private String author;

    //    @NotNull(message = "title is required.")
//    @NotNull(message = "{book.title.invalid}")
//    @NotBlank
//    @NotNull()

    // моя аннотация не работает
//    @TitleAnnotation
    private String title;

    //    @Min(value = 1000, message = "Student ID must be at least 4 digits.")
    //    @Size(min = 1, message = "required")
    //    @Digits(integer = 3, fraction = 0, message = "Не более 3-х знаков")

    // _ ERR _ No validator could be found for constraint 'javax.validation.constraints.Size
    //    @Size(min = 2, max = 30, message = "size required min 2 max 30")
    // _ ERR _ Invocation of init method failed; nested exception is javax.validation.ConstraintViolationException: Validation failed
    //    @Range(min = 2, max = 30, message = "size required min 2 max 30")

    //    @Min(2)
    //    @Max(30)
    private int size;

//    @NotEmpty
//    @Email
//    private String email;

//    @NotNull(message = "Student gender is required.")
//    @Min(18)
//    @Max(100)
//    @Digits(integer = 3, fraction = 0, message = "Не более 3-х знаков")
//    private int age;

    // https://www.journaldev.com/2668/spring-validation-example-mvc-validator
//    @Past
//    @NotNull
//    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    private Date birthday;

//    @Pattern(regexp = "[0-9]+")
//    @Size(min = 8, max = 14)
//    private String phone;

    // https://habr.com/ru/post/424819/
    // используем свою аннотацию
//    @Phone
//    private String phone;

    public List<String> getAllFields() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, Integer.toString(id), author, title, Integer.toString(size));
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}