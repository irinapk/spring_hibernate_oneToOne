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

      // Создание пользователей и машин
      User[] users = {new User("User1", "Lastname1", "user1@mail.ru"),
              new User("User2", "Lastname2", "user2@mail.ru"),
              new User("User3", "Lastname3", "user3@mail.ru"),
              new User("User4", "Lastname4", "user4@mail.ru")};

      Car[] cars = {new Car("BMW", 1000) ,
              new Car("Jeep", 2000),
              new Car("Audi", 3000),
              new Car("KIA", 4000)};

      for (int i = 0; i < 4; i++) {
         users[i].setCar(cars[i]);
         userService.add(users[i]);
      }

      // Достаем всех пользователей обратно
      List<User> allUsers = userService.listUsers();
      for (User user : allUsers) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car model and series = " + user.getCar().toString());
         System.out.println("-------");
      }

      // Тест для метода который достаёт юзера, владеющего машиной по ее модели и серии
      System.out.println("\n --------------- " +
              "\nTest for getting a user by car model and series." +
              "\nThe car owner is " + userService.getUserByCar("Jeep", 2000).toString());

      context.close();
   }
}

