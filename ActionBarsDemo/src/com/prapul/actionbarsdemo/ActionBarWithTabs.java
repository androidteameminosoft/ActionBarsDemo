package com.prapul.actionbarsdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActionBarWithTabs extends Activity implements TabListener {

	ActionBar actionBar;
	MenuItem menuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar_with_tabs);
		actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.addTab(actionBar.newTab().setText("TAB1")
				.setTabListener(this));

		actionBar.addTab(actionBar.newTab().setText("TAB2")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("TAB3")
				.setTabListener(this));

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Fragment fragment = new MyFragment();
		Bundle args = new Bundle();
		args.putInt(MyFragment.Tab_NUMBER, tab.getPosition() + 1);
		fragment.setArguments(args);
		getFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_with_tabs, menu);
		return true;
	}

	public static class MyFragment extends Fragment {
		public static final String Tab_NUMBER = "placeholder_text";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer
					.toString(getArguments().getInt(Tab_NUMBER)));
			return textView;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// View v;
		switch (item.getItemId()) {
		case android.R.id.home:

			finish();

			break;

		case R.id.action_refresh:

			menuItem = item;
			menuItem.setActionView(R.layout.progressbar);
			menuItem.expandActionView();
			Mytask task = new Mytask();
			task.execute("test");

			break;
		case R.id.action_share:
			Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "hello");
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "hai");
			PackageManager pm = ActionBarWithTabs.this.getPackageManager();
			List<ResolveInfo> activityList = pm.queryIntentActivities(
					shareIntent, 0);
			for (final ResolveInfo app : activityList) {
				if ((app.activityInfo.name).contains("facebook")) {
					final ActivityInfo activity = app.activityInfo;
					final ComponentName name = new ComponentName(
							activity.applicationInfo.packageName, activity.name);
					shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
					shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					shareIntent.setComponent(name);
					ActionBarWithTabs.this.startActivity(shareIntent);
					break;
				}
			}
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		outState.putInt("tabposition", getActionBar()
				.getSelectedNavigationIndex());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		if (savedInstanceState.containsKey("tabposition"))
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt("tabposition"));
		super.onRestoreInstanceState(savedInstanceState);
	}

	public class Mytask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			menuItem.collapseActionView();
			menuItem.setActionView(null);
		}

	}

}
