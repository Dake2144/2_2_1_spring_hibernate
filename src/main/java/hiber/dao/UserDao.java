package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   List<User> listUsers();
   //получение юзера по машине
   List<User> getUserOnCar(String model, int series);

}
