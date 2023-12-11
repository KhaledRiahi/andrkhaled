import com.google.firebase.firestore.FieldValue;

public class Attendance {
    private String date;
    private String matiere;
    private String status;
    private String userid;

    // Required empty constructor for Firestore
    public Attendance() {}

    // Constructor for creating instances
    public Attendance(String date, String matiére, String status, String userid) {
        this.date = date;
        this.matiere = matiere;
        this.status = status;
        this.userid = userid;
    }

    // Getter methods
    public String getDate() {
        return date;
    }

    public String getMatiére() {
        return matiere;
    }

    public String getStatus() {
        return status;
    }

    public String getUserid() {
        return userid;
    }





}
