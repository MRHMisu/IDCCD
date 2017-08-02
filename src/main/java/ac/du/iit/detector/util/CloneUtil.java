package ac.du.iit.detector.util;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import detector.model.Bag;
import detector.model.Token;
import detector.model.TokenFrequency;

public class CloneUtil {

	public static Set<Bag> getBagSetFromTokenList(List<String> tokenList) throws ParseException {
		Set<Bag> bagSet = new HashSet<Bag>();
		for (String token : tokenList) {
			bagSet.add(deserialiseToken(token));
		}
		return bagSet;
	}

	private static Bag deserialiseToken(String s) throws ParseException {
		if (null != s && s.trim().length() > 0) {
			String[] parentIdAndBlockIdAndToken = s.split("##");
			String[] parentIdAndBlockId = parentIdAndBlockIdAndToken[0].split(",");
			String sourceFileID = parentIdAndBlockId[0];
			String methodID = parentIdAndBlockId[1];
			String tokenString = parentIdAndBlockIdAndToken[1].replace("{", "").replace("}", "");
			Bag bag = new Bag(methodID);
			bag.setParentId(sourceFileID);
			bag.setToken(tokenString);
			return bag;
		}
		throw new ParseException("parsing error", 0);
	}

	public static Set<Bag> extractAndPopulateJavaBagOfToken(Set<Bag> bagSet) {
		for (Bag bag : bagSet) {
			String[] tokenFrequencyString = bag.getToken().split(",");
			for (String tokenWithFrequency : tokenFrequencyString) {
				tokenWithFrequency = tokenWithFrequency.replace("(", "").replace(")", "");
				String[] tokenAndFrequencySplited = tokenWithFrequency.split("-");
				String tokenString = strip(tokenAndFrequencySplited[0]).trim();
				if (tokenString.length() > 0) {

					Token token = new Token(tokenString);
					TokenFrequency tokenFrequency = new TokenFrequency();
					tokenFrequency.setToken(token);
					try {
						tokenFrequency.setFrequency(Integer.parseInt(tokenAndFrequencySplited[1]));
						bag.addTokenFrequency(tokenFrequency);

					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("EXCEPTION CAUGHT, token: " + token);
						System.out.println("EXCEPTION CAUGHT: " + bag.getToken());

					}

				}
			}
		}
		return bagSet;
	}

	private static String strip(String str) {
		return str.replaceAll("(\'|\"|\\\\)", "");
	}

}
