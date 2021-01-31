package hbLinkedTbls_oneToManyAnd.makingStock;

import util.HbUniversalUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

// https://mkyong.com/hibernate/hibernate-many-to-many-relationship-example-annotation/
public class MakingRun {
    public static void main(String[] args) {
//        stockSetupInDb();
//        removeStock();
//        removeCategoryWrongWay();
    }

    private static void removeCategoryWrongWay() { // Exception..
        Object[] objects = new Object[]{new Category(), new Stock()};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        Category category = session.get(Category.class, 1);
        // ОШИБКА: UPDATE или DELETE в таблице "category" нарушает ограничение внешнего
        // ключа "fk923ytqjj0ilxye96923chpcte" таблицы "stock_category"
        category.setStocks(null);
        session.remove(category); // удаление работает _ при этом удаляет и связанную категорию

        transaction.commit();
        session.close();
    }

    private static void removeStock() {
        Object[] objects = new Object[]{new Category(), new Stock()};
        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();
        Transaction transaction = session.beginTransaction();

        Stock stock = session.get(Stock.class, 7);
        stock.setCategories(null); // а если это рас комментенно, то связанную категорию не удаляет
        session.remove(stock); // удаление работает _ при этом удаляет и связанную категорию

        transaction.commit();
        session.close();
    }

    private static void stockSetupInDb() {
        // при повторном запуске пишет что объект существ в базе
        // а для удаления, через DataGrid
        // [2BP01] ОШИБКА: удалить объект таблица category нельзя, так как от него зависят другие объекты
        //  Подробности: ограничение fk923ytqjj0ilxye96923chpcte в отношении таблица stock_category зависит от объекта таблица category
        //  Подсказка: Для удаления зависимых объектов используйте DROP ... CASCADE.

        System.out.println("Hibernate many to many (Annotation)");
//        Session session = HbUniversalUtil.getSessionFactory(new Class[]{Stock.class})
//        Session session = HbUniversalUtil.getSessionFactory().openSession();

        Object[] objects = new Object[]{
                new Category(), new Stock()
        };

        Session session = HbUniversalUtil.getSessionFactory(objects).openSession();

        session.beginTransaction();

        Stock stock = new Stock();
        stock.setStockCode("13333333");
        stock.setStockName("APL");

        Category category1 = new Category("CONSUM", "CONSUME COMPAN");
        Category category2 = new Category("INVESTME", "INVESTMEN COMPAN");

        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);

        stock.setCategories(categories);

        session.save(stock);

        session.getTransaction().commit();
        System.out.println("Done");
    }
}