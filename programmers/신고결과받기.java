import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> warned_user = new HashMap<>();
        HashMap<String, List<String>> warning = new HashMap<>();

        for (int i = 0 ; i < id_list.length ; i++){
            warned_user.put(id_list[i], 0);
            warning.put(id_list[i], new ArrayList<>());
        }

        for (String s : report){
            String[] s_list = s.split(" ");
            List<String> wu = new ArrayList<>(warning.get(s_list[0]));

            if (wu.contains(s_list[1])) continue;

            wu.add(s_list[1]);
            warned_user.put(s_list[1], warned_user.get(s_list[1]) + 1);
            warning.put(s_list[0], wu);
        }

        for (int i = 0 ; i < id_list.length ; i++){
            List<String> wu = new ArrayList<>(warning.get(id_list[i]));
            int count = 0;

            for (int j = 0 ; j < wu.size() ; j++){
                if(warned_user.get(wu.get(j)) >= k) count++;
            }

            answer[i] = count;
        }

        return answer;
    }
}