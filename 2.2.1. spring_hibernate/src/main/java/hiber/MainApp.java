package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Bob", "Marly", "user1@mail.ru");
      User user2 = new User("Lara", "Croft", "user2@mail.ru");
      User user3 = new User("Jony", "Mnemonyk", "user3@mail.ru");
      User user4 = new User("Morgan", "Freeman", "user4@mail.ru");

      Car car1 = new Car("Lada", 17);
      Car car2 = new Car("BMW", 7);
      Car car3 = new Car("Honda", 3);
      Car car4 = new Car("Opel", 12);


      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }
      System.out.println("");
      System.out.println(" валаделец авто  Honda");
      System.out.println(userService.getCarFromUser("Honda", 3));
      System.out.println("");
      try {
         User notFoundUser = userService.getCarFromUser("Mers", 13);
      } catch (NoResultException e) {
         System.out.println("User not found");

      }

      context.close();
   }
}
