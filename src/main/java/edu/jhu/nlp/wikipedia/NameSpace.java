package edu.jhu.nlp.wikipedia;

public enum NameSpace {
	MEDIA(-2), SPECIAL(-1), NORMAL(0), TALK(1), USER(2), USER_TALK(3), WIKIPEDIA(4), WIKIPEDIA_TALK(5), FILE(
			6), FILE_TALK(7), MEDIAWIKI(8), MEDIAWIKI_TALK(9), TEMPLATE(10), TEMPLATE_TALK(11), HELP(12), HELP_TALK(
					13), CATEGORY(14), CATEGORY_TALK(15), PORTAL(100), PORTAL_TALK(101), BOOK(108), BOOK_TALK(
							109), DRAFT(118), DRAFT_TALK(119), EDUCATION_PROGRAM(446), EDUCATION_PROGRAM_TALK(
									447), TIMEDTEXT(710), TIMEDTEXT_TALK(711), MODULE(828), MODULE_TALK(829), TOPIC(
											2600);

	private NameSpace(int value)

	{
		this.value = value;
	}

	public final int value;

	/**
	 * O(n), n is small, do not care
	 * 
	 * @param value
	 * @return
	 */
	static NameSpace valueOf(int value) {
		NameSpace[] values = NameSpace.values();
		for (int i = 0; i < values.length; i++) {
			if (values[i].value == value)
				return values[i];
		}
		return null;
	}
}
