package com.sp.madproposal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.sp.madproposal.Fragment.Account;
import com.sp.madproposal.Fragment.AppUsageFragment;
import com.sp.madproposal.Fragment.Attendance;
import com.sp.madproposal.Fragment.EmergencyCall;
import com.sp.madproposal.Fragment.Habits;
import com.sp.madproposal.Fragment.History;
import com.sp.madproposal.Fragment.Location;
import com.sp.madproposal.Fragment.Rewards;
import com.sp.madproposal.Fragment.SMS;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar topAppBar;

    Intent intent;

    private String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();

        initWidget();

        getIntentData();

        pageDirectories();
    }

    private void getIntentData() {

        mPhoneNumber = intent.getStringExtra("FatherNumber");
        System.out.println(mPhoneNumber);

        if (mPhoneNumber.isEmpty() | mPhoneNumber == "") {
            mPhoneNumber = "93542856";
        }

    }

    private void initUI() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new Habits());
        fragmentTransaction.commit();
    }

    private void pageDirectories() {

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);

                Bundle bundle = new Bundle();

                switch (id)
                {
                    case R.id.nav_habits:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new Habits(), bundle);
                        break;

                    case R.id.nav_rewards:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new Rewards(), bundle);
                        break;

                    case R.id.nav_history:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new History(), bundle);
                        break;

                    case R.id.nav_attendance:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new Attendance(), bundle);
                        break;

                    case R.id.nav_account:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new Account(), bundle);
                        break;

                    case R.id.nav_emergency:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new EmergencyCall(), bundle);
                        break;

                    case R.id.nav_sms:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new SMS(), bundle);
                        break;

                    case R.id.nav_location:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new Location(), bundle);
                        break;

                    case R.id.nav_appusage:
                        bundle.putString("FatherNumber", mPhoneNumber);
                        replaceFragment(new AppUsageFragment(), bundle);
                        break;

                }
                return false;
            }
        });

    }

    private void replaceFragment(Fragment fragment, Bundle bundle)
    {
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


    private void initWidget() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        topAppBar = findViewById(R.id.topAppBar);

        initUI();

    }
}