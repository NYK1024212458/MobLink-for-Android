package com.mob.moblink.demo.util.statusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;

/**
 * Utils for status bar
 */
public class StatusBarCompat {

	//Get alpha color
	static int calculateStatusBarColor(int color, int alpha) {
		float a = 1 - alpha / 255f;
		int red = color >> 16 & 0xff;
		int green = color >> 8 & 0xff;
		int blue = color & 0xff;
		red = (int) (red * a + 0.5);
		green = (int) (green * a + 0.5);
		blue = (int) (blue * a + 0.5);
		return 0xff << 24 | red << 16 | green << 8 | blue;
	}

	/**
	 * set statusBarColor
	 *
	 * @param statusColor color
	 * @param alpha       0 - 255
	 */
	public static void setStatusBarColor(Activity activity, int statusColor, int alpha) {
		setStatusBarColor(activity, calculateStatusBarColor(statusColor, alpha), true);
	}

	public static void setStatusBarColor(Activity activity, int statusColor, boolean isLight) {
		if (Color.WHITE == statusColor && Build.VERSION.SDK_INT < 23) {
			statusColor = calculateStatusBarColor(statusColor, 30);
		}
		if (Build.VERSION.SDK_INT >= 21) {
			StatusBarCompatLollipop.setStatusBarColor(activity, statusColor, isLight);
		} else if (Build.VERSION.SDK_INT >= 19) {
			StatusBarCompatKitKat.setStatusBarColor(activity, statusColor);
		}
	}

	public static void translucentStatusBar(Activity activity, boolean isLight) {
		if (Build.VERSION.SDK_INT < 23) {
			translucentStatusBar(activity, isLight, false);
		} else {
			translucentStatusBar(activity, isLight, true);
		}
	}

	/**
	 * change to full screen mode
	 *
	 * @param hideStatusBarBackground hide status bar alpha Background when SDK > 21, true if hide it
	 */
	public static void translucentStatusBar(Activity activity, boolean isLight, boolean hideStatusBarBackground) {
		if (Build.VERSION.SDK_INT >= 21) {
			StatusBarCompatLollipop.translucentStatusBar(activity, isLight, hideStatusBarBackground);
		} else if (Build.VERSION.SDK_INT >= 19) {
			StatusBarCompatKitKat.translucentStatusBar(activity);
		}
	}

}
