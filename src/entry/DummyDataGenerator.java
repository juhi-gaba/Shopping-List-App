package entry;

import dto.Item;
import dto.ShoppingList;
import dto.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Juhi Gaba
 */
public class DummyDataGenerator {

    /**
     * Random id of list.
     */
    private static Random listIdRandom = new Random();

    /**
     * Random id of item.
     */
    private static Random ItemIdRandom = new Random();

    /**
     * List upper limit
     */
    private int list_upperLimit = 5;

    /**
     * Item upper limit
     */
    private int item_upperLimit = 5;

    /**
     * Constructor for Dummy Data Generator
     *
     * @param list_rangeUpperLimit List upper limit
     * @param item_rangeUpperLimit Item upper limit
     */
    public DummyDataGenerator(int list_rangeUpperLimit,
                              int item_rangeUpperLimit) {
        this.list_upperLimit = list_rangeUpperLimit;
        this.item_upperLimit = item_rangeUpperLimit;
    }

    /**
     * Create a dummy shopping list.
     * @param noOfItem Number of item in a shopping list.
     *
     * @return A dummy shopping list.
     */
    public ShoppingList getDummyList(final int noOfItem) {
        int listId = listIdRandom.nextInt(list_upperLimit) + 1;
        String listName = this.getListName(listId);
        Date createdDate = new Date();
        List<Item> itemList = getDummyItems(noOfItem);

        ShoppingList list = new ShoppingList(String.valueOf(listId), listName,
                createdDate, itemList);
        System.out.println(list);
        return list;
    }

    /**
     * Create dummy list user.
     *
     * @param noOfItem Number of item in a shopping list.
     * @param noOfUser Number of user.
     * @return List of users.
     */
    public List<User> getDummyUserList(final int noOfItem, int noOfUser) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < noOfUser; i++) {

            List<ShoppingList> list = new ArrayList<ShoppingList>();

            for (int j = 0; j < 2; j++) {
                int listId = new Random().nextInt(list_upperLimit) + 1;
                String listName = this.getListName(listId);
                Date createdDate = new Date();
                List<Item> itemList = getDummyItems(noOfItem);
                ShoppingList shoppingList = new ShoppingList(
                        String.valueOf(listId), listName, createdDate, itemList);
                System.out.println(shoppingList);
                list.add(shoppingList);

            }

            User user = new User(String.valueOf(i + 1), "Usr_"
                    + String.valueOf(i + 1), list);
            users.add(user);
        }
        return users;
    }

    /**
     * Create dummy Items.
     *
     * @param noOfItem Number of items.
     * @return List of items.
     */
    private List<Item> getDummyItems(int noOfItem) {
        List<Item> items = IntStream.range(0, noOfItem)
                .mapToObj(i -> getItem()).collect(Collectors.toList());
        return items;
    }

    /**
     * Retrieves the item
     *
     * @return An Item object.
     */
    private Item getItem() {
        int itemId = new Random().nextInt(item_upperLimit) + 1;

        return new Item(this.getItemName(itemId), String.valueOf(itemId), "2");
    }

    /**
     * Retrieves the item name.
     *
     * @param itemId Item id.
     * @return A string representing item name.
     */
    private String getItemName(int itemId) {
        return "I_" + itemId;
    }

    /**
     * Retrieves the list name.
     *
     * @param listId list id.
     * @return A string representing list name.
     */
    private String getListName(int listId) {
        return "S_L_" + listId;
    }

}
