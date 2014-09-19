package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.jface.text.rules.*;

public class PEGPartitionScanner extends RuleBasedPartitionScanner {
	public final static String PEG_COMMENT = "__peg_comment";
	public final static String PEG_ANGLE = "__peg_angle";
	public final static String PEG_TAG = "__peg_tag";
	public final static String PEG_OBJECT = "__peg_object";
	public final static String PEG_GROUP = "__peg_group";

	public PEGPartitionScanner() {

		IToken pegComment = new Token(PEG_COMMENT);
		IToken angle = new Token(PEG_ANGLE);
		IToken tag = new Token(PEG_TAG);
		IToken pegObject = new Token(PEG_OBJECT);
		IToken pegGroup = new Token(PEG_GROUP);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("//", "\n", pegComment);
		rules[1] = new MultiLineRule("/*", "*/", pegComment);
		rules[2] = new AngleRule(angle);
		rules[3] = new ObjectRule(pegObject);
		rules[4] = new GroupRule(pegGroup);
		rules[5] = new TagRule(tag);

		setPredicateRules(rules);
	}
}
