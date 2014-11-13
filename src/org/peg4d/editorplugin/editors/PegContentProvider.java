package org.peg4d.editorplugin.editors;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.peg4d.ParsingRule;
import org.peg4d.UList;

//アウトラインツリーの内容を管理するクラス
public class PegContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		RootData rootData = (RootData) inputElement;
		if (rootData.ruleList == null) {
			return new Object[] {};
		}
		UList<ParsingRule> newList = extractLiteralRules(rootData.ruleList);
		return newList.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RuleSet) {
			RuleSet ruleSet = (RuleSet) parentElement;
			return ruleSet.getLiteralList();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof RuleSet) {
			return true;
		}
		return false;
	}

	public UList<ParsingRule> extractLiteralRules(UList<ParsingRule> parentList) {
		ArrayList<ParsingRule> literals = new ArrayList<>();
		ArrayList<ParsingRule> nonTerminalRules = new ArrayList<>();
		UList<ParsingRule> newList = new UList<ParsingRule>(new ParsingRule[4]);
		for (int i = 0; i < parentList.size(); i++) {
			if (parentList.get(i).ruleName.startsWith("\"")) {
				literals.add(parentList.get(i));
			} else {
				nonTerminalRules.add(parentList.get(i));
			}
		}
		RuleSet nonTerminalRuleSet = new RuleSet("RULES", nonTerminalRules);
		RuleSet literalRuleSet = new RuleSet("LITERALS", literals);
		newList.add(nonTerminalRuleSet);
		newList.add(literalRuleSet);
		return newList;
	}
}

class RuleSet extends ParsingRule {
	Object[] ruleList;

	public RuleSet(String ruleName, ArrayList<ParsingRule> literals) {
		super(null, ruleName, null, null);
		this.ruleList = literals.toArray();
	}

	public Object[] getLiteralList() {
		return this.ruleList;
	}

}
