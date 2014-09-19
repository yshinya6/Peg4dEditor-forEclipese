package jp.ac.ynu.peg4deditorplugin.preference;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import peg4deditorplug_in.Activator;

public class peg4dPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private Text text;

	public peg4dPreferencePage() {
		super(GRID);
		setPreferenceStore(.getDefault().getPreferenceStore());
	}

	public peg4dPreferencePage(String title) {
		super(title);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public peg4dPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected Control createContents(Composite parent) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Composer:");

		text = new Text(composite, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setText(store.getString(Peg4dPreferenceInitializer.COMPOSER_NAME));

		return composite;
	}

	public boolean performOk() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setValue(Peg4dPreferenceInitializer.COMPOSER_NAME, text.getText());
		return true;
	}

	protected void performDefaults() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		text.setText(store.getDefaultString(Peg4dPreferenceInitializer.COMPOSER_NAME));
	}

	@Override
	public Point computeSize() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean okToLeave() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean performCancel() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void setContainer(IPreferencePageContainer preferencePageContainer) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setSize(Point size) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void createControl(Composite parent) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Control getControl() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getDescription() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Image getImage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getTitle() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void performHelp() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setDescription(String description) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setImageDescriptor(ImageDescriptor image) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setTitle(String title) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setVisible(boolean visible) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
