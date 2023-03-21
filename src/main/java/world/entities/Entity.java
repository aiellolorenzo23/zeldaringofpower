package world.entities;

import enums.EntityType;
import world.globals.Globals;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Entity {

    protected EntityType type;
    protected String name;
    protected String description;
    private Entity parent;
    private List<Entity> contains = new ArrayList<>();

    private static Map<EntityType, String> entitiesMap;

    public Entity(EntityType type, String name, String description){
        this.type = type;
        this.name = name;
        this.description = description;
        this.parent = null;
    }

    public Entity() {
        if (parent != null)
            this.parent = null;
    }

    //boolean operator == (const Entity& e) const { return type == e.type && name == e.name; }
    //boolean operator != (const Entity& e) const { return !operator==(e); }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EntityType getType() {
        return type;
    }

    public Entity getParent() {
        return parent;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public List<Entity> getContains() {
        return contains;
    }

    public void Look() {
        System.out.println(description);
    }

    public void Insert(Entity entity) {
        contains.add(entity);
    }

    public void Remove(Entity entity) {
        contains.remove(entity);
    }

    public int Show(List<Entity> entities, EntityType type) {
        int count = 0;

        // Search for items
        for (Entity e : entities) {
            if (type.equals(e.getType())) {
                String itemName = e.getName().toLowerCase();
                System.out.println("- "+itemName);
                count++;
            }
        }

        return count;
    }
}
