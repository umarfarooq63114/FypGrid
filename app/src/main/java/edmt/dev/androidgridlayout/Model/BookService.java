package edmt.dev.androidgridlayout.Model;

public class BookService {
  String brand_name,item_name,fault,full_name,mobile,location,image,dateOfBooking,timeOfBooking;
  int technician_id;


    public BookService(String brand_name, String item_name, String fault, String full_name, String mobile, String location,
                       String image, String dateOfBooking, String timeOfBooking, int technician_id) {
        this.brand_name = brand_name;
        this.item_name = item_name;
        this.fault = fault;
        this.full_name = full_name;
        this.mobile = mobile;
        this.location = location;
        this.image = image;
        this.dateOfBooking = dateOfBooking;
        this.timeOfBooking = timeOfBooking;
        this.technician_id = technician_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(String timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    public int getTechnician_id() {
        return technician_id;
    }

    public void setTechnician_id(int technician_id) {
        this.technician_id = technician_id;
    }
}
