package com.example.projet;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EnseignantActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView listView;
    List<String> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseignant);

        listView = findViewById(R.id.listView); // Assuming you have a ListView in your layout
        studentList = new ArrayList<>();

        // Query Firestore for all student attendances
        db.collection("attendances")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Iterate through the documents and retrieve student data
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String studentInfo = document.getString("userid")
                                        + " - Date: " + document.getString("date")
                                        + " - Matiere: " + document.getString("matiere")
                                        + " - Status: " + document.getString("status");
                                studentList.add(studentInfo);
                            }

                            // Display the student list in the ListView
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(EnseignantActivity.this,
                                    android.R.layout.simple_list_item_1, studentList);
                            listView.setAdapter(adapter);
                        } else {
                            // Handle errors
                        }
                    }
                });
    }

    // Add methods to update student status as needed
}
