package net.hlw5a.TinMan.Citations;

import net.hlw5a.TinMan.Documents.IDocument;

public interface ICitation {
	
	String createCitation(IDocument document);
	String createShortCitation(IDocument document);
	String getName();
}
