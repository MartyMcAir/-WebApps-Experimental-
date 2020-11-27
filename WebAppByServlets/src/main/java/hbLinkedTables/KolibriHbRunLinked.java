package hbLinkedTables;

import hbLinkedTables.kolibriModels.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Examples from:
// https://devcolibri.com/%D0%BA%D0%B0%D0%BA-%D1%81%D0%B2%D1%8F%D0%B7%D0%B0%D1%82%D1%8C-entity-%D0%B2-jpa/
public class KolibriHbRunLinked {
    public static void main(String[] args) {
//        originOneToOne();

        originZOneToOne();
    }

    private static void originZOneToOne() {
        Author_z author = new Author_z();
        author.setName("Bek");
        author.setLastName("Kek");

        Book_z book = new Book_z();
        book.setName("Java Efficient");
        book.setAuthor(author);
        author.setBook(book);

        Object[] obj = {book, author};
        final Session session = HbUniversalUtil.getSessionFactory(obj).openSession();
        final Transaction transaction = session.beginTransaction();

        // сначала сохранять book, иначе Exception
        session.save(book);
        session.save(author);

        transaction.commit();
        session.close();

        // in SQL
        //create table author_z (
        //    id       integer not null
        //        constraint author_z_pkey
        //            primary key,
        //    lastname varchar(255),
        //    name     varchar(255),
        //    book_id  integer not null
        //        constraint uk_p1xewantphe4l5ghi7l3253w
        //            unique
        //        constraint fk78n58nd2ayifemllefb88k619
        //            references book_z );
        //alter table author_z
        //    owner to postgres;

        // create table book_z (
        //    id   integer not null
        //        constraint book_z_pkey
        //            primary key,
        //    name varchar(255) );
        //alter table book_z
        //    owner to postgres;
    }

    private static void originOneToOne() {
        Author author = new Author();
        author.setName("Bek");
        author.setLastName("Kek");

        Book book = new Book();
        book.setName("Java Efficient");
        book.setAuthor(author);
        author.setBook(book);

        Object[] obj = {book, author};
        final Session session = HbUniversalUtil.getSessionFactory(obj).openSession();
        final Transaction transaction = session.beginTransaction();

        // сначала сохранять book, иначе Exception
        session.save(book);
        session.save(author);

        transaction.commit();
        session.close();
    }
}
