package edmt.dev.androidgridlayout;
import com.google.gson.annotations.SerializedName;
public class Technician {
    @SerializedName("name")
String name;
        @SerializedName("cnic")
        String  cnic;
        @SerializedName("email")
        String email;
        @SerializedName("address")
        String address;
        @SerializedName("status")
        String status;
        @SerializedName("phone")
        String phone;
        @SerializedName("experience")
        String experience;
        @SerializedName("rating")
        double rating;

        @SerializedName("specaility_id")
        int specaility_id;


        public Technician(String name, String cnic, String address, String email, String status, String phone, String experience, double rating, int specaility_id) {
            this.name = name;
            this.cnic = cnic;
            this.address = address;
            this.email = email;
            this.status = status;
            this.phone = phone;
            this.experience = experience;
            this.rating = rating;

            this.specaility_id = specaility_id;
        }

        public String getName() {
            return name;
        }

        public String getCnic() {
            return cnic;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public String getStatus() {
            return status;
        }

        public String getPhone() {
            return phone;
        }

        public String getExperience() {
            return experience;
        }

        public double getRating() {
            return rating;
        }



        public int getSpeciality() {
            return specaility_id;
        }
}
