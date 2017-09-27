package service;

import data.DBService;
import dto.Item;
import dto.ShoppingList;
import dto.User;
import enumeration.Action;

import java.util.List;
import java.util.Map;

/**
 * @author Juhi Gaba
 */
public class ShoppingListService {

    /**
     * Create the list.
     *
     * @param list      List to be created.
     * @param userId    User id of the user creating the list.
     * @return          Boolean value if the list was created or not.
     */
    public boolean createList(ShoppingList list, String userId) {

        if (list == null) {

            return false;
        }

        if (userId == null || userId.isEmpty()) {

            return false;
        }

        UserService userService = new UserService();
        User user = userService.fetchUser(userId);
        user.getShoppingLists().add(list);

        DBService dbService = new DBService();
        return dbService.writeOrUpdateDBForUser(user);
    }

    /**
     * Fetch the list.
     * @param listId List id of the list to be fetched.
     * @param userId User id for which the list is to be fetched.
     * @return       Shopping list object.
     */
    public ShoppingList fetchList(String listId, String userId) {

        UserService userService = new UserService();
        User user = userService.fetchUser(userId);

        return user.getShoppingLists().stream()
                .filter(list -> listId.equals(list.getListId())).findFirst()
                .orElse(null);

    }

    /**
     * Update the list.
     * @param list        List to be updated.
     * @param actionItems Action performed.
     * @return            Updated shopping list.
     */
    public ShoppingList updateList(ShoppingList list,
                                   Map<Item, List<Action>> actionItems) {

        SynchronizeListService synchronizeListService = new SynchronizeListService();

        return synchronizeListService.updateListForAllUsers(list, actionItems);
    }

    /**
     * Delete the list.
     * @param list List to be deleted.
     * @return Boolean if the list was deleted or not.
     */
    public boolean deleteList(ShoppingList list) {

        SynchronizeListService synchronizeListService = new SynchronizeListService();
        synchronizeListService.deleteListFromOtherUsers(list);

        return true;
    }
}
