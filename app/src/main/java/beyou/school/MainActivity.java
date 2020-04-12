package beyou.school;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import beyou.school.ADAPTERS.ViewPagerAdapter;
import beyou.school.Models.User;
import beyou.school.Teacher.ADDUSER;
import beyou.school.Teacher.ActivityFrament;
import beyou.school.Teacher.Homework;
import beyou.school.Teacher.Result;
import beyou.school.SIGNIN.SignIn;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Intent intent;
    TextView hname,hemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav_view);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        adapter.AddFragmnet(new Homework(),"Homework");
        adapter.AddFragmnet(new ActivityFrament(),"Activity");
        adapter.AddFragmnet( new Result(),"Result" );
        viewPager.setAdapter(adapter);
        hideItem();
        tabLayout.setupWithViewPager(viewPager);
        FirebaseDatabase.getInstance().getReference().child( "Users" )
                .child( FirebaseAuth.getInstance().getCurrentUser().getUid() )
                .addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        User user=dataSnapshot.getValue(User.class);
                        View header=navigationView.getHeaderView(0);
                        hname=header.findViewById( R.id.hname );
                        hemail=header.findViewById( R.id.hemailid );
                        hname.setText( user.getUsername() );
                        hemail.setText( user.getEmailid() );

                    }

                    @Override
                    public void onCancelled( DatabaseError databaseError) {

                    }
                } );
       
    }
    private void hideItem()
    {   intent=getIntent();
        if(intent.getStringExtra( "type" )!="Teacher")
        navigationView =  findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.add).setVisible(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (toggle.onOptionsItemSelected(item))
        {
            return true;

        }

        if(item.getItemId()==R.id.signout)
        {


            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(MainActivity.this,SignIn.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home :
                Toast.makeText(getBaseContext(),"home clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting: Toast.makeText(getBaseContext(),"setting clicked ",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "setting clicked ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(getApplicationContext(),"about clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                           Intent intent=new Intent(MainActivity.this, ADDUSER.class);
                           startActivity(intent);
                           break;
        }
        // to close drawer after selecting menu item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
