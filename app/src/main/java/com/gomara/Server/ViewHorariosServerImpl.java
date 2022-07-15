package com.gomara.Server;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.ViewHorariosPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ViewHorariosServerImpl implements  ViewHorariosServer{

    private ViewHorariosPresenter viewHorariosPresenter;

    public ViewHorariosServerImpl(ViewHorariosPresenter viewHorariosPresenter){
        this.viewHorariosPresenter = viewHorariosPresenter;
    }

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public void getAnioCurso(String uid) {
        db.collection("User").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();

                    viewHorariosPresenter.showAnioCurso(anio,curso);
                }

            }
        });

    }

    @Override
    public void getSizeAllMaterias(String anio, String curso) {
        db.collection("Cursos/" + anio + curso + "/Materias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                            viewHorariosPresenter.showSizeAllMaterias(task.getResult().size());
                        else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }

    @Override
    public void getIsPreseptor(String uid) {
        db.collection("User").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    boolean isPreseptor = false;
                    if(task.getResult().contains("preseptor"))
                        isPreseptor = task.getResult().getBoolean("preseptor");

                    viewHorariosPresenter.showIsPreseptor(isPreseptor);
                }
            }
        });
    }

    @Override
    public void getImage(String path) {
        StorageReference ref = storage.getReference().child(path);
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                viewHorariosPresenter.showImage(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                viewHorariosPresenter.onFailure(e);
            }
        });
    }

    @Override
    public void updateImage(String anio,String curso,Uri uri) {
        StorageReference ref = storage.getReference().child("Horarios/horario_" + anio + curso + ".png");
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                viewHorariosPresenter.onSuccess("Se Actualizo correctamente");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                viewHorariosPresenter.onFailure(e);
            }
        });
    }
}
