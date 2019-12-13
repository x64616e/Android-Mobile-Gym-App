package com.example.jimv2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private Button mBtGoBack;
    //private Button create_profile;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private ImageView mButtonChooseImage;
    private Button mButtonUpload;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private Uri uril;
    private StorageTask mUploadTask;
    private EditText nameEditText;
    private EditText monthEditText;
    private EditText dayEditText;
    private EditText yearEditText;
    private EditText weightEditText;
    private EditText heightEditText;
    private Spinner experienceSpinner;
    private Spinner trainingSpinner;
    private TextView scoreTextView;
    private RadioGroup genderRadioGroup;
    private String userID;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "ProfileFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.profile, container, false);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        //user Picture
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://jym350-de9ff.appspot.com/");
        storageRef.child("uploads/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(mImageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        mButtonChooseImage = view.findViewById(R.id.button_choose_image);
        mButtonUpload = view.findViewById(R.id.button_upload);
        mImageView = view.findViewById(R.id.image_view1);
        mProgressBar = view.findViewById(R.id.progress_bar);
        nameEditText = view.findViewById(R.id.nameEditText);
        monthEditText = view.findViewById(R.id.monthEditText);
        dayEditText = view.findViewById(R.id.dayEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        heightEditText = view.findViewById(R.id.heightEditText);
        weightEditText = view.findViewById(R.id.weightEditText);
        experienceSpinner = view.findViewById(R.id.experienceSpinner);
        trainingSpinner = view.findViewById(R.id.trainingSpinner);
        scoreTextView = view.findViewById(R.id.scoreTextView);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        Spinner spinner = view.findViewById(R.id.experienceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.gymexperience, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = view.findViewById(R.id.trainingSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.trainingtype, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        DatabaseReference profileReference = FirebaseDatabase.getInstance().getReference(userID);
        if (profileReference != null) {
            ValueEventListener profileListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ProfileObject profileObject = dataSnapshot.getValue(ProfileObject.class);
                    nameEditText.setText(profileObject.getName());
                    monthEditText.setText(profileObject.getMonth());
                    dayEditText.setText(profileObject.getDay());
                    yearEditText.setText(profileObject.getYear());
                    weightEditText.setText(profileObject.getWeight());
                    heightEditText.setText(profileObject.getHeight());
                    experienceSpinner.setSelection(profileObject.getExperience());
                    trainingSpinner.setSelection(profileObject.getTraining());
                    scoreTextView.setText(String.valueOf(profileObject.getScore()));
                    genderRadioGroup.check(profileObject.getGender());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    Toast.makeText(getActivity(), "Failed to load post.",
                            Toast.LENGTH_SHORT).show();
                }
            };
            profileReference.addValueEventListener(profileListener);
        }






        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

    return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        ProfileObject newProfile = new ProfileObject(userID, nameEditText.getText().toString(),
                monthEditText.getText().toString(), dayEditText.getText().toString(),
                yearEditText.getText().toString(), weightEditText.getText().toString(),
                heightEditText.getText().toString(), experienceSpinner.getSelectedItemPosition(),
                trainingSpinner.getSelectedItemPosition(), Integer.parseInt(scoreTextView.getText().toString()),
                genderRadioGroup.getCheckedRadioButtonId()
        );
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();
        DatabaseReference dbReference = firebaseDB.getReference().child(userID);
        dbReference.setValue(newProfile);


//        Toast.makeText(getActivity(),
//                "Debug: OnPause() ran",
//                Toast.LENGTH_LONG).show();
    }

    public void backToLanding(){
        getFragmentManager().popBackStack();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


//    private void uploadFile() {
//        if (mImageUri != null) {
//            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
//                    + "." + getFileExtension(mImageUri));
//
//            mUploadTask = fileReference.putFile(mImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgressBar.setProgress(0);
//                                }
//                            }, 500);
//
//                            Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_LONG).show();
//                            Upload upload = new Upload("UserProfile",
//                            taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//                            String uploadId = mDatabaseRef.push().getKey();
//                            mDatabaseRef.child(uploadId).setValue(upload);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            mProgressBar.setProgress((int) progress);
//                        }
//                    });
//        } else {
//            Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
//        }
//    }
private void uploadFile()
{
    if(mImageUri != null)
    {
        final StorageReference fileReference = mStorageRef.child("profile" + "." + getFileExtension(mImageUri));
        mUploadTask = fileReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(0);
                            }
                        },500);
                        Toast.makeText(getActivity(), "Successfully Uploaded.", Toast.LENGTH_SHORT).show();
                        final String description =  nameEditText.getText().toString().trim() + "  -" ;
                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Upload upload = new Upload(description, uri.toString());
                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child("/pic").setValue(upload);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        mProgressBar.setProgress((int)progress);
                    }
                });
    }
    else
    {
        Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
    }
}

}
