package com.prapul.actionbarsdemo;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DemoTwo extends Activity implements TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_two);
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.addTab(actionbar.newTab().setText("tab1")
				.setTabListener(this));
		actionbar.addTab(actionbar.newTab().setText("tab2")
				.setTabListener(this));
		actionbar.addTab(actionbar.newTab().setText("tab3")
				.setTabListener(this));
		actionbar.setHomeButtonEnabled(true);
		actionbar.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.demo2, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		Fragment frament = new MyFragmentTwo();
		Bundle bu = new Bundle();
		bu.putString("tab", tab.getText().toString());
		frament.setArguments(bu);
		getFragmentManager().beginTransaction()
				.replace(R.id.container1, frament).commit();

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public static class MyFragmentTwo extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			Bundle bu = getArguments();

			ViewGroup group = (ViewGroup) inflater.inflate(R.layout.fragment1,
					null);

			TextView text = (TextView) group.findViewById(R.id.textView1);

			text.setText(bu.getString("tab"));

			return group;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			Intent in = new Intent(DemoTwo.this, ActionBarWithTabs.class);
			startActivity(in);

		}

		switch (item.getItemId()) {
		case R.id.action_refresh:
			item.setActionView(R.layout.progressbar);

			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);

	}

}
