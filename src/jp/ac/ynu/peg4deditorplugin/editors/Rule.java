package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.*;

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
	public GroupRule(IToken token){
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
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9');
	}
}


class LabelRule implements IRule {
	private IToken token;
	public LabelRule(IToken token) {
		Assert.isNotNull(token);
		this.token = token;
	}
	
	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
			do {
				c = scanner.read();
			} while (!isEqualSymbol((char)c));
			scanner.unread();
			return token;
	}
	
	protected Boolean isEqualSymbol(char c) {
		return (c == '=');
	}
}
