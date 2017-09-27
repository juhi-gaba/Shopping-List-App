package dto;

/**
 * DTO for the Item.
 * @author Juhi Gaba
 */
public class Item implements Comparable<Item> {


    /**
     * Name of the item.
     */
    private String itemName;

    /**
     * Id of the item.
     */
    private String itemId;

    /**
     * Quantity of the item.
     */
    private String quantity;

    /**
     * Constructor for the Item.
     * @param itemName  Name of the item.
     * @param itemId    Id of the item.
     * @param quantity  Quantity of the item.
     */
    public Item(String itemName, String itemId, String quantity) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return A string representing the item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Retrieves the id of the item.
     *
     * @return A string representing the item id.
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Retrieves the quantity of the item.
     *
     * @return A string representing the item quantity.
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Set the name of the item.
     *
     * @param itemName Item name.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Set the quantity for the item.
     *
     * @param quantity Quantity.
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * For testing purpose.
     * @return Used for testing purpose.
     */
    @Override
    public String toString() {
        return "{ itemId : " + this.itemId + " , itemName : " + this.itemName
                + " , quantity : " + this.quantity + "}";

    }

    @Override
    public int compareTo(Item o) {
        return this.getItemId().compareTo(o.getItemId());
    }

}
