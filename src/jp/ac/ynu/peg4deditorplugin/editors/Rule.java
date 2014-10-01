package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

public class Rule extends MultiLineRule {
	public Rule(String startSequence, String endSequence, IToken token) {
		super(startSequence, endSequence, token);
	}
}

class AngleRule extends Rule {
	public AngleRule(IToken token) {
		super("<", ">", token);
	}
}

class ObjectRule extends Rule {
	public ObjectRule(IToken token) {
		super("{", "}", token);
	}
}

class GroupRule extends Rule {
	public GroupRule(IToken token) {
		super("(", ")", token);
	}
}

class ConnectorRule implements IRule {
	private IToken token;

	public ConnectorRule(IToken token) {
		Assert.isNotNull(token);
		this.token = token;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
		if (c == '@') {
			return token;
		}
		scanner.unread();
		return Token.UNDEFINED;
	}
}

class tagRule implements IRule {
	private IToken token;

	public tagRule(IToken token) {
		Assert.isNotNull(token);
		this.token = token;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
		if (c == '#') {
			do {
				c = scanner.read();
			} while (isWordPart((char) c));
			scanner.unread();
			return token;
		}
		scanner.unread();
		return Token.UNDEFINED;
	}

	protected Boolean isWordPart(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')
				|| ('0' <= c && c <= '9');
	}
}

class labelRule implements IRule {
	private IToken token;

	public labelRule(IToken token) {
		Assert.isNotNull(token);
		this.token = token;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
		if (isLetterPart((char) c)) {
			do {
				c = scanner.read();
			} while ((isWordPart((char) c)));
			scanner.unread();
			do {
				c = scanner.read();
				if (c == '=') {
					scanner.unread();
					return token;
				}
			} while (isWhiteSpace((char) c));
			scanner.unread();
			return Token.UNDEFINED;
		}
		scanner.unread();
		return Token.UNDEFINED;
	}

	protected Boolean isWordPart(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')
				|| ('0' <= c && c <= '9');
	}

	protected Boolean isLetterPart(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
	}

	protected Boolean isWhiteSpace(char c) {
		return (c == '\n') || (c == '\r') || (c == ' ') || (c == '\t');
	}
}
