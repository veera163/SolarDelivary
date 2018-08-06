package com.example.home.solardelivary.activties;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.solardelivary.Api.CollectApi;
import com.example.home.solardelivary.Model.CollectClient;
import com.example.home.solardelivary.Model.UploadImageResult;
import com.example.home.solardelivary.R;
import com.example.home.solardelivary.permission.InternetConnection;
import com.example.home.solardelivary.permission.PermissionsChecker;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by home on 12/21/2017.
 */

public class Syntax extends AppCompatActivity implements View.OnClickListener {

    String id,material="TANK_DELIVERED";
    Button captureBtn = null;
    final int CAMERA_CAPTURE = 1;
    private Uri picUri;
    Button submit;
    String tab="0",trip="T1";
    public GoogleApiClient mGoogleApiClient;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private GridView grid;
    String _path,latlog,add;
    PermissionsChecker checker;
    private List<String> listOfImagesPath;
    TextView veera;
    Context mContext;
    public static  String GridViewDemo_ImagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect);
        veera=(TextView)findViewById(R.id.veera);
        Bundle bundle = getIntent().getExtras();
        id = String.valueOf(bundle.getString("id"));
        veera.setText("School Id = "+id);
        submit=(Button)findViewById(R.id.submit);
        captureBtn = (Button)findViewById(R.id.capture_btn1);
        captureBtn.setOnClickListener(this);
        grid = ( GridView) findViewById(R.id.gridviewimg);
        checker = new PermissionsChecker(this);
        GridViewDemo_ImagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Solars/"+material+"/"+id+"/DELIVEREY/";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            isStoragePermissionGranted();
        }

        else {

            GridViewDemo_ImagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Solars/"+material+"/"+id+"/DELIVEREY/";

            //recreate();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("paths", String.valueOf(listOfImagesPath));

                if(!TextUtils.isEmpty(_path)){
                    if (!TextUtils.isEmpty(String.valueOf(listOfImagesPath))) {

                        if (InternetConnection.checkConnection(Syntax.this)) {
                            /******************Retrofit***************/
                            uploadImage();
                        } else {
                            Toast.makeText(Syntax.this,R.string.string_internet_connection_warning,Toast.LENGTH_LONG).show();
                            //Snackbar.make(parentView, R.string.string_internet_connection_warning, Snackbar.LENGTH_INDEFINITE).show();
                        }
                    }
                }
                else {

                    Toast.makeText(Syntax.this,R.string.string_message_to_attach_file,Toast.LENGTH_LONG).show();
                    //Snackbar.make(parentView, R.string.string_message_to_attach_file, Snackbar.LENGTH_INDEFINITE).show();
                }

    /*  File root = new File(GridViewDemo_ImagePath);
        File[] Files = root.listFiles();
        if(Files != null) {
            int j;
            for(j = 0; j < Files.length; j++) {
                System.out.println(Files[j].getAbsolutePath());
                System.out.println(Files[j].delete());
            }
        }*/

                // grid.setAdapter(null);
            }
        });
        listOfImagesPath = null;
        listOfImagesPath = RetriveCapturedImagePath();
        if(listOfImagesPath!=null){
            grid.setAdapter(new Syntax.ImageListAdapter(this,listOfImagesPath));
        }
    }
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
        else
        {
            Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadImage() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(Syntax.this);
        progressDialog.setMessage(getString(R.string.string_title_upload_progressbar_));
        progressDialog.show();
        File file = new File(_path);

        //Create Upload Server Client
        CollectApi service = CollectClient.getApiService();

        //File creating from selected URL

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //Log.e("request", String.valueOf(requestFile));
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[listOfImagesPath.size()];

        for (int index = 0; index < listOfImagesPath.size(); index++) {
            file = new File(listOfImagesPath.get(index));
            RequestBody surveyBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            surveyImagesParts[index] = MultipartBody.Part.createFormData("SurveyImage", file.getName(), surveyBody);
        }

        Call<UploadImageResult> resultCall = service.uploadImage(surveyImagesParts,id,material,tab,trip);
//        Call<Result> resultCall = service.test();
        // finally, execute the request
        resultCall.enqueue(new Callback<UploadImageResult>() {
            @Override
            public void onResponse(Call<UploadImageResult> call, Response<UploadImageResult> response) {
                progressDialog.dismiss();
                _path = "";
                if(response.isSuccessful()){

                   /* File root = new File(GridViewDemo_ImagePath);
                        File[] Files = root.listFiles();
                        if(Files != null) {
                            int j;
                            for(j = 0; j < Files.length; j++) {
                                System.out.println(Files[j].getAbsolutePath());
                                System.out.println(Files[j].delete());
                            }
                        }
                   */
                    grid.setAdapter(null);
                    Toast.makeText(Syntax.this,"Successfully Uploaded",Toast.LENGTH_LONG).show();
                    //  Intent i= new Intent(Collect.this, MapsActivity.class);
                    // startActivity(i);
                }
                else {


                }
            }

            @Override
            public void onFailure(Call<UploadImageResult> call, Throwable t) {

                Toast.makeText(Syntax.this,t.getMessage(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }
    @Override
    public void onClick(View arg0) {
// TODO Auto-generated method stub
        if (arg0.getId() == R.id.capture_btn1) {

            try {
//use standard intent to capture an image
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//we will handle the returned data in onActivityResult
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            } catch(ActivityNotFoundException anfe){
//display an error message
                String errorMessage = "Whoops - your device doesn't support capturing images!";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
//user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE){
                Bundle extras = data.getExtras();
                Bitmap thePic = extras.getParcelable("data");
                String imgcurTime = dateFormat.format(new Date());
                File imageDirectory = new File(GridViewDemo_ImagePath);
                imageDirectory.mkdirs();
                _path = GridViewDemo_ImagePath + imgcurTime+".jpg";
                Log.e("path", String.valueOf(_path));
                try {
                    FileOutputStream out = new FileOutputStream(_path);
                    thePic.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listOfImagesPath = null;
                listOfImagesPath = RetriveCapturedImagePath();
                Log.e("values", String.valueOf(listOfImagesPath));

                if(listOfImagesPath!=null){

                    grid.setAdapter(new Syntax.ImageListAdapter(this,listOfImagesPath));
                }
            }
        }
    }

    private List<String> RetriveCapturedImagePath() {
        List<String> tFileList = new ArrayList<String>();
        File f = new File(GridViewDemo_ImagePath);
        if (f.exists()) {
            File[] files=f.listFiles();
            Arrays.sort(files);

            for(int i=0; i<files.length; i++){
                File file = files[i];
                if(file.isDirectory())
                    continue;
                tFileList.add(file.getPath());
            }
        }
        return tFileList;
    }

    public class ImageListAdapter extends BaseAdapter
    {
        private Context context;
        private List<String> imgPic;
        public ImageListAdapter(Context c, List<String> thePic)
        {
            context = c;
            imgPic = thePic;
        }
        public int getCount() {
            if(imgPic != null)
                return imgPic.size();
            else
                return 0;
        }

        //---returns the ID of an item---
        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView;
            BitmapFactory.Options bfOptions=new BitmapFactory.Options();
            bfOptions.inDither=false;                     //Disable Dithering mode
            bfOptions.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
            bfOptions.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
            bfOptions.inTempStorage=new byte[32 * 1024];
            if (convertView == null) {
                imageView = new ImageView(context);
                GridView.LayoutParams lp= new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                // view.setLayoutParams(/* your layout params */); //where view is cell view

                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setPadding(10, 10, 10, 10);
                // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }
            FileInputStream fs = null;
            Bitmap bm;
            try {
                fs = new FileInputStream(new File(imgPic.get(position).toString()));

                if(fs!=null) {
                    bm=BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
                    imageView.setImageBitmap(bm);
                    imageView.setId(position);
                    imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                if(fs!=null) {
                    try {
                        fs.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return imageView;
        }
    }

}


