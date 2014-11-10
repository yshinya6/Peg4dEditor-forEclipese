package org.peg4d.editorplugin.editors;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

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
		// ArrayList<String> ruleNameList = new ArrayList<String>();
		// for (int i = 0; i < rootData.ruleList.size(); i++) {
		// ruleNameList.add(rootData.ruleList.get(i).ruleName);
		// }
		// return ruleNameList.toArray();
		return rootData.ruleList.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}

}
