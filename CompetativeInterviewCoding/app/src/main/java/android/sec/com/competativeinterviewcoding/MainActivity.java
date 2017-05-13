package android.sec.com.competativeinterviewcoding;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,GeeksForGeeksFragment.OnFragmentInteractionListener,
        CareerCupFragment.OnFragmentInteractionListener , CodeChefFragment.OnFragmentInteractionListener,
        CodeEvalFragment.OnFragmentInteractionListener ,CodeForceFragment.OnFragmentInteractionListener,
        CoderByteFragment.OnFragmentInteractionListener,CodeWarsFragment.OnFragmentInteractionListener,
        CodinGameFragment.OnFragmentInteractionListener ,GlassDoorFragment.OnFragmentInteractionListener,
        GyanloBlogFragment.OnFragmentInteractionListener,HackerEarthFragment.OnFragmentInteractionListener,
        HackerRankFragment.OnFragmentInteractionListener,InterviewBitFragment.OnFragmentInteractionListener,
        JavaRevisitedBlogFragment.OnFragmentInteractionListener,
        ProjectEulerFragment.OnFragmentInteractionListener, SpojFragment.OnFragmentInteractionListener,
        TopCoderFragment.OnFragmentInteractionListener , UvaOnlineJudgeFragment.OnFragmentInteractionListener
{
    @Override
    public void onGeeksForGeeksFragmentInteraction(Uri uri) {

    }

    private ViewPager mPager;
    private TabLayout mSlidingTab;
    private PagerTabStrip mPagerstrip;
    private PagerFragmentAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPager = (ViewPager) findViewById(R.id.main_pager);
        mSlidingTab = (TabLayout) findViewById(R.id.sliding_tabs);
        mPagerstrip = (PagerTabStrip) findViewById(R.id.pagestrip);
        mPagerAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        mSlidingTab.setupWithViewPager(mPager);
        mPagerstrip.setDrawFullUnderline(true);
        mPagerstrip.setTabIndicatorColor(Color.GREEN);
        mPagerAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class PagerFragmentAdapter extends FragmentPagerAdapter {

        public static final int TOTALTAB = 19;
        public static final String TITLE[]={"GeeksForGeeks" ,"CareerCup","GyanloBlog" ,"JavaRevisited","LeetCode","HackerRank",
        "InterviewBit","GlassDoor","ProjectEuler","TopCoder","CodeChef","CodeForces","CoderByte","CodeWars","Spoj","CodinGame",
        "CodeEval","HackerEarth","UvaOnline"};
        public static final int GEEKSFORGEEKS   =0;
        public static final int CAREERCUP=1;
        public static final int GYANLOBLOG=2;
        public static final int JAVAREVISITED=3;
        public static final int LEETCODE=4;
        public static final int HACKERRANK=5;
        public static final int HACKEREARTH=17;
        public static final int INTERVIEWBIT=6;
        public static final int GLASSDOOR=7;
        public static final int PROJECTEULER=8;
        public static final int TOPCODER=9;
        public static final int CODECHEF=10;
        public static final int CODEFORCES=11;
        public static final int CODERBYTE=12;
        public static final int CODEWARS=13;
        public static final int SPOJ=14;
        public static final int CODINGAME=15;
        public static final int CODEEVAL=16;
        public static final int UVAONLINE=18;



        public PagerFragmentAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case GEEKSFORGEEKS :
                    return new GeeksForGeeksFragment();
                case CAREERCUP:
                    return new CareerCupFragment();
                case GYANLOBLOG:
                    return new GyanloBlogFragment();
                case JAVAREVISITED:
                    return  new JavaRevisitedBlogFragment();
                case LEETCODE:
                    return new LeetCodeFragment();
                case HACKERRANK:
                    return new HackerRankFragment();
                case HACKEREARTH:
                    return new HackerEarthFragment();
                case INTERVIEWBIT:
                    return new InterviewBitFragment();
                case  GLASSDOOR:
                    return new GlassDoorFragment();
                case PROJECTEULER:
                    return new ProjectEulerFragment();
                case TOPCODER:
                    return new TopCoderFragment();
                case CODECHEF:
                    return new CodeChefFragment();
                case CODEFORCES:
                    return new CodeForceFragment();
                case CODERBYTE:
                    return new CoderByteFragment();
                case CODEWARS:
                    return new CodeWarsFragment();
                case SPOJ:
                    return new SpojFragment();
                case CODINGAME:
                    return new CodinGameFragment();
                case CODEEVAL:
                    return new CodeEvalFragment();
                case UVAONLINE :
                    return new UvaOnlineJudgeFragment();
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return TOTALTAB;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }
    }
}
