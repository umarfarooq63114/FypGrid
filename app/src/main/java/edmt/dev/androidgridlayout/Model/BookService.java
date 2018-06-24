package edmt.dev.androidgridlayout.Model;

public class BookService {
  String brand_name,item_name,fault,dateOfBooking,timeOfBooking;
  int technician_id;

    public BookService( String item_name,String brand_name, String fault,int technician_id,
                       String timeOfBooking , String dateOfBooking) {
        this.brand_name = brand_name;
        this.item_name = item_name;
        this.fault = fault;
        this.dateOfBooking = dateOfBooking;
        this.timeOfBooking = timeOfBooking;
        this.technician_id = technician_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getFault() {
        return fault;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public String getTimeOfBooking() {
        return timeOfBooking;
    }

    public int getTechnician_id() {
        return technician_id;
    }
}
