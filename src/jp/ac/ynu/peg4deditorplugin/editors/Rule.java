package jp.ac.ynu.peg4deditorplugin.editors;

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

class TagRule extends Rule {
	public TagRule(IToken token) {
		super("#", " ", token);
	}
}
