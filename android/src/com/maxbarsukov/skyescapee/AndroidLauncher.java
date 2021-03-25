package com.maxbarsukov.skyescapee;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.maxbarsukov.skyescapee.Game;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		// It's not necessary, seriously
		config.useCompass = false;
		config.useAccelerometer = false;
		config.useGyroscope = false;

		initialize(new Game(), config);
	}
}
