class Solution {
    String init(String s) {
        return s.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "C")
                .replace("E#", "F");
    }

    int toMinutes(String hhmm) {
        int hh = Integer.parseInt(hhmm.substring(0, 2));
        int mm = Integer.parseInt(hhmm.substring(3, 5));
        return hh * 60 + mm;
    }

    String buildPlayedScore(String score, int playTime) {
        int len = score.length();
        if (len == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playTime; i++) {
            sb.append(score.charAt(i % len));
        }
        return sb.toString();
    }

    public String solution(String m, String[] musicinfos) {
        String target = init(m);

        String answer = "(None)";
        int best = -1;

        for (int i = 0; i < musicinfos.length; i++) {
            String[] parts = musicinfos[i].split(",");
            int start = toMinutes(parts[0]);
            int end = toMinutes(parts[1]);
            String title = parts[2];
            String score = init(parts[3]);

            int playTime = end - start;
            String played = buildPlayedScore(score, playTime);

            if (played.contains(target)) {
                if (playTime > best) {
                    best = playTime;
                    answer = title;
                }
            }
        }

        return answer;
    }
}