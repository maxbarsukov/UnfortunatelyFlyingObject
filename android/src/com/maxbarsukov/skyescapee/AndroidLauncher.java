package com.maxbarsukov.skyescapee;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.maxbarsukov.skyescapee.Main;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useCompass = false;
		config.useAccelerometer = false;
		config.useGyroscope = false;

		initialize(new Main(), config);
	}
}
