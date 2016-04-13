package com.proyecto1.moviles.proyecto;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Model.Classes.GetFiles;
import Model.Classes.ResultFile;
import Model.Services.GetSharedFilesService;
import Model.Services.PostUploadFileService;

public class Files_View extends ListActivity {
    GetFiles files = null;
    private int PICK_IMAGE_REQUEST = 1;
    //final String strPref_Download_ID = "PREF_DOWNLOAD_ID";
    private DownloadManager downloadManager;
    //SharedPreferences preferenceManager;
    private long downloadReference;
    private Integer idUser;
    private final String User = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files__view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent myIntent = getIntent(); // gets the previously created intent
        idUser = myIntent.getIntExtra("idUser", 0); // will return "FirstKeyValue"
        try {
            files = new GetSharedFilesService().execute(idUser.toString(), User).get();
            ArrayList<String> consulta = new ArrayList<String>();
            if (!files.getData().isEmpty()) {
                for (ResultFile f : files.getData()) {
                    consulta.add("Archivo: " + f.getName() + " " + f.getDate());
                }
                ArrayAdapter<String> dataArray = new ArrayAdapter<String>(this, R.layout.msg_view, consulta);
                this.setListAdapter(dataArray);
            } else {
                Toast.makeText(Files_View.this, "Esta persona no ha compartido archivos con usted.", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //Click Listview
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //get selected items
        ResultFile file = files.getData().get(position);
        download(file.getId(), file.getName());

        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, "Descargando: " + file.getName(), Toast.LENGTH_SHORT).show();
    }

    //Activty to get the image from the implicit intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                File finalFile = new File(getRealPathFromURI(tempUri));
                System.out.println(finalFile.getAbsolutePath());
                new PostUploadFileService().execute(User, idUser.toString(), finalFile.getAbsolutePath()).get();
                // Log.d(TAG, String.valueOf(bitmap));
                /*ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Implicit intent to select a picture from the gallery
    public void uploadImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    //Android download manager
    /*public void download(Integer id, String name) throws FileNotFoundException {
        preferenceManager
                = PreferenceManager.getDefaultSharedPreferences(this);
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri Download_Uri = Uri.parse("http://192.168.0.13:8191/rest/files/"+ id.toString() );
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(false);
        //Set the title of this download, to be displayed in notifications (if enabled).
        request.setTitle(name);
        //Set a description of this download, to be displayed in notifications (if enabled)
        request.setDescription("Android Data download using DownloadManager.");
        //Set the local destination for the downloaded file to a path within the application's external files directory
        if(1 == DownloadManager.STATUS_SUCCESSFUL) {
            //Retrieve the saved request id
            long downloadID = preferenceManager.getLong(strPref_Download_ID, 0);
            ParcelFileDescriptor file;
            file = downloadManager.openDownloadedFile(downloadID);
            FileInputStream fileInputStream = new ParcelFileDescriptor.AutoCloseInputStream(file);
            Bitmap bm = BitmapFactory.decodeStream(fileInputStream);

            MediaStore.Images.Media.insertImage(getContentResolver(), bm, name, null);

            //request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_PICTURES, name);

            //Enqueue a new download and same the referenceId

        }
        downloadReference = downloadManager.enqueue(request);
    }*/

    public void download(int id, String name) {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri Download_Uri = Uri.parse("http://192.168.0.13:8191/rest/files/"+id);
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(false);
        //Set the title of this download, to be displayed in notifications (if enabled).
        request.setTitle(name);
        //Set a description of this download, to be displayed in notifications (if enabled)
        request.setDescription("Android Data download using DownloadManager.");
        //Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);

        //Enqueue a new download and same the referenceId
        downloadReference = downloadManager.enqueue(request);
    }

    //Functions to get the path of the selected image
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
        //return  getImageUri(getApplicationContext(), inImage);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}
