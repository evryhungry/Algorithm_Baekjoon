import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1차 풀이.
 * <br>
 * <br>
 * 풀이.
 * <br>1. 단어의 위치를 찾고, 해당 단어가 어떤 단어인지 찾는다.
 * <br>2. 이미 노출된 단어를 저장한다. int[] revealedAt
 * <br>3. 노출이 되지않은 단어들의 조합을 생성한다. Set<String> nonSpoilerSet
 * <br>4. 노출이 된 단어의 리스트를 찾아낸다. List<int[]> spoilerWords
 * <br>5. 해당 단어가 이미 노출 되었는지에 대한 확인을 거친다. Set<String> revealedSpoilerSet <br/>
 */
class Solution {
    List<int[]> wordPos = new ArrayList<>();
    List<String> wordTexts = new ArrayList<>();

    public int solution(String message, int[][] spoiler_ranges) {
        int n = message.length();
        int i = 0;

        while (i < n){
            if (message.charAt(i) == ' ') { i++; continue; }
            int start = i;
            while (i < n && message.charAt(i) != ' ') i++;
            wordPos.add(new int[]{start, i - 1});
            wordTexts.add(message.substring(start, i));
        }

        int W = wordPos.size();
        int S = spoiler_ranges.length;

        int[] revealedAt = new int[W];
        Arrays.fill(revealedAt, -1);

        for (int w = 0 ; w < W ; w++){
            int ws = wordPos.get(w)[0]; int we = wordPos.get(w)[1];
            for (int s = 0 ; s < S; s++){
                int ss = spoiler_ranges[s][0]; int se = spoiler_ranges[s][1];
                if (ss > we) break;
                if (se >= ws) revealedAt[w] = s;
            }
        }

        Set<String> nonSpoilerSet = new HashSet<>();
        for(int w = 0; w < W; w++) {
            if (revealedAt[w] != -1) continue;
            nonSpoilerSet.add(wordTexts.get(w));
        }

        List<int[]> spoilerWords = new ArrayList<>();
        for (int w = 0; w < W; w++) {
            if (revealedAt[w] != -1) spoilerWords.add(new int[]{revealedAt[w], w});
        }
        spoilerWords.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        Set<String> revealedSpoilerSet = new HashSet<>();
        int answer = 0;
        for (int[] sw : spoilerWords) {
            String text = wordTexts.get(sw[1]);
            if (!nonSpoilerSet.contains(text) && !revealedSpoilerSet.contains(text)) answer++;
            revealedSpoilerSet.add(text);
        }

        return answer;
    }
}

/**
 * 2번쨰 풀이.
 *
 * 1개의 set으로 마무리 하는 법.
 * Stringbuilder 에서의 setCharAt을 활용한다.
 * setCharAt(int index, char c) -> 해당 index 위치에 대체 언어 삽입.
 * toString() -> 해당 StringBuilder을 string으로 전환
 * split(String regex) -> regex를 기준으로 String을 나눔.
 */

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        HashSet<String> wordSet = new HashSet<>();

        StringBuilder sb = new StringBuilder(message);
        for (int[] spoiler_range : spoiler_ranges) {
            int start = spoiler_range[0]; int end = spoiler_range[1];

            for (int r = start; r <= end; r++) {
                if (sb.charAt(r) == ' ') continue;
                sb.setCharAt(r, '*');
            }
        }

        for (String word : sb.toString().split(" ")) { wordSet.add(word); }

        for (String word : message.split(" ")){
            if (!wordSet.contains(word)) {
                answer++;
                wordSet.add(word);
            }
        }

        return answer;
    }
}