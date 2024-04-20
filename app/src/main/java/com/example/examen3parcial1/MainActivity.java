package com.example.examen3parcial1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final  int REQUEST_IMAGE = 101;
    static final  int PETICION_ACCESS_CAM = 201;
    String currentPhotoPath;
    private Uri image_url;
EditText txtdescripcion,txtperiodista,txtfecha;
ImageView image;
Button guardar,lista,tomarfoto;
ImageButton grabar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtdescripcion = (EditText) findViewById(R.id.descripcion);
        txtperiodista = (EditText) findViewById(R.id.periodista2);
        txtfecha = (EditText) findViewById(R.id.fecha);
        image = (ImageView) findViewById(R.id.imageView);
        tomarfoto = (Button) findViewById(R.id.tomarfoto);
        guardar = (Button) findViewById(R.id.guardar);
        lista = (Button) findViewById(R.id.lista);
        grabar = (ImageButton) findViewById(R.id.grabar);

        tomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permisos();
            }
        });
    }

    private void permisos(){
        // METODO PARA LOS PERMISOS DE LA APLICACION
        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},PETICION_ACCESS_CAM);
        }
        else
        {
            dispatchTakePictureIntent();
            //TomarFoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==  PETICION_ACCESS_CAM){
            if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }{
                Toast.makeText(getApplicationContext(),"Se necesita permiso de la camara",Toast.LENGTH_LONG);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            try {
                File foto = new File(currentPhotoPath);
                image_url = Uri.fromFile(foto);
                //subirPhoto(image_url); // Si necesitas subir la foto, hazlo aquí
                image.setImageURI(image_url);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.toString();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.examen3parcial1",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE);
            }
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use  with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
}
        }