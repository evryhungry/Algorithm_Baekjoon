import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();

    void init(){
        int next = 1;
        for (char i = 'A' ; i <= 'Z' ; i++ ){
            map.put(String.valueOf(i), next);
            next++;
        }
    }

    public int[] solution(String msg) {
        init();

        int i = 0;
        List<Integer> output = new ArrayList<>();

        while ( i < msg.length() ){
            String w = "";
            int j = i + 1;

            while (j <= msg.length()
                    && map.containsKey(msg.substring(i, j))) { j++; }

            String last = msg.substring(i, j - 1);
            output.add(map.get(last));
            if (j <= msg.length()) { map.put(msg.substring(i, j), map.size() + 1); }
            i += last.length();
        }

        int[] answer = new int[output.size()];

        for(int k = 0 ; k < answer.length ; k++) answer[k] = output.get(k);
        return answer;
    }
}