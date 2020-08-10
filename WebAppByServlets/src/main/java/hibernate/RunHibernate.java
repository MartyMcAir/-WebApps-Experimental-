package hibernate;

import hibernate.models.Auto;
import hibernate.models.User;
import hibernate.services.UserService;

public class RunHibernate {
    // https://javarush.ru/groups/posts/hibernate-java
    // https://javarush.ru/groups/posts/523-vashe-pervoe-prilozhenie-s-ispoljhzovaniem-java-servletov
    static UserService userService = new UserService();

    public static void main(String[] args) {
        // нельзя таким образом добавить несколько юзеров!
        // таблицы просто заново перезатираеютя и всё
        createTbl_andAddUserWithAuto();
//        deleteUserById(1); // удаляет и авто юзера так же удаляются
    }

    private static void createTbl_andAddUserWithAuto() {
        // если таких таблиц нет он сам создаст
        User user = createUser("Gomora", 999);
        Auto ferrari = new Auto("Ferrari", "red");
        Auto ford = new Auto("Maserati", "black");

        // просто сетится внутри класса машины _ лишние 2 строки
//        ferrari.setUser(user); // ставим владельца для авто
//        ford.setUser(user);

        // тпм внутри тож самое, что выше т.е. машине сеттится юзер, так что те строки лишние
        user.addAuto(ferrari); // кидаем юзеру его машину
        user.addAuto(ford);

//        user.setName("IronMan"); // переименовываем юзера
        userService.updateUser(user); // сохраняет юзера _ запрос в БД
//        userService.deleteUser(user); // удалить юзера _ при этом авто будут удалены
    }

    private static void deleteUserById(int id) {
        User user1 = userService.findUser(id);
        userService.deleteUser(user1);
    }

    private static User createUser(String userName, int userAge) {
        User user = new User(userName, userAge);
        userService.saveUser(user); // добавляем юзера в БД таблицу _ запрос в БД
        return user;
    }
}
