package com.example.arpit.pocket;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    ImageButton ib1;
    ImageButton ib2;
    EditText et_purchase, et_return, et_name, et_description;

    private String spinnerItem;

    Calendar c;
    DatePickerDialog dpd;

    ImageView imgPicture;
    private Uri imageUri = null;
    String ext = "jpg";
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int CAMERA_REQUEST_CODE = 228;
    //keep track of cropping intent
    final int PIC_CROP = 2;

    private Spinner mSpinnerCategories;

    private StorageTask mUploadTask;

    ArrayList<String> item = new ArrayList<String>();
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

         ib1 = (ImageButton) findViewById(R.id.imageButton);
         ib2 = (ImageButton) findViewById(R.id.imageButton2);
         et_purchase = (EditText) findViewById(R.id.purchase);
         et_return = (EditText) findViewById(R.id.returnDate);
         et_name = (EditText) findViewById(R.id.etname);
         et_description = (EditText) findViewById(R.id.et_description);

        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        mSpinnerCategories = (Spinner) findViewById(R.id.spinner);

        spinner_categoriesAdapter spinnerAdapter = new spinner_categoriesAdapter(this, android.R.layout.simple_spinner_item, item);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        item.add("Groceries");
        item.add("Electronics");
        item.add("Furniture");
        item.add("Clothes");
        item.add("Reservations");
        item.add("Other");

        mSpinnerCategories.setAdapter(spinnerAdapter);

        mSpinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItem = adapterView.getItemAtPosition(i).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(getIntent().hasExtra("name")){
            et_name.setText(getIntent().getStringExtra("name"));
        }
        if(getIntent().hasExtra("url")){
            url = (getIntent().getStringExtra("url"));
            Picasso.with(this).load(url).into(imgPicture);
            imgPicture.setRotation(90);
        }
        if(getIntent().hasExtra("purchase")){
            et_purchase.setText(getIntent().getStringExtra("purchase"));
        }
        if(getIntent().hasExtra("return")){
            et_return.setText(getIntent().getStringExtra("return"));
        }
//        if(getIntent().hasExtra("spinneritem")){
//
//        }
        if(getIntent().hasExtra("descript")){
            et_description.setText(getIntent().getStringExtra("descript"));
        }
    }

//    ***********DATE MENU*********************
    public void dateMenu1(View view){
        c = Calendar.getInstance();
                 int day = c.get(Calendar.DAY_OF_MONTH);
                 int month = c.get(Calendar.MONTH);
                 int year = c.get(Calendar.YEAR);

        dpd = new DatePickerDialog(DetailsActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    final Calendar myCalendar = Calendar.getInstance();
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        et_purchase.setText(sdf.format(myCalendar.getTime()));

                    }
                }, year, month, day);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        dpd.show();
    }

    public void dateMenu2(View view){
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

//        dpd = new DatePickerDialog(DetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
//                et_return.setText(mDay+"/"+mMonth+"/"+mYear);
//            }
//        },day, month, year);
//        dpd.show();
        dpd = new DatePickerDialog(DetailsActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    final Calendar myCalendar = Calendar.getInstance();
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                           et_return.setText(sdf.format(myCalendar.getTime()));

                    }
                }, year, month, day);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        dpd.show();
    }

    //    ***********CAMERA CLICKED*********************
    public void onTakePhotoClicked(View v) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()));
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String cameraFilePath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }

    //    ***********GALLERY SELECTED*********************
    public void onImageGalleryClicked(View v) {
        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        imageUri = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(imageUri, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    public void save(View v) {
        if(imageUri == null && url == null){
            Toast.makeText(DetailsActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }else if(mUploadTask != null && mUploadTask.isInProgress()){
            Toast.makeText(DetailsActivity.this, "In Progress", Toast.LENGTH_SHORT).show();
        }else{
            uploadFile();
        }
    }

    //    ***********UPLOAD OBJECT TO FIREBASE DATABASE*********************
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(getFileExtension(imageUri)!=null){
            ext = getFileExtension(imageUri);
        }
        if(imageUri != null && et_name.getText().toString().length()>0 && et_purchase.getText().toString().length() > 0){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+ext);

            mUploadTask = fileReference.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(DetailsActivity.this, "upload successful", Toast.LENGTH_LONG).show();

                            Upload upload = new Upload(
                                    et_name.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString(),
                                    et_purchase.getText().toString().trim(),
                                    et_return.getText().toString().trim(),
                                    spinnerItem,
                                    et_description.getText().toString().trim()
                            );

                            String UploadID = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(UploadID).setValue(upload);
                            et_name.setText("");
                            imgPicture.setVisibility(View.GONE);
                            et_purchase.setText("");
                            et_return.setText("");
                            et_description.setText("");


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                }
            });
        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    //    *********** AFTER THE IMAGE IS SELECTED ********************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                imgPicture.setImageURI(Uri.parse(cameraFilePath));
                //Uri imageUri = data.getData();

                imageUri = (Uri.parse(cameraFilePath));

                imgPicture.setImageURI(Uri.parse(cameraFilePath));
                performCrop();

//                imgPicture.setPivotX(imgPicture.getWidth()/2);
//                imgPicture.setPivotY(imgPicture.getHeight()/2);
//                imgPicture.setRotation(90);
//                Picasso.with(this).load(imageUri).into(imgPicture);
            }
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                imageUri = data.getData();
                 Picasso.with(this).load(imageUri).into(imgPicture);
                performCrop();
//                imgPicture.setPivotX(imgPicture.getWidth()/2);
//                imgPicture.setPivotY(imgPicture.getHeight()/2);
//                imgPicture.setRotation(90);
                // declare a stream to read the image data from the SD Card.
//                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                //                    inputStream = getContentResolver().openInputStream(imageUri);

                // get a bitmap from the stream.
//                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                // show the image to the user

//                    imgPicture.setImageBitmap(image);

            }else if(requestCode == PIC_CROP){
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");

                //display the returned cropped image
                imgPicture.setImageBitmap(thePic);
            }

        }
    }

    //    ***********CROP IMAGE FUNCTION*********************
    private void performCrop(){
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(imageUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
//            //indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            //indicate output X and Y
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 612);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}





