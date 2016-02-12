package edu.jhu.nlp.wikipedia;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import edu.jhu.nlp.util.FileUtil;

public class TestCitation {

	public static void main(String[] args) throws IOException {
		String readFile = FileUtils.readFileToString((new File(TestCitation.class.getResource("/citation.txt").getFile())));
		//System.out.println(WikiTextParser.clearCitation(readFile));
	}
}
