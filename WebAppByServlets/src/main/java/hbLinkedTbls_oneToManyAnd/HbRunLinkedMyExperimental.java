package hbLinkedTbls_oneToManyAnd;

import hbLinkedTbls_oneToManyAnd.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HbUniversalUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HbRunLinkedMyExperimental {
    public static void main(String[] args) {
        exampleManyToManyAuthor();

        // образец юза связанностей OneToMany
        // ни один из способов удаления не удаляет таблицу tag_for_article_article
        // что за tbl и зачем её создал HB ..?
//        exampleOneToManyRemoveTag();
//        exampleOneToManyRemoveArticle();
//        exampleOneToManySetupArticle();

        // образец юза связанностей OneToOne
//        exampleOneToOneRemoveBeck();
//        exampleOneToOneSetupBeck();

        // Простейшая сущность добавляемая в БД
//        simpleExample();

//        SimpleEntity simpleEntity = getSimpleEntity(98);
//        System.out.println(simpleEntity.getNote());
    }

    private static void exampleManyToManyAuthor() {
        Author authorBeckKent = new Author();
        authorBeckKent.setAuthor_name("Beck Kent");

        List<Author> authorListForExtremeProgram = new ArrayList<>();
        authorListForExtremeProgram.add(authorBeckKent);

        Book bookExtremeProgram = new Book();
        bookExtremeProgram.setBook_name("Extreme Programming:Test Driven Development");
        bookExtremeProgram.setPublishing_house("Saint Petersburg");

        // сеттим конкретной книге список её авторов
        bookExtremeProgram.setAuthorList(authorListForExtremeProgram);

        List<Book> bookListForBeckKent = new ArrayList<>();
        bookListForBeckKent.add(bookExtremeProgram);

        // сеттим конкретному автору, список его книг
        authorBeckKent.setBookList(bookListForBeckKent);


        Object[] objects = new Object[]{authorBeckKent, bookExtremeProgram};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        session.save(authorBeckKent);
        session.save(bookExtremeProgram);

        transaction.commit();
        session.close();
    }

    private static void exampleOneToManyRemoveTag() { // удаляет из таблицы tag_for_article
        Object[] objects = new Object[]{new Article(), new Tag_for_article()};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        Tag_for_article tagForArticle = session.get(Tag_for_article.class, 93);
        // если статья к тегу существует то
        // ОШИБКА: UPDATE или DELETE в таблице "tag_for_article" нарушает ограничение внешнего ключа
        // "fk3502ro49xdhynnb1pd4uuuuqv" таблицы "article_tag_for_article
//        tagForArticle.setArticleList(null); // если раскомментить не поможет..
        session.remove(tagForArticle);

        transaction.commit();
        session.close();
    }

    // удаляет из таблицы article и article_tag_for_article
    private static void exampleOneToManyRemoveArticle() {
        Object[] objects = new Object[]{new Article(), new Tag_for_article()};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        Article article = session.get(Article.class, 89);
// если раскомментенно, то теги связанные с ней должен не удалить в таблице tab_for_article
        // а в article_tag_for_article - удалить в любом случае
        // но нет работает как то по своему..
        article.setForArticleList(null);
        session.remove(article); // удаляет статью и все теги к ней

        transaction.commit();
        session.close();
    }

    private static void exampleOneToManySetupArticle() {
        // создаёт целых 3 связанные таблицы _ и 4я таблица пустая, неясно для чего..
        Article article = new Article();   // создали статью
        article.setArticle_name("Java Best Practices for Many-To-One..");
        article.setArticle_content("When you model your database, you will most likely..");

        List<Article> articleList = new ArrayList<>();

        // создали теги
        Tag_for_article tagJava = new Tag_for_article("Java");
        Tag_for_article tagJpa = new Tag_for_article("JPA");

        tagJava.setArticleList(articleList);  // добавили связь список статей с конкретным тегом
        tagJpa.setArticleList(articleList);

        List<Tag_for_article> tagForArticleList = new ArrayList<>();
        Collections.addAll(tagForArticleList, tagJava, tagJpa); // заполнили список тегов

        article.setForArticleList(tagForArticleList);  // добавили список тегов к статье

        Object[] objects = new Object[]{article, tagJava};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        session.save(article);
        session.save(tagJava);
        session.save(tagJpa);

        transaction.commit();
        session.close();
    }

    private static void exampleOneToOneRemoveBeck() {
        Object[] objects = new Object[]{new Citizen(), new BirthCertificate()};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        // для удаления если не знаем id гражданина но знаем его фамилию
        List<Citizen> citizenList = (List<Citizen>) session.createQuery("FROM Citizen ").list();
//        Citizen citizenForDelete = null;
//        for (Citizen item : citizenList) {
//            if ("Beck".equalsIgnoreCase(item.getCitizen_surname())) {
//                citizenForDelete = item;
//                break;
//            }
//        }

        // для удаления гражданина по его id, получаем самого гражданина из базы
        Citizen citizenForDelete = session.get(Citizen.class, 3);

        session.delete(citizenForDelete); // и удаляем
        transaction.commit();
        session.close();
    }

    private static void exampleOneToOneSetupBeck() {
        Citizen citizenKent = new Citizen();
        citizenKent.setCitizen_name("Kent");
        citizenKent.setCitizen_surname("Beck");

        BirthCertificate birthCertificate = new BirthCertificate();
        birthCertificate.setCitizen(citizenKent);
        birthCertificate.setDate_of_birth_day("31 марта 1961");

        citizenKent.setBirth_certificate(birthCertificate);

        Object[] objects = new Object[]{citizenKent, birthCertificate};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        session.save(citizenKent);
        session.save(birthCertificate);
        transaction.commit();
        session.close();
//        	// и это будет равносильно в SQL
        // create table citizen   (
        //    id                   integer not null
        //        constraint citizen_pkey
        //            primary key,
        //    citizen_middlename   varchar(255),
        //    citizen_name         varchar(255),
        //    citizen_surname      varchar(255),
        //    birth_certificate_id integer
        //        constraint fkn4n8en4rh6naj417vqhp63ayf
        //            references birthcertificate   );
        //
        //alter table citizen
        //    owner to postgres;

        // и для BirthCertificate
        // create table birthcertificate   (
        //    id                integer not null
        //        constraint birthcertificate_pkey
        //            primary key,
        //    date_of_birth_day varchar(255),
        //    citizen_id        integer
        //        constraint fkegajwu0l4oqlq4i4k7i2p742j
        //            references citizen   );
        //
        //alter table birthcertificate
        //    owner to postgres;

    }

    private static void simpleExample() {
        SimpleEntity simpleEntity = new SimpleEntity();
//        simpleEntity.setNote("my new notes");
//        simpleEntity.setDescription("my new description");

        Object[] objects = new Object[]{simpleEntity};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();
//
        session.save(simpleEntity);

        // при сохранении и любой полноценной транзакции, а не просто запроса выборки.. обязательно делать commit
        transaction.commit();
        session.close();
//        	// и это будет равносильно в SQL
//	create table simpleentity   (
//	    id          serial not null
//	        constraint simpleentity_pkey
//	            primary key,
//	    description varchar(255),
//	    note        varchar(255)   );
//
//	alter table simpleentity
//	    owner to postgres;
//
//	// и после добавлению пользователя
    }

    //    https://stackoverflow.com/questions/21574236/how-to-fix-org-hibernate-lazyinitializationexception-could-not-initialize-prox
    public static SimpleEntity getSimpleEntity(int id) {
        // Get for UPD
//        SimpleEntity simpleEntity1 = session.get(SimpleEntity.class, 98);
//        simpleEntity1.setNote("is new note UPD");

        Session session = null;
        SimpleEntity entity = null;
        try {
            Object[] obj = {new SimpleEntity()};
            session = HbUniversalUtil.getSessionFactory(obj).openSession();
            Transaction transaction = session.beginTransaction();

//            entity = (SimpleEntity) session.load(SimpleEntity.class, id);
            entity = (SimpleEntity) session.get(SimpleEntity.class, id);
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }

    //  could not initialize proxy [hbLinkedTables.models.SimpleEntity
    // https://stackoverflow.com/questions/21574236/how-to-fix-org-hibernate-lazyinitializationexception-could-not-initialize-Aprox
    public static SimpleEntity loadSimpleEntity(int id) {
        Session session = null;
        SimpleEntity entity = null;
        try {
            Object[] obj = {new SimpleEntity()};
            session = HbUniversalUtil.getSessionFactory(obj).openSession();
//            Transaction transaction = session.beginTransaction();

            entity = (SimpleEntity) session.load(SimpleEntity.class, id);
//            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }
}