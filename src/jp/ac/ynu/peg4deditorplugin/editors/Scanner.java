package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;

class PEGScanner extends RuleBasedScanner {
	public PEGScanner(ColorManager manager) {
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

		IRule[] rules = { new ConnectorRule(connecter), new tagRule(tag),
				new labelRule(label), new SingleLineRule("`", "`", insert),
				new SingleLineRule("\"", "\"", string),
				new SingleLineRule("'", "'", string),
				new SingleLineRule("[example:", "]", example),
				new SingleLineRule("[", "]", character),
				new WhitespaceRule(new PegWhitespaceDetector()) };

		setRules(rules);
	}
}