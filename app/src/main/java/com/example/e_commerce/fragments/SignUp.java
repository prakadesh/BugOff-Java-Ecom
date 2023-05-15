package com.example.e_commerce.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.example.e_commerce.data.USER_MODEL;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignUp extends Fragment {

    private ImageView backBtnIVSignUp;

    private TextView signinredirect;
    private Button createAccountBtn;
    private FirebaseAuth mAuth;
    private TextInputEditText userNameTIET;
    private TextInputEditText phoneTIET;
    private TextInputEditText emailTIETSU;
    private TextInputEditText passwordTIETSU;
    private TextInputEditText addressTIETSU;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        initialize(view);

        backBtnIVSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction(new LogIn(),R.anim.neg_100_to_pos_0,R.anim.pos_0_to_pos_100);
            }
        });
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUserSignUp();
            }
        });
        signinredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction(new LogIn(),R.anim.neg_100_to_pos_0,R.anim.pos_0_to_pos_100);

            }
        });


        return view;
    }

    private void initialize(View view){
        backBtnIVSignUp=view.findViewById(R.id.backBtnIVSignUp);
        createAccountBtn=view.findViewById(R.id.createAccountBtn);
        mAuth = FirebaseAuth.getInstance();
        signinredirect=view.findViewById(R.id.textviewhaveacc);

        userNameTIET=view.findViewById(R.id.userNameTIET);
        phoneTIET=view.findViewById(R.id.phoneTIET);
        emailTIETSU=view.findViewById(R.id.emailTIETSU);
        passwordTIETSU=view.findViewById(R.id.passwordTIETSU);
        addressTIETSU=view.findViewById(R.id.addressTIETSU);
    }


    private void fragmentTransaction(Fragment fragment, int entry,int exit){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(entry,exit);
        fragmentTransaction.replace(R.id.frameLayoutLogSignActivity,fragment);
        fragmentTransaction.commit();
    }


    private void newUserSignUp(){
        String email= Objects.requireNonNull(emailTIETSU.getText()).toString().trim();
        String password= Objects.requireNonNull(passwordTIETSU.getText()).toString().trim();
        String phone= Objects.requireNonNull(phoneTIET.getText()).toString().trim();
        String name= Objects.requireNonNull(userNameTIET.getText()).toString().trim();
        String address= Objects.requireNonNull(addressTIETSU.getText()).toString().trim();

        if(!email.equals("") && !password.equals("") && !phone.equals("") && !name.equals("") && !address.equals("")&&
        !password.equals("#")&&!password.equals("@")&&!password.equals("%")&&!password.equals("^")&&!password.equals("&"))


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        firebaseUser.sendEmailVerification();
                        Toast.makeText(getContext(), "You have successfully Signed Up,Check your email to complete verification", Toast.LENGTH_SHORT).show();



                        // Sign in success, update UI with the signed-in user's information
                        USER_MODEL myUserModel = new USER_MODEL(name,email,phone,address);
                        FirebaseDatabase db= FirebaseDatabase.getInstance();
                        DatabaseReference Users= db.getReference("Users");
                        Users.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).setValue(myUserModel);

                        userNameTIET.setText("");
                        phoneTIET.setText("");
                        emailTIETSU.setText("");
                        passwordTIETSU.setText("");
                        addressTIETSU.setText("");


                        fragmentTransaction(new LogIn(),R.anim.neg_100_to_pos_0,R.anim.pos_0_to_pos_100);


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(getContext(), "Failed to Signed Up", Toast.LENGTH_SHORT).show();


                    }
                }
            });
        else Toast.makeText(requireContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();

    }
}