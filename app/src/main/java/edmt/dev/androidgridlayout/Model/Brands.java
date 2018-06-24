package edmt.dev.androidgridlayout.Model;

public class Brands {
    String brand_name;
    int item_id;

    public Brands(String brand_name, int item_id) {
        this.brand_name = brand_name;
        this.item_id = item_id;
    }


    public String getBrand_name() {
        return brand_name;
    }

    public int getItem_id() {
        return item_id;
    }
}
