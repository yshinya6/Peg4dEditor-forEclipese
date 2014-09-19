package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class Peg4dEditor extends TextEditor {

	private ColorManager colorManager;

	public Peg4dEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new PEGConfiguration(colorManager));
		setDocumentProvider(new PEGDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
