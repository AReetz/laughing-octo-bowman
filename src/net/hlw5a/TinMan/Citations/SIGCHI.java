package net.hlw5a.TinMan.Citations;

import java.util.Iterator;

import net.hlw5a.TinMan.Contributor.IPerson;
import net.hlw5a.TinMan.Documents.IDocument;

public class SIGCHI implements ICitation {

	private static volatile ICitation instance = null;
	private static String name = "SIGCHI Proceedings Format";
	
	public static ICitation getInstance() {
		if (instance == null) {
			synchronized (SIGCHI.class){
				if (instance == null) {
					instance = new SIGCHI();
				}
			}
		}
		return instance;
	}
	
	public String getName() { return name; }
	
	public String createCitation(IDocument document) {

		StringBuilder citation = new StringBuilder();
		
		switch (document.getType()) {
		
		case ConferenceProceedings:
			Iterator<IPerson> iter = document.getAuthors();
			while (iter.hasNext()) {
				IPerson cont = iter.next();
				if (!iter.hasNext()) citation.append("and ");
				citation.append(cont.getLastName()).append(", ");
				String[] firstNames = cont.getFirstNames().split(" ");
				for (int i = 0; i < firstNames.length; i++) {
					citation.append(firstNames[i].substring(0,1)).append(".");
				}
				if (iter.hasNext()) citation.append(", "); else citation.append(" ");
			}
			citation.append(document.getTitle()).append(". In ");
			citation.append(document.getPublication()).append(", ");
			citation.append(document.getPublisher().getName()).append(" (").append(document.getYear()).append("), ");
			citation.append(document.getPages());
			break;
			
		case JournalArticle:
			iter = document.getAuthors();
			while (iter.hasNext()) {
				IPerson cont = iter.next();
				if (!iter.hasNext()) citation.append("and ");
				citation.append(cont.getLastName()).append(", ");
				String[] firstNames = cont.getFirstNames().split(" ");
				for (int i = 0; i < firstNames.length; i++) {
					citation.append(firstNames[i].substring(0,1)).append(".");
				}
				if (iter.hasNext()) citation.append(", "); else citation.append(" ");
			}
			citation.append(document.getTitle()).append(". ");
			citation.append(document.getPublication()).append(" ");
			citation.append(document.getIssue()).append(", ");
			citation.append(document.getVolume()).append(" ");
			citation.append(" (").append(document.getYear()).append("), ");
			citation.append(document.getPages());
			break;
			
		default:
			break;
		}
				
		return citation.toString();
	}
	
	public String createShortCitation(IDocument document) {
		
		StringBuilder citation = new StringBuilder();
		
		switch (document.getType()) {
		
		case ConferenceProceedings:

			String regex = "^Proceedings [\\w\\s-]* - (\\w*) ('\\d){2}";
			String replacement = "Proc of $1 $2";
		
			String publication = document.getPublication().replaceAll(regex, replacement);
			String title = document.getTitle();
		
			Iterator<IPerson> iter = document.getAuthors();
			IPerson cont = iter.next();
			citation.append(cont.getLastName());
			if (!iter.hasNext()) citation.append(": ");
			else {
				cont = iter.next();
				if (iter.hasNext()) citation.append(", et al.: "); else citation.append(" and ").append(cont.getLastName()).append(": ");
			}		
			if (title.contains(":")) citation.append(title.substring(0, title.indexOf(":"))).append(". "); else citation.append(title).append(". ");
			citation.append(publication).append(", ");
			citation.append(document.getPublisher().getName()).append(", ");
			citation.append(document.getPages());
			break;
			
		case JournalArticle:
			iter = document.getAuthors();
			cont = iter.next();
			citation.append(cont.getLastName());
			if (!iter.hasNext()) citation.append(": ");
			else {
				cont = iter.next();
				if (iter.hasNext()) citation.append(", et al.: "); else citation.append(" and ").append(cont.getLastName()).append(": ");
			}
			citation.append(document.getTitle()).append(". ");
			citation.append(document.getPublication()).append(" ");
			citation.append(document.getIssue()).append(",");
			citation.append(document.getVolume()).append(", ");
			citation.append(document.getPages());
			break;
			
		default:
			break;
		}
		return citation.toString();
	}
}
