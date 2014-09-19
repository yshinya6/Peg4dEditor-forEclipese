package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;
import org.eclipse.ui.internal.handlers.WizardHandler.New;

class PEGScanner extends RuleBasedScanner {
	public PEGScanner(ColorManager manager) {
		IToken connecter =
			new Token(
				new TextAttribute(
					manager.getColor(IPEGColorConstants.PEG_CONNECTER)));
		IToken tag =
			new Token(
				new TextAttribute(
					manager.getColor(IPEGColorConstants.TAG)));

		IRule[] rules = new IRule[3];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("@", " ", connecter);
		rules[1] = new SingleLineRule("#", " ", tag);
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new PEGWhitespaceDetector());

		setRules(rules);
	}
}

class PEGTagScanner extends RuleBasedScanner {

	public PEGTagScanner(ColorManager manager) {
		IToken string =
			new Token(
				new TextAttribute(manager.getColor(IPEGColorConstants.STRING)));

		IRule[] rules = new IRule[3];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new PEGWhitespaceDetector());

		setRules(rules);
	}
}
