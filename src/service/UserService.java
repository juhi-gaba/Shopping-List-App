package service;

import com.google.gson.Gson;
import data.DBService;
import data.FileService;
import dto.ShoppingList;
import dto.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User service to fetch or create user.
 * @author Juhi Gaba
 */
public class UserService {

    /**
     * Fetches the user from the DB. Currently from the file.
     *
     * @param userId The id of the user to be fetched.
     * @return       The user of the corresponding Id.
     */
    public User fetchUser(String userId) {

        DBService db = new DBService();
        List<User> users = db.fetchAllUser();
        User user = users.stream()
                .filter(usr -> usr.getUserId().equals(userId)).findFirst()
                .orElse(null);

        if (user == null) {
            user = createDummyUser(userId);
        }

        return user;

    }

    /**
     * Creates a dummy user.
     *
     * @param userId User id.
     * @return       A dummy user.
     */
    private User createDummyUser(String userId) {

        return new User(userId, "Usr_" + userId, new ArrayList<ShoppingList>());
    }
    /**
     * Creates a user and add it to the list.
     *
     * @param list  List of the users.
     */
    public void createUser(List<User> list) {

        Gson gson = new Gson();
        FileService writerService = new FileService();
        for (User user : list) {
            String userData = gson.toJson(user);
            writerService.writeToUserData(userData);
        }

    }
}