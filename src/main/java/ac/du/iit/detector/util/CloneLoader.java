package ac.du.iit.detector.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import detector.model.ClonePairId;
import detector.model.ClonePairIdCode;
import detector.model.FunctionIdPathStarEnd;

public class CloneLoader {

	public static List<ClonePairIdCode> getClonePairWithCode(List<ClonePairId> javaCSharpMethodIDPairs,
			String javaMethodIDPath, String cSharpMethodIDPath) throws IOException {

		List<FunctionIdPathStarEnd> javaMethodsIDPathStartEnd = FileUtil.getFunctionIdPathStartEnd(javaMethodIDPath);
		List<FunctionIdPathStarEnd> cSharpMethodsIDPathStartEnd = FileUtil
				.getFunctionIdPathStartEnd(cSharpMethodIDPath);

		List<ClonePairIdCode> clonePairIdCode = getClonePairIdCode(javaCSharpMethodIDPairs, javaMethodsIDPathStartEnd,
				cSharpMethodsIDPathStartEnd);
		return clonePairIdCode;

	}

	public static List<ClonePairIdCode> getClonePairIdCode(List<ClonePairId> javaCSharpMethodIDPairs,
			List<FunctionIdPathStarEnd> javaMethodsIDPathStartEnd,
			List<FunctionIdPathStarEnd> cSharpMethodsIDPathStartEnd) throws IOException {

		List<ClonePairIdCode> _clonePairIdCode = new ArrayList<ClonePairIdCode>();

		for (ClonePairId pairIds : javaCSharpMethodIDPairs) {

			FunctionIdPathStarEnd javaMethods = findFunctionIdPathStarEnd(pairIds.getJavaMethodID(),
					javaMethodsIDPathStartEnd);
			String javaMethodCode = FileUtil.getCodeByPathAndLineNumber(javaMethods.getPath(),
					javaMethods.getStartLineNo(), javaMethods.getEndLineNo());

			FunctionIdPathStarEnd cSharpMethod = findFunctionIdPathStarEnd(pairIds.getcSharpMethodID(),
					cSharpMethodsIDPathStartEnd);
			String cSharpMethodCode = FileUtil.getCodeByPathAndLineNumber(cSharpMethod.getPath(),
					cSharpMethod.getStartLineNo(), cSharpMethod.getEndLineNo());

			_clonePairIdCode.add(new ClonePairIdCode(pairIds.getJavaMethodID(), javaMethodCode,
					pairIds.getcSharpMethodID(), cSharpMethodCode));
		}

		return _clonePairIdCode;
	}

	public static FunctionIdPathStarEnd findFunctionIdPathStarEnd(String id,
			List<FunctionIdPathStarEnd> idPathStartEnd) {
		for (FunctionIdPathStarEnd fidp : idPathStartEnd) {
			String functionID=fidp.getFunctionId().toString();
			if (functionID.equals(id)) {
				return fidp;
			}
		}
		return null;
	}

}
