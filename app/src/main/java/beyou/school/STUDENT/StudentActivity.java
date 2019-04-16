package beyou.school.STUDENT;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import beyou.school.ADAPTERS.ViewPagerAdapter;
import beyou.school.R;
import beyou.school.SIGNIN.SignIn;

public class StudentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   private DrawerLayout sdrawerLayout;
   private NavigationView snavigationView;
   private ActionBarDrawerToggle stoggle;
   private Toolbar stoolbar;
   private TabLayout stabLayout;
   private ViewPager sviewPager;
   private ViewPagerAdapter sadapter;
    AppBarLayout sappBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student );
        stoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(stoolbar);
        init();

    }

    private void init()
    {
        sdrawerLayout=findViewById(R.id.sdrawer);
        snavigationView=findViewById(R.id.snav_view);
        stoggle=new ActionBarDrawerToggle(this,sdrawerLayout,R.string.open,R.string.close);
        sdrawerLayout.addDrawerListener(stoggle);
        stoggle.syncState();
        snavigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        sadapter=new ViewPagerAdapter(getSupportFragmentManager());
        sappBarLayout=findViewById(R.id.sappbar);
        stabLayout=findViewById(R.id.stablayout);
        sviewPager=findViewById(R.id.sviewpager);
        sadapter.AddFragmnet(new StuHomework(),"Homework");
        sadapter.AddFragmnet(new StuResult(),"Result");
        sviewPager.setAdapter(sadapter);
        stabLayout.setupWithViewPager(sviewPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        // menu.findItem( R.id.signout ).setVisible( false );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (stoggle.onOptionsItemSelected(item))
        {
            return true;

        }

        if(item.getItemId()==R.id.signout)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(StudentActivity.this,SignIn.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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

        }
        // to close drawer after selecting menu item
        sdrawerLayout.closeDrawer( GravityCompat.START);
        return true;
    }
}
