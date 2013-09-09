package com.prapul.actionbarsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button firstBScrren;
	Button butWithTabs;
	Button butWithNavigationList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUi();
		firstBScrren.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent in = new Intent(MainActivity.this, FirstScreenDemo.class);
				startActivity(in);

			}
		});
		butWithTabs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent in = new Intent(MainActivity.this,
						ActionBarWithTabs.class);
				startActivity(in);

			}
		});

		butWithNavigationList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent in = new Intent(MainActivity.this,
						ActionBarNavigationList.class);
				startActivity(in);

			}
		});

	}

	/**
	 * this is method used for initilization ui
	 * 
	 * @author prudhvi reddy
	 */

	private void initUi() {
		firstBScrren = (Button) findViewById(R.id.butFirst);
		butWithTabs = (Button) findViewById(R.id.butSecondTabs);
		butWithNavigationList = (Button) findViewById(R.id.butwithnav);

	}
}
