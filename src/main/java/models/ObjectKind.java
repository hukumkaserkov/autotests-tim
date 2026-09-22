package models;

public class ObjectKind {
    public String id;
    public String name;
    public String type;

    public ObjectKind (String id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
