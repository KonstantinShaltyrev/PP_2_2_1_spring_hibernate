package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.addUser(new User("User1", "Lastname1", "user1@mail.ru", new Car("Audi", 1)));
      userService.addUser(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", 2)));
      userService.addUser(new User("User3", "Lastname3", "user3@mail.ru", new Car("Ford", 3)));
      userService.addUser(new User("User4", "Lastname4", "user4@mail.ru", new Car("Lada", 4)));
      userService.addUser(new User("User5", "Lastname5", "user5@mail.ru"));
      userService.addUser(new User("User6", "Lastname6", "user6@mail.ru"));
      userService.addUser(new User("User7", "Lastname7", "user7@mail.ru", new Car("Audi", 1)));
      userService.addUser(new User("User8", "Lastname8", "user8@mail.ru", new Car("Audi", 1)));

      List<User> users = userService.getUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + (user.getCar() == null ? "Car wasn't found in database" : user.getCar().toString()));
         System.out.println();
      }

      List<User> carOwner = userService.findUser(new Car("Audi", 1));
      System.out.println("Model \"Audi\" series \"1\" owners:\n");
      for (User user : carOwner) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().toString());
         System.out.println();
      }

      context.close();
   }
}
