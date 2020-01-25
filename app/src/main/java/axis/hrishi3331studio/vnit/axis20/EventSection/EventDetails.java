package axis.hrishi3331studio.vnit.axis20.EventSection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import axis.hrishi3331studio.vnit.axis20.R;

public class EventDetails extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String event_category;
    private String event_id;
    private String title;
    private TextView titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titlebar = (TextView)toolbar.findViewById(R.id.header_nav);

        Intent intent = getIntent();
        event_category = intent.getStringExtra("cat");
        event_id = intent.getStringExtra("id");

        FirebaseDatabase.getInstance().getReference().child("Events").child(event_category).child(event_id).child("title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    title = dataSnapshot.getValue().toString();
                    titlebar.setText(title);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            View rootView = inflater.inflate(R.layout.fragment_event_details, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:{
                    Fragment fragment = new AboutFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_category", event_category);
                    bundle.putString("event_id", event_id);
                    bundle.putString("section", "about");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                case 1:{
                    Fragment fragment = new StatementFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_category", event_category);
                    bundle.putString("event_id", event_id);
                    bundle.putString("section", "statement");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                case 2:{
                    Fragment fragment = new RulesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_category", event_category);
                    bundle.putString("event_id", event_id);
                    bundle.putString("section", "rules");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                case 3:{
                    Fragment fragment = new ContactFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_category", event_category);
                    bundle.putString("event_id", event_id);
                    bundle.putString("section", "contact");
                    fragment.setArguments(bundle);
                    return fragment;
                }

                default: {
                    Fragment fragment = new ContactFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("event_category", event_category);
                    bundle.putString("event_id", event_id);
                    bundle.putString("section", "contact");
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
