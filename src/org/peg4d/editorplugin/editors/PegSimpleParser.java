package org.peg4d.editorplugin.editors;

import org.peg4d.Grammar;
import org.peg4d.GrammarFactory;
import org.peg4d.ParsingContext;
import org.peg4d.ParsingRule;
import org.peg4d.ParsingSource;
import org.peg4d.UList;

public class PegSimpleParser {
	private String sourceText;
	private GrammarFactory factory;

	public PegSimpleParser(String sourceText) {
		this.sourceText = sourceText;
		this.factory = new GrammarFactory();
	}

	public UList<ParsingRule> parseGrammar() {
		ParsingSource source = org.peg4d.ParsingSource.loadSourceText(sourceText);
		Grammar peg = new Grammar(factory, "target");
		peg.loadGrammar(source);
		UList<ParsingRule> ruleList = peg.getRuleList();
		return ruleList;
	}

	public ParsingContext parse() {
		ParsingSource source = org.peg4d.ParsingSource.loadSourceText(sourceText);
		Grammar peg4d = GrammarFactory.Grammar;
		ParsingContext context = new ParsingContext(source);
		while (context.hasByteChar()) {
			context.parse(peg4d, "Chunk");
			if (context.isFailure()) {
				break;
			}
		}
		return context;
	}
}
