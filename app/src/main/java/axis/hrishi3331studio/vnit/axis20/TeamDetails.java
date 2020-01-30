package axis.hrishi3331studio.vnit.axis20;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TeamDetails extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container1);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.team_tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mDialog = new ProgressDialog(TeamDetails.this);
        mDialog.setMessage("Please wait..");
        mDialog.setTitle("Loading");
        mDialog.setCanceledOnTouchOutside(false);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.team_view, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public void goBack(View view){
        finish();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:{
                    Fragment fragment = new TeamFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("team_category", "core");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                case 1:{
                    Fragment fragment = new TeamFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("team_category", "publicity");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                case 2:{
                    Fragment fragment = new TeamFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("team_category", "web");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                case 3:{
                    Fragment fragment = new TeamFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("team_category", "app");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                default: {
                    Fragment fragment = new TeamFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("team_category", "core");
                    fragment.setArguments(bundle);
                    return fragment;
                }
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
