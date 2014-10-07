package org.peg4d.editorplugin.preference;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import peg4deditorplug_in.Activator;

public class peg4dPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	// private Text text;

	public peg4dPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_TAG,
				"tag (#ABC)", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_CONNECTOR,
				"Connector (@ABC)", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_COMMENT,
				"comment", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_STRING,
				"strings (\"ABC\")", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_CHARACTER,
				"regex character ([ABC])", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_DEFAULT,
				"default", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_EXAMPLE,
				"example ([example: ])", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_VALUE,
				"value (`ABC`)", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_LABEL,
				"rule name (ABC = ...)", getFieldEditorParent()));
	}

	// public peg4dPreferencePage(String title) {
	// super();
	// // TODO 自動生成されたコンストラクター・スタブ
	// }

	// public peg4dPreferencePage(String title, ImageDescriptor image) {
	// super();
	// // TODO 自動生成されたコンストラクター・スタブ
	// }

	@Override
	public void init(IWorkbench workbench) {
		// TODO 自動生成されたメソッド・スタブ

	}

	// @Override
	// protected Control createContents(Composite parent) {
	// IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	// Composite composite = new Composite(parent, SWT.NONE);
	//
	// GridLayout layout = new GridLayout();
	// layout.numColumns = 2;
	// composite.setLayout(layout);
	//
	// Label label = new Label(composite, SWT.NONE);
	// label.setText("Composer:");
	//
	// text = new Text(composite, SWT.SINGLE | SWT.BORDER);
	// text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	// text.setText(store.getString(Peg4dPreferenceInitializer.COMPOSER_NAME));
	//
	// return composite;
	// }

	// @Override
	// public boolean performOk() {
	// IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	// store.setValue(Peg4dPreferenceInitializer.COMPOSER_NAME, text.getText());
	// return true;
	// }
	//
	// @Override
	// protected void performDefaults() {
	// IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	// text.setText(store
	// .getDefaultString(Peg4dPreferenceInitializer.COMPOSER_NAME));
	// }

	// @Override
	// public Point computeSize() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }

	// @Override
	// public boolean isValid() {
	// // TODO 自動生成されたメソッド・スタブ
	// return false;
	// }
	//
	// @Override
	// public boolean okToLeave() {
	// // TODO 自動生成されたメソッド・スタブ
	// return false;
	// }
	//
	// @Override
	// public boolean performCancel() {
	// // TODO 自動生成されたメソッド・スタブ
	// return false;
	// }

	// @Override
	// public void setContainer(IPreferencePageContainer
	// preferencePageContainer) {
	// // TODO 自動生成されたメソッド・スタブ
	//
	// }

	// @Override
	// public void setSize(Point size) {
	// // TODO 自動生成されたメソッド・スタブ
	//
	// }

	// @Override
	// public void createControl(Composite parent) {
	// // TODO 自動生成されたメソッド・スタブ
	//
	// }


	// @Override
	// public Control getControl() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }

	// @Override
	// public String getDescription() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }

	// @Override
	// public String getErrorMessage() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }

	// @Override
	// public Image getImage() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }

	// @Override
	// public String getMessage() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }

	// @Override
	// public String getTitle() {
	// // TODO 自動生成されたメソッド・スタブ
	// return null;
	// }


	// @Override
	// public void setImageDescriptor(ImageDescriptor image) {
	// // TODO 自動生成されたメソッド・スタブ
	//
	// }

}
