package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.source.MatchingCharacterPainter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;

public class Peg4dEditor extends TextEditor {
	private ColorManager colorManager;

	public Peg4dEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new PegConfiguration(colorManager));
		setDocumentProvider(new PegDocumentProvider());
	}

	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
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
}
