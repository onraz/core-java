package questions.findcommonancestor;

import java.util.HashMap;
import java.util.Map;

public class MyFindCommonAncestor implements FindCommonAncestor {
	@Override
    public String findCommmonAncestor(String[] commitHashes,
    		String[][] parentHashes, String commitHash1, String commitHash2) {
		if (commitHashes.length != parentHashes.length || commitHashes == null
				|| commitHashes.length == 0 || parentHashes == null
				|| parentHashes.length == 0 || commitHash1 == null
				|| commitHash1.length() == 0 || commitHash2 == null
				|| commitHash2.length() == 0)
			return null;

		// Create a lookup table for commits
		Map<String, Integer> commitIndexesMap = commitIndexesMap(commitHashes);
		int commit1Pos = commitIndexesMap.get(commitHash1);
		int commit2Pos = commitIndexesMap.get(commitHash2);

		// identify the later and earlier commits
		int laterCommit = Math.min(commit1Pos, commit2Pos);
		int earlierCommit = Math.max(commit1Pos, commit2Pos);

		// find the later ancestor, and set the earlier commit as the earlier ancestor
		String laterAncestor = getLastParentById(parentHashes, laterCommit);
		String earlierAncestor = commitHashes[earlierCommit];

		// point to the earlier and later ancestors in the graph
		earlierCommit = commitIndexesMap.get(earlierAncestor);
		laterCommit = commitIndexesMap.get(laterAncestor);

		// Keep Searching for a least common parent by moving down the graph
		while (!laterAncestor.equals(earlierAncestor)) {
			if (laterCommit > earlierCommit) {
				earlierAncestor = getLastParentById(parentHashes, earlierCommit);
				earlierCommit = commitIndexesMap.get(earlierAncestor);
			} else {
				laterAncestor = getLastParentById(parentHashes, laterCommit);
				laterCommit = commitIndexesMap.get(laterAncestor);
			}
		}

		return commitHashes[earlierCommit];
	}

	public static String getLastParentById(String[][] parentHashes, int id) {
		return id == (parentHashes.length - 1) ? null
				: parentHashes[id][parentHashes[id].length - 1];
	}

	public Map<String, Integer> commitIndexesMap(String[] commitHashes) {
		Map<String, Integer> commitIndexes = new HashMap<>();
		for (int index = 0; index < commitHashes.length; index++) {
			commitIndexes.put(commitHashes[index], index);
		}
		return commitIndexes;
	}

}
