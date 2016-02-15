package edu.jhu.nlp.wikipedia;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * A Wrapper class for the PageCallbackHandler
 *
 * @author Jason Smith
 *
 */
public class SAXPageCallbackHandler extends DefaultHandler {

	private PageCallbackHandler pageHandler;
	private WikiPage currentPage;
	private String currentTag;

	// Current largest article is 784K
	private StringBuilder currentWikitext = new StringBuilder(1_000_000);
	private StringBuilder currentTitle = new StringBuilder(200);
	private StringBuilder currentID = new StringBuilder(20);
	private StringBuilder revisionID = new StringBuilder(20);
	private StringBuilder ns = new StringBuilder(4);
	private String language = null;
	private int iDcount;

	public SAXPageCallbackHandler(PageCallbackHandler pageHandler, String language) {
		this.pageHandler = pageHandler;
		this.language = language;
	}

	@Override
	public void startElement(String uri, String name, String qName, Attributes attr) {
		currentTag = qName;
		if (qName.equals("page")) {
			currentPage = new WikiPage();
			currentWikitext.setLength(0);
			currentTitle.setLength(0);
			currentID.setLength(0);
			revisionID.setLength(0);
			ns.setLength(0);
			iDcount = 0;

		}
	}

	@Override
	public void endElement(String uri, String name, String qName) {
		if (qName.equals("page")) {
			currentPage.setTitle(currentTitle.toString());
			String stringid = currentID.toString().trim();
			if (stringid.length() > 0)
				currentPage.setID(Long.parseLong(stringid));
			String stringRevisionID = revisionID.toString().trim();
			if (stringRevisionID.length() > 0)
				currentPage.setRevisionID(Long.parseLong(stringRevisionID));
			currentPage.setWikiText(currentWikitext.toString(), language);
			if (ns.length() > 0) {
				String string = ns.toString().trim();
				currentPage.setNs(NameSpace.valueOf(Integer.parseInt(string)));
			}
			pageHandler.process(currentPage);
		}
		if (qName.equals("mediawiki")) {
			// TODO hasMoreElements() should now return false
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		if (currentTag.equals("title")) {
			currentTitle = currentTitle.append(ch, start, length);
		}
		// TODO: To avoid looking at the revision ID, only the first ID is
		// taken.
		// I'm not sure how big the block size is in each call to characters(),
		// so this may be unsafe.
		else if ((currentTag.equals("id"))) {
			iDcount++;
			// First ID - the page one
			if (currentID.length() == 0) {
				currentID.append(ch, start, length);
			} else if (iDcount == 3) {
				// Already something, so this is the second one
				revisionID.append(ch, start, length);
			}
		} else if (currentTag.equals("text")) {
			currentWikitext = currentWikitext.append(ch, start, length);
		} else if (currentTag.equals("ns")) {
			ns.append(ch, start, length);
		}
	}
}