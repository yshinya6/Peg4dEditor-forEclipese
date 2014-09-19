package jp.ac.ynu.peg4deditorplugin.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class PEGWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
