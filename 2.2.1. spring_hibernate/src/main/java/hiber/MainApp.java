package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        // Добавляем юзеров и машины
        User u1 = new User("name", "surname", "somemail@d");
        u1.setCar(new Car("toyota raw 4", 9999));
        User u2 = new User("john", "doe", "somemail2@c");
        u2.setCar(new Car("ford f150", 22));
        User u3 = new User("jane", "doe", "somemail3@b");
        u3.setCar(new Car("humvee", 0));
        User u4 = new User("user w/o a car", "surname", "nocar0@a");

        userService.add(u1);
        userService.add(u2);
        userService.add(u3);
        userService.add(u4);

        List<User> users = userService.listUsers();

        // Достаем юзеров
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());

            if (user.getCar() != null) {
                System.out.println("Car's series = " + user.getCar().getSeries());
                System.out.println("Car's model = " + user.getCar().getModel());
            }
            System.out.println();
        }

        // Поиск юзера с некоторой машиной
        System.out.println("SEARCH: User who has a \"humvee\" with series \"0\":");
        System.out.println(userService.getUser("humvee", 0).toString());
        System.out.println();

        context.close();
    }
}
