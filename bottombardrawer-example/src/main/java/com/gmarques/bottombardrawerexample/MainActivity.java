package com.gmarques.bottombardrawerexample;

import android.content.Context;
import android.widget.*;
import com.gmarques.bottombardrawer.BottomBarDrawerLayout;
import com.gmarques.bottombardrawer.BottomBarDrawerLayout.DrawerListener;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listview);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        final BottomBarDrawerLayout slideInLayout = (BottomBarDrawerLayout) findViewById(R.id.drawerLayout);
        slideInLayout.setCloseOnlyWithHeader(true);
        listview.setAdapter(adapter);

		final ActionBar actionBar = getActionBar();

        final Button botao = (Button) findViewById(R.id.botao);

        botao.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideInLayout.openDrawerView();
                    }
                }
        );
		slideInLayout.setDrawerShadow(R.drawable.drawer_shadow);

		slideInLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int newState) {

			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				if (actionBar != null) {
					if (slideOffset > 0.8f) {
						actionBar.hide();

					} else {

						actionBar.show();
					}
				}
			}

			@Override
			public void onDrawerOpened(View drawerView) {
                //listview.setAdapter(adapter);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
                //listview.setAdapter(null);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    void clicou(){

    }

}
