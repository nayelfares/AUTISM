package com.medical.autism.parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.medical.autism.R;
import com.medical.autism.parent.ui.ParentProfile;
import com.medical.autism.parent.ui.ParentTrainers;

public class ParentActivity extends AppCompatActivity {

    public static  String  token = "";
    public static  Integer id    = 0 ;
    public static  String  type  = "trainer" ;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView parentNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        parentNavView = findViewById(R.id.parentNavView);
        toggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        parentNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Menu menu = parentNavView.getMenu();
                for (int i = 0; i < menu.size(); i++) {
                    menu.getItem(i).setChecked(false);
                }
                switch(item.getItemId()) {
                    case R.id.parent_speech_trainers: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        replaceFragmentAndClear(new ParentTrainers());
                        break;
                    }
                    case R.id.parent_join_virtual_session: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.parent_profile: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        replaceFragmentAndClear(new ParentProfile());
                        break;
                    }
                    case R.id.parent_chat: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    default: {
                        Toast.makeText(getApplicationContext(), "nothing selected", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });

        replaceFragmentAndClear( new ParentTrainers());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.parent_container, fragment);
        transaction.addToBackStack(fragment.getTag());
        transaction.commit();
    }
    public void replaceFragmentAndClear(Fragment fragment){
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.parent_container, fragment);
        transaction.addToBackStack(fragment.getTag());
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.parent_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}