package service;

import data.DBService;
import dto.Item;
import dto.ShoppingList;
import dto.User;
import enumeration.Action;

import java.util.*;

/**
 * Synchronize list service.
 * @author Juhi Gaba
 */
public class SynchronizeListService {

    /**
     * Delete the list from the other users.
     *
     * @param list List that is to be deleted.
     */
    public synchronized void deleteListFromOtherUsers(ShoppingList list) {

        DBService service = new DBService();
        Map<User, ShoppingList> userShoppingListMap = service.fetchAllUserByListId(list.getListId());

        if (userShoppingListMap != null) {

            for (Map.Entry<User, ShoppingList> entry : userShoppingListMap.entrySet()) {

                User user = entry.getKey();
                ShoppingList previousList = entry.getValue();

                if(list.getListId().equals(previousList.getListId())) {
                    user.getShoppingLists().remove(previousList);
                }
            }
        }
        if(userShoppingListMap != null) {
            service.updateDBForUsers(userShoppingListMap.keySet());
        }

    }

    /**
     * Update the list for all users.
     *
     * @param actionItems   Action to be performed.
     * @param currentList   List on which update is to be done.
     */
    public synchronized ShoppingList updateListForAllUsers(ShoppingList currentList, Map<Item, List<Action>> actionItems) {

        ItemService itemService = new ItemService();
        List<Item> keys = new ArrayList<>(actionItems.keySet());

        for (Item currentItem : keys) {

            List<Action> action = actionItems.get(currentItem);


            for (Action actionToPerform : action) {

                switch (actionToPerform) {
                    case CREATE:
                        currentList.getItemList().add(currentItem);
                        break;

                    case UPDATE:
                        Item existingItem = currentList
                                .getItemList()
                                .stream()
                                .filter(x -> x.getItemId().equals(
                                        currentItem.getItemId())).findFirst()
                                .orElse(null);

                        itemService.updateItem(existingItem, currentItem);
                        break;

                    default:
                        break;
                }
            }
        }

        currentList.setLastUpdated(new Date());

        DBService dbService = new DBService();
        Map<User, ShoppingList> users = dbService
                .fetchAllUserByListId(currentList.getListId());

        if (users != null) {

            for (Map.Entry<User, ShoppingList> entry : users.entrySet()) {
                User user = entry.getKey();
                ShoppingList previousList = entry.getValue();
                user.getShoppingLists().remove(previousList);
                user.getShoppingLists().add(currentList);
            }
        }

        if (dbService.updateDBForUsers(users.keySet())) {
            return currentList;
        } else {
            return null;
        }

    }
}
