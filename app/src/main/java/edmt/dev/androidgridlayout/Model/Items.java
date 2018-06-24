package edmt.dev.androidgridlayout.Model;

public class Items {
    String item_name;
    int category_id;

    public Items(String item_name, int category_id) {
        this.item_name = item_name;
        this.category_id = category_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public int getCategory_id() {
        return category_id;
    }
}
