package org.peg4d.editorplugin.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

class PEGScanner extends RuleBasedScanner {
	public PEGScanner(ColorManager manager) {
		IToken token = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.DEFAULT)));
		IToken connecter = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.PEG_CONNECTER)));
		IToken tag = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.TAG)));
		IToken string = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.STRING)));
		IToken label = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.LABEL)));
		IToken insert = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.INSERT)));
		IToken character = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.PEG_CHARACTER)));
		IToken example = new Token(new TextAttribute(
				manager.getColor(IPegColorConstants.PEG_EXAMPLE)));

		IRule[] rules = { new tagRule(tag), new ConnectorRule(connecter),
		/* new labelRule(label), */new SingleLineRule("`", "`", insert),
				new SingleLineRule("\"", "\"", string),
				new SingleLineRule("'", "'", string),
				new SingleLineRule("[example:", "]", example),
				new SingleLineRule("[", "]", character), /* new tokenRule(token), */
				new WhitespaceRule(new PegWhitespaceDetector()),
		/* new PegWordRule("@", connecter) */};

		setRules(rules);
	}
}

class PegWordRule extends WordRule {
	public PegWordRule(String word, IToken token) {
		super(new PegWordDetector());
		addWord(word, token);
	}
}

class PegWordDetector implements IWordDetector {
	@Override
	public boolean isWordStart(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || (c == '@');
	}

	@Override
	public boolean isWordPart(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || (c == '@');
	}
}
