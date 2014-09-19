package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class PEGConfiguration extends SourceViewerConfiguration {
	private PEGDoubleClickStrategy doubleClickStrategy;
	private PEGTagScanner tagScanner;
	private PEGScanner scanner;
	private ColorManager colorManager;

	public PEGConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			PEGPartitionScanner.PEG_COMMENT,
			PEGPartitionScanner.PEG_ANGLE,
			PEGPartitionScanner.PEG_OBJECT,
			PEGPartitionScanner.PEG_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new PEGDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected PEGScanner getPEGScanner() {
		if (scanner == null) {
			scanner = new PEGScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IPEGColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected PEGTagScanner getPEGTagScanner() {
		if (tagScanner == null) {
			tagScanner = new PEGTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IPEGColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getPEGTagScanner());
		reconciler.setDamager(dr, PEGPartitionScanner.PEG_TAG);
		reconciler.setRepairer(dr, PEGPartitionScanner.PEG_TAG);

		dr = new DefaultDamagerRepairer(getPEGScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IPEGColorConstants.PEG_COMMENT)));
		reconciler.setDamager(ndr, PEGPartitionScanner.PEG_COMMENT);
		reconciler.setRepairer(ndr, PEGPartitionScanner.PEG_COMMENT);

		return reconciler;
	}

}