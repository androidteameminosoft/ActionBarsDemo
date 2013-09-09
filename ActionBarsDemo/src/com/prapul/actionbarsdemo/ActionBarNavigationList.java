package com.prapul.actionbarsdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ActionBarNavigationList extends Activity implements
		ActionBar.OnNavigationListener {

	String dropDownValues[] = { "India", "Australiya", "NewZiland" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_list);
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(actionbar.NAVIGATION_MODE_LIST);
		actionbar.setDisplayShowTitleEnabled(false);

		// Specify a SpinnerAdapter to populate the dropdown list.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actionbar.getThemedContext(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				dropDownValues);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		actionbar.setListNavigationCallbacks(adapter, this);

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, itemPosition + 1);
		fragment.setArguments(args);
		getFragmentManager().beginTransaction().replace(R.id.contianer, fragment).commit();
		return true;
	}

	public static class DummySectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "placeholder_text";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}
}
