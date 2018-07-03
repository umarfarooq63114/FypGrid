package edmt.dev.androidgridlayout.Model;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("item_name")
    String item_name;
    @SerializedName("category_id")
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
