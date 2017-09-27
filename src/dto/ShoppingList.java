package dto;

import java.util.Date;
import java.util.List;

/**
 * @author Juhi Gaba
 */
public class ShoppingList {


    /**
     * Id of the List.
     */
    private String listId;

    /**
     * List name.
     */
    private String listName;

    /**
     * Last updated date.
     */
    private Date lastUpdated;

    /**
     * Date on which list got created.
     */
    private Date created;

    /**
     * List of items.
     */
    private List<Item> itemList;

    /**
     * Constructor for the Shopping list.
     * @param listId    Id of the List.
     * @param listName  List name.
     * @param created   Date on which list got created.
     * @param itemList  List of items.
     */
    public ShoppingList(String listId, String listName, Date created,
                        List<Item> itemList) {
        this.listId = listId;
        this.listName = listName;
        this.created = created;
        this.itemList = itemList;
    }

    /**
     * Retrieves the list id.
     *
     * @return A string representing the list id.
     */
    public String getListId() {
        return listId;
    }

    /**
     * Retrieves the list name.
     *
     * @return A string representing the list name.
     */
    public String getListName() {
        return listName;
    }

    /**
     *  Retrieves the list last updated date.
     *
     * @return Date on which list was last updated.
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Retrieves the list items.
     *
     * @return A list containg the items.
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Set the name of the list
     *
     * @param listName List name.
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * Sets the list last updated date.
     *
     * @param lastUpdated last updated date.
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Retrieves the list created date.
     *
     * @return Created date.
     */
    public Date getCreated() {
        return created;
    }


    @Override
    public String toString() {
        return "{ listId : " + this.listId + " , listName : " + this.listName
                + " , created Date : " + this.created + " , updated Date : "
                + this.lastUpdated + " , itemList : " + this.itemList + "}";
    }
}
