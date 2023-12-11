package com.example.projet;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class EtudiantActivity<YourCustomAdapter> extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        setContentView(R.layout.activity_attendance_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create an instance of your custom adapter and set it to the RecyclerView


        Log.d(TAG  ,      "1111111111111111111");
        if (currentUser != null) {
            String userId = currentUser.getUid();
            Log.d(TAG  ,      "222222222222");
            db.collection("attendances")
                    .whereEqualTo("userid", userId)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        // Iterate through the documents and retrieve data
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String date = document.getString("date");
                            String matiere = document.getString("matiere");
                            String status = document.getString("status");

                            // Handle the data (e.g., display it in a RecyclerView or ListView)
                            Log.d(TAG, "Date: " + date + ", Matiere: " + matiere + ", Status: " + status);
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Handle errors
                        Log.e(TAG, "Error fetching attendance data", e);
                    });
        }
    }
}
