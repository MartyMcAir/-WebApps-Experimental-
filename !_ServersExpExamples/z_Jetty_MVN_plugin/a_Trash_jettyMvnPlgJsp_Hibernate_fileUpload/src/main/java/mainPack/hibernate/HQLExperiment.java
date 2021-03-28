package mainPack.hibernate;


import mainPack.hibernate.models.Auto;
import mainPack.hibernate.models.User;
import mainPack.hibernate.services.UserService;

public class HQLExperiment {
    public static void main(String[] args) {
        addUser();

    }

    private static void addUser() {
        UserService userService = new UserService();
        User user = new User("Thanos", 26);
        userService.saveUser(user); // добавляем юзера в БД таблицу _ запрос в БД

        Auto ferrari = new Auto("Ferrari", "red");
        Auto ford = new Auto("Maserati", "black");

        // просто сетится внутри класса машины
        ferrari.setUser(user); // ставим владельца для авто
        ford.setUser(user);

        // тпм внутри тож самое, что выше т.е. машине сеттится юзер, так что походу те строки лишние
        user.addAuto(ferrari); // кидаем юзеру его машину
        user.addAuto(ford);


        userService.updateUser(user); // сохраняет юзера _ запрос в БД
    }
}
