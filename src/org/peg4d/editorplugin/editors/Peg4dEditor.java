package org.peg4d.editorplugin.editors;

import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.source.MatchingCharacterPainter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;

import peg4deditorplug_in.Activator;

public class Peg4dEditor extends TextEditor implements IPropertyChangeListener {
	private ColorManager colorManager;

	public Peg4dEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new PegConfiguration(colorManager));
		setDocumentProvider(new PegDocumentProvider());
		// add preference listener
		Activator.getDefault().getPreferenceStore()
				.addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
		// delete preference listener
		Activator.getDefault().getPreferenceStore()
				.removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		PegConfiguration config = (PegConfiguration) getSourceViewerConfiguration();
		config.updatePreferences();
		getSourceViewer().invalidateTextPresentation();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		MatchingCharacterPainter painter = new MatchingCharacterPainter(
				getSourceViewer(), new PegObjectMatcher());
		painter.setColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));

		ITextViewerExtension2 extension = (ITextViewerExtension2) getSourceViewer();
		extension.addPainter(painter);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);

		PegConfiguration configuration = (PegConfiguration) getSourceViewerConfiguration();
		PegHyperlinkDetector detector = configuration.getPegHyperlinkDetector();
		detector.init(this);
	}

	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.eclipse.ui.textEditorScope",
				"peg4d-editor-plugin.context" });
	}

}
