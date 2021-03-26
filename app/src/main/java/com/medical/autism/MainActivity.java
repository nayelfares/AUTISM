package com.medical.autism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static  String  token = "";
    public static  Integer id    = 0 ;
    public static  String  type  = "trainer" ;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView parentNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    case R.id.nav_speech_trainers: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.nav_join_virtual_session: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.nav_profile: {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.nav_chat: {
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}