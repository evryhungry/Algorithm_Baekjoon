import java.util.Map;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> user = new HashMap<>();
        
        for (String r : record){
            String[] s = r.split(" ");
            
            if (!s[0].equals("Leave")){
                user.put(s[1], s[2]);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < record.length ; i++){
            String[] s = record[i].split(" ");
            
            if (s[0].equals("Enter")){
                sb.append(user.get(s[1]) + "님이 들어왔습니다.~");
            } else if (s[0].equals("Leave")) {
                sb.append(user.get(s[1]) + "님이 나갔습니다.~");
            }
        }
        
        return sb.toString().split("~");
    }
}