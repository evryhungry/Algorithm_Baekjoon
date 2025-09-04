import java.util.*;

/**
 * 순차 탐색(indexOf) 기반 구현.
 *
 * <p><b>알고리즘 개요</b><br>
 * - players를 List로 변환하고,<br>
 * - callings의 각 이름에 대해 매번 list.indexOf(name)로 현재 인덱스를 찾은 뒤,<br>
 *   바로 앞 선수와 자리만 교환합니다.
 *
 * <p><b>시간 복잡도</b><br>
 * - 초기 변환: Arrays.asList(players) → O(n)<br>
 * - 각 호출 처리: list.indexOf(name) = O(n), set 2회 = O(1) ⇒ 호출 1회당 O(n)<br>
 * - 전체: O(n) + O(m·n) = <b>O(n·m)</b><br>
 *   (n = players 길이, m = callings 길이)
 *
 * <p><b>공간 복잡도</b><br>
 * - players를 감싸는 고정 크기 리스트 + answer 배열: <b>O(n)</b>
 *
 * <p><b>장단점/주의</b><br>
 * - 구현이 단순하나, m이 크면 indexOf가 병목이 되어 시간 초과 가능성이 높습니다.<br>
 * - 고정 크기 리스트라 add/remove 불가(UnsupportedOperationException).
 */
class SolutionIndexOf {
    public String[] solution(String[] players, String[] callings) {
        List<String> list_players = new ArrayList<>(Arrays.asList(players));
        String[] answer = new String[list_players.size()];

        for (String calling : callings) {
            int index = list_players.indexOf(calling); // O(n)
            String temp = list_players.get(index - 1);
            list_players.set(index - 1, calling);
            list_players.set(index, temp);
        }

        for (int i = 0 ; i < list_players.size(); i++){
            answer[i] = list_players.get(i);
        }

        return answer;
    }
}

/**
 * 해시맵(이름 → 인덱스) 기반 구현.
 *
 * <p><b>알고리즘 개요</b><br>
 * - order 배열에 현재 순서를 유지하고,<br>
 * - 해시맵 pos에 (이름 → 현재 인덱스)를 저장합니다.<br>
 * - 호출될 때마다 pos에서 O(1)에 인덱스를 얻어, 바로 앞 선수와 swap 후 두 선수의 인덱스를 pos에 갱신합니다.
 *
 * <p><b>시간 복잡도</b><br>
 * - 초기 맵 구축: O(n)<br>
 * - 각 호출 처리: 조회/갱신 모두 평균 O(1) → 호출 1회당 O(1)<br>
 * - 전체: <b>O(n + m)</b>
 *
 * <p><b>공간 복잡도</b><br>
 * - order 배열 + 해시맵(pos): <b>O(n)</b>
 *
 * <p><b>장단점/주의</b><br>
 * - 매우 빠른 평균 시간(상수 시간 스왑)으로 대용량 m에서도 안정적입니다.<br>
 * - 해시맵 추가 비용이 있지만 O(n)으로 작고, 실무/코테 모두에서 권장되는 접근입니다.<br>
 * - 해시 충돌이 심한 최악의 경우 이론상 O(m·n)까지 치솟을 수 있으나, 보통의 자바 구현에서는 평균 O(1)로 동작합니다.
 */
class SolutionHashMap {
    public String[] solution(String[] players, String[] callings) {
        String[] order = Arrays.copyOf(players, players.length); // O(n)
        Map<String, Integer> pos = new HashMap<>(order.length * 2);
        for (int i = 0; i < order.length; i++) {
            pos.put(order[i], i); // O(1) 평균, 전체 O(n)
        }

        for (String name : callings) {
            int i = pos.get(name); // O(1) 평균
            if (i == 0) continue;  // 선두면 스킵

            // 앞사람과 스왑
            String ahead = order[i - 1];
            order[i - 1] = order[i];
            order[i] = ahead;

            // 인덱스 갱신
            pos.put(name, i - 1);
            pos.put(ahead, i);
        }

        return order;
    }
}