package dto;

import java.util.List;

/**
 * @author Juhi Gaba
 */
public class User {

    /**
     * User id.
     */
    private String userId;

    /**
     * User Name.
     */
    private String userName;

    /**
     * List of shopping list.
     */
    private List<ShoppingList> shoppingLists;

    /**
     * Constructor for the user.
     * @param userId        User id.
     * @param userName      User Name.
     * @param shoppingLists List of shopping list.
     */
    public User(String userId, String userName, List<ShoppingList> shoppingLists) {
        this.userId = userId;
        this.userName = userName;
        this.shoppingLists = shoppingLists;
    }

    /**
     * Retrieves the user id.
     *
     * @return A string representing the user id.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Retrieves the user name.
     *
     * @return A string representing the user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieves the list of shopping list.
     *
     * @return List of shopping list.
     */
    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    @Override
    public String toString() {
        return "{ userId : " + this.userId + " , userName : " + this.userName
                + " , shoppingList : " + this.shoppingLists + "}";
    }
}
