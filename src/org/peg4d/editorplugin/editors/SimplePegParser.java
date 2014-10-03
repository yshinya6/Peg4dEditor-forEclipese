package org.peg4d.editorplugin.editors;

import org.eclipse.jface.text.rules.IToken;

public class SimplePegParser {
	//TODO invoke peg4d API and send parsing result 
}

class PegToken implements IToken {
	
	public int getOffset() {
		return 0;
	}
	
	public int getLength() {
		return 0;
	}
	
	@Override
	public boolean isUndefined() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWhitespace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEOF() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOther() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}