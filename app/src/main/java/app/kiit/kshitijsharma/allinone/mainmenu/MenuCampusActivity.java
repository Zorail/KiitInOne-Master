package app.kiit.kshitijsharma.allinone.mainmenu;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import app.kiit.kshitijsharma.allinone.sqdatabase.mycontentprovider;
import java.util.ArrayList;

import app.kiit.kshitijsharma.allinone.mainmenuactivites.CampusMapsActivity;
import app.kiit.kshitijsharma.allinone.R;

public class MenuCampusActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
ListView lv1;
    ArrayList al1;
    String path="content://a.b.c.d/Campuses";
    Uri uri= Uri.parse(path);
    ContentResolver cr;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_campus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv1= (ListView) findViewById(R.id.listView1);
        al1= new ArrayList();
        cr = getContentResolver();
        String q="select * from Campuses";
        Cursor cur= cr.query(uri,null,null,null,null);
        while(cur.moveToNext()){
           int name=cur.getInt(0);
            String s= "Campus "+name;
            al1.add(s);
        }
        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,al1);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    Intent i;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.timet) {
            i= new Intent(this,MenuTimetableActivity.class);
            startActivity(i);
        } else if (id == R.id.attendance) {

        } else if (id == R.id.nearp) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        cr = getContentResolver();
        String Ab="";
        double lat=0.0;
        double longi=0.0;
        String q="select * from Campuses";
        Cursor cur= cr.query(uri,null,null,null,null);
        while(cur.moveToNext()) {
            if (i+1==cur.getInt(0)){
                Ab=cur.getString(1);
                lat=cur.getDouble(2);
                longi= cur.getDouble(3);
                Toast.makeText(this,"FOUND AT "+cur.getInt(0),Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(this,CampusMapsActivity.class);
        intent.putExtra("Ab",Ab);
        intent.putExtra("L",lat);
        intent.putExtra("P",i+1);
        intent.putExtra("LO",longi);
        startActivity(intent);
        }
}
