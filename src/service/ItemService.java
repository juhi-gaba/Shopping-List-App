package service;

import dto.Item;

/**
 * Item service
 * @author Juhi Gaba
 */
public class ItemService {

    /**
     * Update the item in the existing list.
     * @param existingItem Item in the list that has to be updated.
     * @param currentItem  Current item.
     */
    public void updateItem(Item existingItem, Item currentItem) {
        existingItem.setItemName(currentItem.getItemName());
        existingItem.setQuantity(currentItem.getQuantity());
    }

}
