package ac.du.iit.detector.core;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import detector.model.Bag;
import detector.model.ClonePairId;
import detector.model.ClonePairIdCode;
import detector.model.TokenFrequency;
import detector.util.CloneLoader;
import detector.util.CloneUtil;
import detector.util.FileUtil;

public class CloneDetector {

	private float threashold;
	private String javaTokenFilePath;
	private String cSharpTokenFilepath;
	private String javaHeaderFilePath;
	private String cSharpHeaderFilePath;
	private String outputCloneIDCodes;
	private String outputCloneIDs;

	private List<ClonePairId> detectedClonePair;

	public CloneDetector(float threashold, String javaHeaderFilePath, String javaTokenFilePath,
			String cSharpHeaderFilePath, String cSharpTokenFilepath, String outputCloneIDs, String outputCloneIDCodes) {
		this.threashold = threashold;
		this.javaHeaderFilePath = javaHeaderFilePath;
		this.javaTokenFilePath = javaTokenFilePath;
		this.cSharpHeaderFilePath = cSharpHeaderFilePath;
		this.cSharpTokenFilepath = cSharpTokenFilepath;
		this.outputCloneIDs = outputCloneIDs;
		this.outputCloneIDCodes = outputCloneIDCodes;
		detectedClonePair = new ArrayList<ClonePairId>();
	}

	public Map<String, Bag> getBagMap() throws ParseException, IOException {
		List<String> javaTokenList = FileUtil.getTokenListFromTokenFile(javaTokenFilePath);
		Set<Bag> bagOfRawJavaTokens = CloneUtil.getBagSetFromTokenList(javaTokenList);
		Set<Bag> bagOfPopulatedJavaTokens = CloneUtil.extractAndPopulateJavaBagOfToken(bagOfRawJavaTokens);

		Map<String, Bag> bagMap = new HashMap<String, Bag>();
		for (Bag b : bagOfPopulatedJavaTokens) {
			bagMap.put(b.getMethodID(), b);
		}
		return bagMap;
	}

	public Set<Bag> getProbableBagSet(Set<String> idSet, Map<String, Bag> bagMap) {

		Set<Bag> bagSet = new HashSet<Bag>();
		for (String s : idSet) {
			bagSet.add(bagMap.get(s));
		}
		return bagSet;

	}

	public void detectClone() throws ParseException, IOException {
		List<String> javaTokenList = FileUtil.getTokenListFromTokenFile(javaTokenFilePath);
		Set<Bag> bagOfRawJavaTokens = CloneUtil.getBagSetFromTokenList(javaTokenList);
		Set<Bag> bagOfPopulatedJavaTokens = CloneUtil.extractAndPopulateJavaBagOfToken(bagOfRawJavaTokens);

		Map<String, Bag> bagMap = new HashMap<String, Bag>();
		for (Bag b : bagOfPopulatedJavaTokens) {
			bagMap.put(b.getMethodID(), b);
		}

		detectProbableCLones(bagOfPopulatedJavaTokens, bagOfPopulatedJavaTokens, threashold);
		FileUtil.writeClones(detectedClonePair, outputCloneIDs);
		List<ClonePairIdCode> clonePairIDCodes = CloneLoader.getClonePairWithCode(detectedClonePair, javaHeaderFilePath,
				cSharpHeaderFilePath);
		FileUtil.writeClonesWithCode(clonePairIDCodes, outputCloneIDCodes);

	}

	public void outputClone() throws IOException {
		FileUtil.writeClones(detectedClonePair, outputCloneIDs);
		List<ClonePairIdCode> clonePairIDCodes = CloneLoader.getClonePairWithCode(detectedClonePair, javaHeaderFilePath,
				cSharpHeaderFilePath);
		FileUtil.writeClonesWithCode(clonePairIDCodes, outputCloneIDCodes);
	}

	public void detectReducedClones(Set<Bag> javaTokenBagSet, Bag targetBag, float threashold) throws IOException {
		for (Bag bagInSetOne : javaTokenBagSet) {
			if (!bagInSetOne.getMethodID().equals(targetBag.getMethodID())) {
				detectClonesAsCloneWork(bagInSetOne, targetBag, threashold);
			}

		}
	}

	private void detectProbableCLones(Set<Bag> javaTokenBagSet, Set<Bag> cSharpTokenBagSet, float threashold)
			throws IOException {
		for (Bag bagInSetOne : javaTokenBagSet) {
			for (Bag bagInSetTwo : cSharpTokenBagSet) {
				if (!bagInSetOne.getMethodID().equals(bagInSetTwo.getMethodID())) {
					detectClonesAsCloneWork(bagInSetOne, bagInSetTwo, threashold);
				}

			}
		}
	}

	private void detectClonesAsCloneWork(Bag bagA, Bag bagB, float threashold) throws IOException {

		Bag max = null;
		Bag min = null;
		if (bagA.getSize() >= bagB.getSize()) {
			max = bagA;
			min = bagB;
		}
		if (bagB.getSize() >= bagA.getSize()) {
			max = bagB;
			min = bagA;
		}

		int threshold = (int) Math.ceil(max.getSize() * threashold);
		int sharedTokens = 0;
		// int remainingTokens = max.getSize();
		if (min.getSize() < threshold) {
			// return false;
			return;
		}
		for (TokenFrequency maxTF : max.getTokenFrequency()) {
			TokenFrequency minTF = min.get(maxTF);
			// int maxFreq = maxTF.getFrequency();
			// Integer minFreq = (Integer) min.get(term);
			if (minTF != null) {
				sharedTokens += Math.min(maxTF.getFrequency(), minTF.getFrequency());
			}
			// remainingTokens -= maxFreq;
			if (sharedTokens >= threshold) {
				detectedClonePair.add(new ClonePairId(bagA.getMethodID(), bagB.getMethodID()));
				break;
			}

		}
	}

}
