package edu.jhu.nlp.wikipedia;

import static org.junit.Assert.*;

import org.junit.Test;

public class WikiFirstParagraphExtractorTest {

	@Test
	public void test() throws Exception {
		WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser(getClass().getResource("/newton.xml"));

		wxsp.setPageCallback(new PageCallbackHandler() {
			@Override
			public void process(WikiPage page) {

				
				System.out.println(page.getCategories());
				

			}
		});
		wxsp.parse();
	}

}
