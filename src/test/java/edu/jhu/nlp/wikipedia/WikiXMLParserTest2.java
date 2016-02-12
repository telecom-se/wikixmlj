package edu.jhu.nlp.wikipedia;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WikiXMLParserTest2 {
	@Test
	public void testSaxParser() throws Exception {
		WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser(getClass().getResource("/mini.xml"));

		wxsp.setPageCallback(new PageCallbackHandler() {
			@Override
			public void process(WikiPage page) {
				if (!page.isRedirect()) {
					System.out.println(page.getFirstParagraph());
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println("--------------------------------------------------------------------------------------");
				}

			}
		});
		wxsp.parse();
	}
}
