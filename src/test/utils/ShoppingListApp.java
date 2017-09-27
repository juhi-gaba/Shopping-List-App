package test.utils;

import dto.Item;
import dto.ShoppingList;
import enumeration.Action;
import service.ShoppingListService;

import java.util.List;
import java.util.Map;

/**
 * @author Juhi Gaba
 */
public class ShoppingListApp implements Runnable {

    /**
     * The list id.
     */
    private String listId;

    /**
     * User id.
     */
    private String userId;

    /**
     * Action to be performed.
     */
    private Map<Item, List<Action>> actionItems;

    /**
     * Constructor for Shopping List App.
     * @param listId        List id.
     * @param userId        User id.
     * @param actionItems   Action to be performed.
     */
    public ShoppingListApp(String listId, String userId, Map<Item, List<Action>> actionItems) {
        this.listId = listId;
        this.userId = userId;
        this.actionItems = actionItems;
    }

    @Override
    public void run() {

        ShoppingListService shoppingListService = new ShoppingListService();
        ShoppingList list = shoppingListService.fetchList(listId, userId);

        shoppingListService.updateList(list, actionItems);

    }

}
