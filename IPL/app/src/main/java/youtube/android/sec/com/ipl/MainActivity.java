package youtube.android.sec.com.ipl;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.FirebaseDatabase;

import youtube.android.sec.com.ipl.dummy.DummyContent;
import youtube.android.sec.com.ipl.model.Match;
import youtube.android.sec.com.ipl.model.Player;
import youtube.android.sec.com.ipl.model.Team;

public class MainActivity extends AppCompatActivity implements TeamFragment.OnListFragmentInteractionListener, MatchFragment.OnListFragmentInteractionListener,
        PlayeerFragment.OnListFragmentInteractionListener, LiveScoreFragment.OnFragmentInteractionListener, ScoreFragment.OnFragmentInteractionListener {

    private PagerFragmentAdapter mAdapter;
    private ViewPager mPager;
    private TabLayout mSlidingTab;
    public String[] teamName;
    public AdView mAdView;

    public static final String[] TABTITLE = {"Teams", "Match", "Player", "LiveScore"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // google ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4508795483897212~8198065287");
        // google ads

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        teamName = getResources().getStringArray(R.array.team_name);
        FirebaseDatabase.getInstance().getReference("livescore").keepSynced(true);

        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.main_pager);
        mSlidingTab = (TabLayout) findViewById(R.id.sliding_tabs);
        mPager.setAdapter(mAdapter);
        mSlidingTab.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mAdView = (AdView) findViewById(R.id.adView);
        //mAdView.setAdSize(AdSize.BANNER);
        //mAdView.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteractionTeam(Team item) {

    }

    @Override
    public void onLiveFragmentInteraction(Uri uri) {

    }

    @Override
    public void onScoreFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Match item) {

    }

    @Override
    public void onListFragmentInteraction(Player item) {

    }
    // pagerAdapter start

    public static class PagerFragmentAdapter extends FragmentPagerAdapter {

        public static final int TOTALTAB = 4;

        public PagerFragmentAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TeamFragment();
                case 1:
                    return new MatchFragment();
                case 2:
                    return new PlayeerFragment();
                case 3:
                    return new ScoreFragment();
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
            return TABTITLE[position];
        }
    }

// pagerAdapter end
}
