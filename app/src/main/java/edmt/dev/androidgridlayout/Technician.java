package edmt.dev.androidgridlayout;

public class Technician {

        private int image;
        private String status,category;
    private String name;
    private String phone;
    private float rating;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }






        public Technician(int image, String name, String phone,String status ,String category,float rating) {
            this.image = image;
            this.name = name;
            this.category=category;
            this.status=status;
            this.phone = phone;
            this.rating=rating;
        }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }




