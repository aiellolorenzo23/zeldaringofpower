package world.entities;

import enums.EntityType;
import enums.ItemType;

import static enums.ItemType.COMMON;

public class Item extends Entity{
    private ItemType itemType;

    public Item(String name, String description, ItemType type) {
        this.itemType = type;
        this.name = name;
        this.description = description;
        this.type = EntityType.ITEM;
    }

    public Item(String name, String description) {
        this.itemType = COMMON;
        this.name = name;
        this.description = description;
        this.type = EntityType.ITEM;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
