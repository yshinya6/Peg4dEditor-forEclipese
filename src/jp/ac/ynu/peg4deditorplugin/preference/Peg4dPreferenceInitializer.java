package jp.ac.ynu.peg4deditorplugin.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import peg4deditorplug_in.Activator;

public class Peg4dPreferenceInitializer extends AbstractPreferenceInitializer {
	public static String COMPOSER_NAME = "sin.y";
	public Peg4dPreferenceInitializer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(COMPOSER_NAME, "Shinya Yamaguchi");
	}

}
