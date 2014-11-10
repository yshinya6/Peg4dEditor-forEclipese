package org.peg4d.editorplugin.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
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
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.SimpleMarkerAnnotation;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.peg4d.ParsingRule;
import org.peg4d.UList;

import peg4deditorplug_in.Activator;

public class PegEditor extends TextEditor implements IPropertyChangeListener {
	private ColorManager colorManager;
	public static final String ASSIST_ACTION_ID = "Peg4dEditor.Assist";
	protected OutlinePage outlinePage;

	public PegEditor() {
		super();
		colorManager = new ColorManager();
		setDocumentProvider(new PegDocumentProvider());
		setSourceViewerConfiguration(new PegConfiguration(colorManager));

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.addPropertyChangeListener(this);
		// // add preference listener
		// Activator.getDefault().getPreferenceStore()
		// .addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		// // delete preference listener
		// Activator.getDefault().getPreferenceStore()
		// .removePropertyChangeListener(this);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.removePropertyChangeListener(this);
		colorManager.dispose();
		super.dispose();
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

		MatchingCharacterPainter painter = new MatchingCharacterPainter(getSourceViewer(),
				new PegObjectMatcher());
		painter.setColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));

		ITextViewerExtension2 extension = (ITextViewerExtension2) getSourceViewer();
		extension.addPainter(painter);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
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

	@Override
	protected void createActions() {
		super.createActions();
		IAction contentAssistAction = new Action("Contents Assist") {
			@Override
			public void run() {
				ITextOperationTarget target = (ITextOperationTarget) PegEditor.this
						.getAdapter(ITextOperationTarget.class);
				target.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
			}
		};
		contentAssistAction
				.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction(ASSIST_ACTION_ID, contentAssistAction);
	}

	public static void addAnnotation(IMarker marker, ITextSelection selection, ITextEditor editor) {
		// The DocumentProvider enables to get the document currently loaded in
		// the editor
		IDocumentProvider idp = editor.getDocumentProvider();

		// This is the document we want to connect to. This is taken from
		// the current editor input.
		IDocument document = idp.getDocument(editor.getEditorInput());

		// The IannotationModel enables to add/remove/change annotation to a
		// Document
		// loaded in an Editor
		IAnnotationModel iamf = idp.getAnnotationModel(editor.getEditorInput());

		// Note: The annotation type id specify that you want to create one of
		// your
		// annotations
		SimpleMarkerAnnotation ma = new SimpleMarkerAnnotation("org.peg4d.editorplugin.occurences",
				marker);

		// Finally add the new annotation to the model
		iamf.connect(document);
		iamf.addAnnotation(ma, new Position(selection.getOffset(), selection.getLength()));
		iamf.disconnect(document);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (IContentOutlinePage.class.equals(adapter)) {
			if (outlinePage == null) {
				outlinePage = new OutlinePage(this);
			}
			return outlinePage;
		}

		// // フォールディング
		// Object obj = foldingManager.getAdapter(adapter);
		// if (obj != null) {
		// return obj;
		// }

		return super.getAdapter(adapter);
	}

	@Override
	public void doSaveAs() {
		super.doSaveAs();

		update();
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);

		update();
	}

	private void update() {
		IDocument document = getDocumentProvider().getDocument(getEditorInput());
		PegSimpleParser parser = new PegSimpleParser(document.get());
		UList<ParsingRule> ruleList = parser.parse();

		// フォールディング範囲を最新状態に更新する
		// foldingManager.updateFolding(document, models);

		// アウトラインを最新状態に更新する
		if (outlinePage != null) {
			outlinePage.refresh(ruleList);
		}
	}
}
