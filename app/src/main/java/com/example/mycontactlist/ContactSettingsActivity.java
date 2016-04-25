package com.example.mycontactlist;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class ContactSettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_settings);

		initSettings();
		initSortByClick();
		initSortOrderClick();
		initChangeBackground();
		initListButton();
		initMapButton();
		initSettingsButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_settings, menu);
		return true;
	}

	private void initSettings() {
		String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield", "contactname");
		String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");
		String backgroundColor = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("backgroundColor", "radioBlue");

		RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
		RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
		RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);

		if (sortBy.equalsIgnoreCase("contactname")) {
			rbName.setChecked(true);
		}
		else if (sortBy.equalsIgnoreCase("city")) {
			rbCity.setChecked(true);
		}
		else {
			rbBirthDay.setChecked(true);
		}

		RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
		RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
		if (sortOrder.equalsIgnoreCase("ASC")) {
			rbAscending.setChecked(true);
		}
		else {
			rbDescending.setChecked(true);
		}

		RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
		RadioButton rbYellow = (RadioButton) findViewById(R.id.radioYellow);
		if (backgroundColor.equalsIgnoreCase("radioBlue")) {
			rbBlue.setChecked(true);
		}
		else {
			rbYellow.setChecked(true);
		}
	}

	private void initSortByClick() {
		RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroup1);
		rgSortBy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
				RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
//				RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthdate);
				if (rbName.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "contactname").commit();
				}
				else if (rbCity.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "city").commit();
				}
				else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").commit();
				}
			}
		});
	}

	private void initChangeBackground() {
		RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup3);
		rgSortOrder.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
				RadioButton rbYellow = (RadioButton) findViewById(R.id.radioYellow);
				final RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout);
				if (rbBlue.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("backgroundColor", "radioBlue").commit();

					rbBlue.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							rl.setBackgroundColor(getResources().getColor(R.color.settingsBackgroundBlue));
						}
					});
				}
				else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("backgroundColor", "radioYellow").commit();

					rbYellow.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							rl.setBackgroundColor(getResources().getColor(R.color.settingsBackgroundYellow));
						}
					});
				}
			}
		});
	}

	private void initSortOrderClick() {
		RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup2);
		rgSortOrder.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
//				RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
				if (rbAscending.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").commit();
				}
				else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").commit();
				}
			}
		});
	}

	private void initListButton() {
		ImageButton list = (ImageButton) findViewById(R.id.imageButtonList);
		list.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	private void initSettingsButton() {
		ImageButton list = (ImageButton) findViewById(R.id.imageButtonSettings);
		list.setEnabled(false);
	}

	private void initMapButton() {
		ImageButton list = (ImageButton) findViewById(R.id.imageButtonMap);
		list.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}


}
