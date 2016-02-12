package edu.jhu.nlp.wikipedia;

import java.util.HashMap;
import java.util.Set;

/**
 * A class abstracting Wiki infobox
 * 
 * @author Delip Rao
 */
public class InfoBox {
	String infoBoxWikiText = null;
	HashMap<String, String> boxMap;

	InfoBox(String infoBoxWikiText) {
		this.infoBoxWikiText = infoBoxWikiText;
		String[] split = infoBoxWikiText.split("\\n");
		boxMap = new HashMap<>(split.length);
		for (String string : split) {
			String[] split2 = string.split("=", 2);
			if (split2.length != 2)
				continue;
			String key = split2[0].trim().substring(1);

			String value = split2[1].trim();
			if (value.length() > 0) {
				boxMap.put(key, value);
			}
		}
	}

	public String dumpRaw() {
		return infoBoxWikiText;
	}

	public Set<String> getKeySet() {
		return boxMap.keySet();
	}

	@Override
	public String toString() {
		return "InfoBox [infoBoxWikiText=" + infoBoxWikiText + ", boxMap=" + boxMap + "]";
	}

}
