package test;

import data.FileService;
import dto.Item;
import dto.ShoppingList;
import entry.DummyDataGenerator;
import enumeration.Action;
import org.junit.Assert;
import org.junit.Test;
import service.ShoppingListService;
import test.utils.ShoppingListApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Juhi Gaba
 */
public class ShoppingAppListTest {
    private static int list_upperLimit = 7;
    private static int item_upperLimit = 10;
    private int noOfItems = 5;


    @Test
    public void ShoppingListService_CreateList() {
        String userId = "2";
        ShoppingList list = getDummyData(list_upperLimit, item_upperLimit,
                noOfItems);
        ShoppingListService shoppingListService = new ShoppingListService();
        assertTrue(shoppingListService.createList(list, userId));

        FileService fileService = new FileService();
        String output = fileService.readFileFromUserData();

        Assert.assertTrue(output.contains(list.getListId()));
    }

    /**
     * Generate dummy data for test.
     *
     * @param list_upperLimit List upper limit
     * @param item_upperLimit Item upper limit
     * @param noOfItems       Number of items.
     * @return                Dummy shopping list data.
     */
    private static ShoppingList getDummyData(int list_upperLimit,
                                             int item_upperLimit, int noOfItems) {

        DummyDataGenerator generator = new DummyDataGenerator(list_upperLimit,
                item_upperLimit);
        return generator.getDummyList(noOfItems);

    }


    @Test
    public void ShoppingListService_fetchList() {
        String userid = "2";
        String listId = "3";
        ShoppingListService shoppingListService = new ShoppingListService();
        ShoppingList list = shoppingListService.fetchList(listId, userid);
        System.out.println(list);
        assertNotNull(list);
    }


    @Test
    public void ShoppingListService_updateList() {
        String userId = "2";
        String listId = "3";

        ShoppingListService shoppingListService = new ShoppingListService();
        ShoppingList list = shoppingListService.fetchList(listId, userId);

        assertNotNull(list);
        Item existingItem = list.getItemList().stream().findAny().orElse(null);

        if (existingItem != null) {
            existingItem.setItemName(existingItem.getItemName() + "_EDIT");
        }

        Item itemA = new Item("Apple", "apple-1", "4");

        List<Action> actionList = new ArrayList<>();
        actionList.add(Action.UPDATE);

        List<Action> actionList1 = new ArrayList<>();
        actionList.add(Action.UPDATE);

        Map<Item, List<Action>> actionItems = new HashMap<Item, List<Action>>();
        actionItems.put(existingItem, actionList1);
        actionItems.put(itemA, actionList);

        assertNotNull(shoppingListService.updateList(list, actionItems));
    }


    @Test
    public void multipleUser_updateList(){

        String userId = "2";
        String listId = "3";

        String userId2 = "3";
        Executor executor = Executors.newFixedThreadPool(2);

        ShoppingListService shoppingListService = new ShoppingListService();
        ShoppingList listUser1 = shoppingListService.fetchList(listId, userId);
        ShoppingList listUser2 = shoppingListService.fetchList(listId, userId2);

        Item itemA = new Item("Orange", "orange-1", "4");

        List<Action> actionList = new ArrayList<>();
        actionList.add(Action.CREATE);

        Map<Item, List<Action>> actionItems = new HashMap<Item, List<Action>>();
        actionItems.put(itemA, actionList);

//        assertNotNull(shoppingListService.updateList(listUser1, actionItems, userId));

        executor.execute(new ShoppingListApp(listId, userId, actionItems));

    }


    @Test
    public void ShoppingListService_deleteList() {
        String userId = "3";
        String listId = "1";
        ShoppingListService shoppingListService = new ShoppingListService();
        ShoppingList list = shoppingListService.fetchList(listId, userId);

        assertNotNull(shoppingListService.deleteList(list));
    }
}
