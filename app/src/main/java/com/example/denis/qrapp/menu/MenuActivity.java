package com.example.denis.qrapp.menu;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.denis.qrapp.R;
import com.example.denis.qrapp.WiFiTest;
import com.example.denis.qrapp.barcodeScanner.barcode.BarcodeCaptureActivity;
import com.example.denis.qrapp.data.Kafic;
import com.example.denis.qrapp.data.NarucenoPice;
import com.example.denis.qrapp.data.Narudzba;
import com.example.denis.qrapp.root.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.emitter.Emitter;
import retrofit2.Retrofit;

public class MenuActivity extends AppCompatActivity implements MenuActivityMVP.View, NavigationView.OnNavigationItemSelectedListener {


    @Inject
    MenuActivityMVP.Presenter presenter;

    @Inject
    Retrofit retrofit;


    MemoryRepository repo;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private  RecyclerView narudzbe;
    private Narudzba narudzba;
    private NaruceniAdapter naruceniAdapter;
    private ArrayList<NarucenoPice> pica;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ((App) getApplication()).getComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.





        Log.d("View:", "Krećem sa requestom date!");
        presenter.requestData(this);

        pica = new ArrayList<>();
        //NarucenoPice(String naziv, double cijena, String kolicina, String nacin)




        narudzbe = (RecyclerView) findViewById(R.id.naruceniRecycler);
        naruceniAdapter = new NaruceniAdapter(pica);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        narudzbe.setLayoutManager(layoutManager);
        narudzbe.addItemDecoration(new RecyclerDecoration(this, LinearLayoutManager.VERTICAL));
        narudzbe.setAdapter(naruceniAdapter);


        //((App) getApplication()).getNetComponent().inject(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        }

    public void setViewData(){


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), presenter.getActiveKafic());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        LinearLayout bottomSheetViewGroup = (LinearLayout) findViewById(R.id.bottomSheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetViewGroup);

       // bottomSheetBehavior.setState(BottomSheetBehavior.STATE_DRAGGING);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                /*Snackbar snackbar = Snackbar
                        .make(view, "Dodano!", Snackbar.LENGTH_LONG);

                snackbar.show();
                */

                io.socket.client.Manager manager = new Manager();
                manager.socket("15454");
                final io.socket.client.Socket socket = new io.socket.client.Socket(manager, "http://smaffe-dex6.c9users.io/");
                socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        socket.emit("foo", "hi");
                        Log.d("Socket", "emited");

                    }
                });
                socket.connect();
            }
        });



        narudzbe = (RecyclerView) findViewById(R.id.naruceniRecycler);



        /*
        FloatingActionButton fabKonobar = (FloatingActionButton) findViewById(R.id.fab_konobar);
        fabKonobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                Snackbar snackbar = Snackbar
                        .make(view, "Konobar!", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        */

    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_menu, menu);
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
            Log.d("NavID = ", id+"");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, WiFiTest.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        @Inject
        Retrofit retrofit;

        private Kafic activeKafic;




        private static final String ARG_SECTION_NUMBER = "section_number";
        private RecyclerView recyclerView;
        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager layoutManager;


        public PlaceholderFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, Kafic activeKafic) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable("Kafic", activeKafic);


            fragment.setArguments(args);





            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

            TextView textView = (TextView) rootView.findViewById(R.id.redCijena);




            Kafic kafic = (Kafic) getArguments().getSerializable("Kafic");
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerPica);



            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);






            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    adapter = new RecyclerViewAdapter(kafic.getTopli());
                    recyclerView.setAdapter(adapter);
                    return rootView;
                case 2:
                    adapter = new RecyclerViewAdapter(kafic.getBezalkoholni());
                    recyclerView.setAdapter(adapter);
                    return rootView;
                case 3:
                    adapter = new RecyclerViewAdapter(kafic.getPivo());
                    recyclerView.setAdapter(adapter);
                    return rootView;
                case 4:
                    adapter = new RecyclerViewAdapter(kafic.getVina());
                    recyclerView.setAdapter(adapter);
                    return rootView;
                case 5:
                    adapter = new RecyclerViewAdapter(kafic.getZestoki());
                    recyclerView.setAdapter(adapter);
                    return rootView;
            }



            adapter = new RecyclerViewAdapter(kafic.getPica());
            recyclerView.setAdapter(adapter);
            return rootView;
        }

        public static String getArgSectionNumber() {
            return ARG_SECTION_NUMBER;
        }

        @Override
        public void onPause() {
            super.onPause();

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Kafic activeKafic;

        public SectionsPagerAdapter(FragmentManager fm, Kafic activeKafic) {
            super(fm);

            this.activeKafic = activeKafic;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position+1, activeKafic);
        }

        @Override
        public int getCount() {

            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Topli napitci";
                case 1:
                    return "Bezalkoholna pića";
                case 2:
                    return "Pivo";
                case 3:
                    return "Vina";
                case 4:
                    return "Žestoka pića";
            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NarucenoPice narucenoPice){


       for(int i = 0; i < pica.size();i++){
           NarucenoPice temp = pica.get(i);
           if (temp.getNaziv() == narucenoPice.getNaziv() && temp.getNacin() == narucenoPice.getNacin()){
               temp.setKolicina(temp.getKolicina() + narucenoPice.getKolicina());
               pica.set(i, temp);
               naruceniAdapter.notifyItemChanged(i);
               return;
           }
       }

        pica.add(narucenoPice);
        naruceniAdapter.notifyDataSetChanged();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRemove(Integer position){
        pica.remove(position.intValue());
        Log.d("position ...", position+"");
        for (int i = 0; i <pica.size();i++){
            Log.d("pice:... ", pica.get(i).getNaziv());
        }
        naruceniAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
