package com.rl.riloco.loginconface;

import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    TextView txtUser;
    ProfilePictureView imagenUsuario;
    Button btnCerrarSesion;
    private ProfileTracker mProfileTracker;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = (TextView) findViewById(R.id.banner);
        imagenUsuario = (ProfilePictureView) findViewById(R.id.imgProfile);
        btnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);

        if(AccessToken.getCurrentAccessToken() == null){
            IrPantallaLogin();
        }else {
            //Profile profile = Profile.getCurrentProfile();
            //txtUser.setText(profile.getFirstName());u
            //imagenUsuario.setProfileId(profile.getId());

            if(Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        Log.v("facebook - profile", profile2.getFirstName());
                        mProfileTracker.stopTracking();
                    }
                };

            }
            else {
                Profile profile = Profile.getCurrentProfile();
                Log.v("facebook - profile", profile.getFirstName());
            }
        }

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                IrPantallaLogin();
            }
        });

        adView = (AdView) findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
    }

    private void IrPantallaLogin() {
        Intent inten = new Intent(MainActivity.this, LoginActivity.class);
        finish();
        startActivity(inten);
    }
}
