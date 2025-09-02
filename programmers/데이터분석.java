import java.util.*;

class Solution {
    Map<String, Integer> map =  Map.of(
            "code", 0,
            "date", 1,
            "maximum", 2,
            "remain", 3
    );

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> filteredList = new ArrayList<>();
        int ext_index = map.get(ext);
        int sort_by_index = map.get(sort_by);

        for(int i = 0 ; i < data.length ; i++){
            if (data[i][ext_index] < val_ext) filteredList.add(data[i]);
        }

        filteredList.sort((a, b) -> Integer.compare(a[sort_by_index], b[sort_by_index]));

        int[][] answer = new int[filteredList.size()][];
        for(int i = 0 ; i < filteredList.size() ; i++){
            answer[i] = filteredList.get(i);
        }

        return answer;
    }
}