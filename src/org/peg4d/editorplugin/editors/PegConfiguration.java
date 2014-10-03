package org.peg4d.editorplugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class PegConfiguration extends SourceViewerConfiguration {
	private PegDoubleClickStrategy doubleClickStrategy;
	private PegHyperlinkDetector pegHyperlinkDetector;
	private PEGScanner scanner;
	private ColorManager colorManager;

	public PegConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			PegPartitionScanner.PEG_COMMENT };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new PegDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected PEGScanner getPEGScanner() {
		if (scanner == null) {
			scanner = new PEGScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IPegColorConstants.DEFAULT))));
		}
		return scanner;
	}
	

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();


		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getPEGScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IPegColorConstants.PEG_COMMENT)));
		reconciler.setDamager(ndr, PegPartitionScanner.PEG_COMMENT);
		reconciler.setRepairer(ndr, PegPartitionScanner.PEG_COMMENT);

		return reconciler;
	}
	
	public PegHyperlinkDetector getPegHyperlinkDetector() { 
		if (pegHyperlinkDetector == null) {
			pegHyperlinkDetector = new PegHyperlinkDetector();
		}
		return pegHyperlinkDetector;
	}
	
	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		IHyperlinkDetector[] parentDetecors = super.getHyperlinkDetectors(sourceViewer);
		IHyperlinkDetector[] myDetectors = new IHyperlinkDetector[parentDetecors.length + 1];
		System.arraycopy(parentDetecors, 0, myDetectors, 0, parentDetecors.length);
		myDetectors[myDetectors.length - 1] = getPegHyperlinkDetector();
		return myDetectors;
	}
	
}