import java.util.*;

public class wordLadder2 {
	public static void main(String[] args){
		String start = "hit";
		String end = "cog";
		String[] SET_VALUES = new String[] {"hot","dot","dog","lot","log"};		
		Set<String> dict = new HashSet<String>(Arrays.asList(SET_VALUES));
        List<List<String>> result = new ArrayList<>();
        result = findLadders(start, end, dict);
        System.out.println(result);
    }

    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
    	List<String> buffer = new ArrayList<String>();
    	List<List<String>> result = new ArrayList();
    	if (start == end) {
    		buffer.add(start);
    		result.add(buffer);
    		return result;
    	}
    	dict.add(start);
    	dict.add(end);
    	Map<String, Integer> distance = new HashMap<String, Integer>();
    	distance = bfs(start, end, dict);
    	buffer.add(start);
    	dfs(result, buffer, dict, start, end, distance);
    	return result;
    }

    public static Map<String, Integer> bfs(String start, String end, Set<String> dict) {
    	Queue<String> queue = new LinkedList<String>();
    	Set<String> hash = new HashSet<String>();
    	Map<String, Integer> distance = new HashMap<String, Integer>();
    	queue.offer(end);
    	hash.add(end);
    	int dis = 0;
    	distance.put(end, dis);
    	while(!queue.isEmpty()) {
    		dis++;
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			String curWord = queue.poll();
    			for (String nextWord: getNextWords(curWord, dict)) {
    				if (hash.contains(nextWord)){
    					continue;
    				}
    				queue.offer(nextWord);
    				hash.add(nextWord);
    				distance.put(nextWord,dis);
    			}
    		}
    	}
    	return distance;
    }

    public static List<String> getNextWords(String curWord, Set<String> dict) {
    	List<String> nextWords = new ArrayList<String>();
    	for (int i = 0; i < curWord.length(); i++) {
    		for (char c = 'a'; c <= 'z'; c++) {
    			if (curWord.charAt(i) == c) {
    				continue;
    			}
    			String newWord = replace(curWord, i, c);
    			if (dict.contains(newWord)) {
    				nextWords.add(newWord);
    			}
    		}
    	}
    	return nextWords;    	
    }

    public static String replace(String curWord, int index, char c) {
    	char[] words = curWord.toCharArray();
    	words[index] = c;
    	return new String(words);
    }

    public static void dfs(List<List<String>> result, List<String> buffer, Set<String> dict, String curWord, String end, Map<String, Integer> distance) {
    	if (curWord.equals(end)) {
    		result.add(new ArrayList<String>(buffer));
    		return;
    	}
    	for (String nextWord: getNextWords(curWord, dict)) {
    		if (distance.get(nextWord) == distance.get(curWord) - 1) {
    			buffer.add(nextWord);
    			dfs(result, buffer, dict, nextWord, end, distance);
    			buffer.remove(buffer.size() - 1);
    		}
    	}
    }
}


// 1. 用Arrays.asList初始化set