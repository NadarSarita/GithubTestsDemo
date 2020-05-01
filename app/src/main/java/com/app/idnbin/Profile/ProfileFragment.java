package com.app.idnbin.Profile;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.LoginRegister.LoginActivity;
import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.Profile.Deposit.DepositActivity;
import com.app.idnbin.Profile.Settings.NotificationSettings.NotificationSettingActivity;
import com.app.idnbin.Profile.Settings.Cards.PaymentActivity;
import com.app.idnbin.Profile.Settings.TraderoomSettingActivity;
import com.app.idnbin.Profile.History.Operation.OperationsHistoryActivity;
import com.app.idnbin.Profile.Support.SupportActivity;
import com.app.idnbin.Profile.Withdraw.WithdrawActivity;
import com.app.idnbin.R;
import com.app.idnbin.Profile.MyProfile.UserProfileActivity;
import com.app.idnbin.Profile.Settings.Security.SecurityActivity;
import com.app.idnbin.Profile.History.Trading.TradingHistoryActivity;
import com.app.idnbin.WebView.ReadMeActivity;
import com.app.idnbin.util.BaseFragment;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    private CircleImageView IvUserProfile;
    private DatabaseReference reference;
    private FirebaseUser fuser;
    private StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;
    private TextView TvOperations,TvTradingHistory,TvSettingTrade,TvSecurity,TvCards,TvNotification,TvUserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        CardView cvSetting = v.findViewById(R.id.CvSetting);
        TextView TvUserName = v.findViewById(R.id.TvUserName);
        CardView cvHelp = v.findViewById(R.id.CvHelp);
        CardView cvBalance = v.findViewById(R.id.CvBalance);
        CardView cvDeposit = v.findViewById(R.id.CvDeposit);
        CardView cvWithdraw = v.findViewById(R.id.CvWithdraw);
        CardView cvHistory = v.findViewById(R.id.CvHistory);
        CardView cvPrivacySetting = v.findViewById(R.id.CvPrivacySetting);
        CardView cvPayment = v.findViewById(R.id.CvPayment);
        CardView cvNotificationSetting = v.findViewById(R.id.CvNotificationSetting);
        CardView cvRateus = v.findViewById(R.id.CvRateus);
        CardView CVLogout = v.findViewById(R.id.CVLogout);
        IvUserProfile = v.findViewById(R.id.IvUserProfile);
        RelativeLayout RLTermsConditions = v.findViewById(R.id.RLTermsConditions);
        TvUserName = v.findViewById(R.id.TvUserName);


        TvUserName.setText(getPref(getContext(), "username"));

        TvOperations = v.findViewById(R.id.TvOperations);
        TvTradingHistory = v.findViewById(R.id.TvTradingHistory);
        TvSettingTrade = v.findViewById(R.id.TvSettingTrade);
        TvSecurity = v.findViewById(R.id.TvSecurity);
        TvCards = v.findViewById(R.id.TvCards);
        TvNotification = v.findViewById(R.id.TvNotification);


        cvSetting.setOnClickListener(this);
        TvUserName.setOnClickListener(this);
        cvHelp.setOnClickListener(this);
        cvBalance.setOnClickListener(this);
        cvDeposit.setOnClickListener(this);
        cvWithdraw.setOnClickListener(this);
        cvHistory.setOnClickListener(this);
        cvPrivacySetting.setOnClickListener(this);
        cvPayment.setOnClickListener(this);
        cvNotificationSetting.setOnClickListener(this);
        cvRateus.setOnClickListener(this);
        RLTermsConditions.setOnClickListener(this);
        CVLogout.setOnClickListener(this);
        IvUserProfile.setOnClickListener(this);

        TvOperations.setOnClickListener(this);
        TvTradingHistory.setOnClickListener(this);
        TvSettingTrade.setOnClickListener(this);
        TvSecurity.setOnClickListener(this);
        TvCards.setOnClickListener(this);
        TvNotification.setOnClickListener(this);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("UserDetails").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getActivity() == null) {
                    return;
                }
                UserDetails user = dataSnapshot.getValue(UserDetails.class);

                if (user !=null){
                    if (user.getImageURL() != null && !user.getImageURL().equalsIgnoreCase(""))
                        Glide.with(getActivity()).load(user.getImageURL()).into(IvUserProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.TvUserName:
                startActivity(new Intent(getContext(), UserProfileActivity.class));
                break;
            case R.id.CvHelp:
                startActivity(new Intent(getContext(), SupportActivity.class));
                break;
            case R.id.CvSetting:
                if (TvSettingTrade.getVisibility() == View.VISIBLE){
                    TvSettingTrade.setVisibility(View.GONE);
                    TvSecurity.setVisibility(View.GONE);
                    TvCards.setVisibility(View.GONE);
                    TvNotification.setVisibility(View.GONE);
                }else {
                    TvSettingTrade.setVisibility(View.VISIBLE);
                    TvSecurity.setVisibility(View.VISIBLE);
                    TvCards.setVisibility(View.VISIBLE);
                    TvNotification.setVisibility(View.VISIBLE);
                }
                break;
           /* case R.id.CvBalance:
                startActivity(new Intent(getContext(), BalanceActivity.class));
                break;*/
            case R.id.CvDeposit:
                startActivity(new Intent(getContext(), DepositActivity.class));
                break;
            case R.id.CvWithdraw:
                startActivity(new Intent(getContext(), WithdrawActivity.class));
                break;
            case R.id.CvHistory:
                if (TvTradingHistory.getVisibility() == View.VISIBLE){
                    TvOperations.setVisibility(View.GONE);
                    TvTradingHistory.setVisibility(View.GONE);
                }else {
                    TvOperations.setVisibility(View.VISIBLE);
                    TvTradingHistory.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.CvPrivacySetting:

                break;
            case R.id.CvPayment:
                //startActivity(new Intent(getContext(), PaymentActivity.class));
                break;
            case R.id.CvNotificationSetting:
                //startActivity(new Intent(getContext(), NotificationSettingActivity.class));
                break;
            case R.id.CvRateus:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=dts.app")));
                break;
            case R.id.RLTermsConditions:
                intent = new Intent(getContext(), ReadMeActivity.class);
                intent.putExtra("linkurl", "http://betdigital.club/terms-and-conditions.html");
                startActivity(intent);
                break;
            case R.id.CVLogout:
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                intent  = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finishAffinity();
                break;
            case R.id.IvUserProfile:
                openImage();
                break;
            case R.id.TvOperations:
                startActivity(new Intent(getContext(), OperationsHistoryActivity.class));
                break;
            case R.id.TvTradingHistory:
                startActivity(new Intent(getContext(), TradingHistoryActivity.class));
                break;
            case R.id.TvSettingTrade:
                startActivity(new Intent(getContext(), TraderoomSettingActivity.class));
                break;
            case R.id.TvSecurity:
                startActivity(new Intent(getContext(), SecurityActivity.class));
                break;
            case R.id.TvCards:
                startActivity(new Intent(getContext(), PaymentActivity.class));
                break;
            case R.id.TvNotification:
                startActivity(new Intent(getContext(), NotificationSettingActivity.class));
                break;
        }
    }


    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImg(){
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading");
        pd.setCanceledOnTouchOutside(getRetainInstance());
        pd.show();

        if (imageUri != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }

                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("UserDetails").child(fuser.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", ""+mUri);
                        reference.updateChildren(map);

                        pd.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        } else {
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();

            if (uploadTask != null && uploadTask.isInProgress()){
                Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
            } else {
                uploadImg();
            }
        }
    }
}
