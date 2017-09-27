package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import dto.ShoppingList;
import dto.User;

/**
 * @author Juhi Gaba
 */
public class DBService {

    /**
     * Fetch all users.
     * @return List of users.
     */
    public List<User> fetchAllUser() {
        List<User> users = new ArrayList<User>();

        FileService fileService = new FileService();
        String output = fileService.readFileFromUserData();

        if (output == null || output.isEmpty()) {
            return users;
        }

        String[] records = output.split("\n");
        Gson gson = new Gson();

        for (String string : records) {
            User user = gson.fromJson(string, User.class);
            users.add(user);
        }
        return users;
    }

    /**
     * Fetch user by list id.
     * @param listId Id of the list.
     * @return       A map containing user as key and the shopping list as value.
     */
    public Map<User, ShoppingList> fetchAllUserByListId(String listId) {
        Map<User, ShoppingList> users = new HashMap<User, ShoppingList>();

        for (User user : this.fetchAllUser()) {
            ShoppingList list = user.getShoppingLists().stream()
                    .filter(x -> x.getListId().equals(listId)).findFirst()
                    .orElse(null);
            if (list != null) {

                users.put(user, list);
            }
        }

        return users;
    }

    /**
     * Update the DB for users.
     * @param updatedUsers Users to be updated
     * @return Boolean if user was updated or not.
     */
    public  boolean updateDBForUsers(Set<User> updatedUsers) {
        FileService fileService = new FileService();

        String output = fileService.readFileFromUserData();

        if (output == null || output.isEmpty()) {
            return false;
        }

        Map<String, User> userIdUserMap = updatedUsers.stream().collect(
                Collectors.toMap(User::getUserId, Function.identity()));

        String[] records = output.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        Gson gson = new Gson();
        for (String string : records) {
            User user = gson.fromJson(string, User.class);
            String updatedOutput = string;

            if (userIdUserMap.containsKey(user.getUserId())) {
                User updatedUser = userIdUserMap.get(user.getUserId());
                updatedOutput = gson.toJson(updatedUser);
            }

            stringBuilder.append(updatedOutput).append("\n");
        }

        return fileService.updateUserData(stringBuilder.toString());
    }

    /**
     * Write or update DB for a single user.
     *
     * @param updatedUser User.
     * @return Boolean.
     */
    public boolean writeOrUpdateDBForUser(User updatedUser) {
        FileService fileService = new FileService();

        String output = fileService.readFileFromUserData();

        Gson gson = new Gson();
        if (output == null || output.isEmpty()) {

            String data = gson.toJson(updatedUser);
            return fileService.writeToUserData(data);
        }

        String[] records = output.split("\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : records) {
            User user = gson.fromJson(string, User.class);
            String updatedOutput = string;

            if (updatedUser.getUserId().equals(user.getUserId())) {
                updatedOutput = gson.toJson(updatedUser);
            }

            stringBuilder.append(updatedOutput).append("\n");
        }

        return fileService.updateUserData(stringBuilder.toString());
    }
}
