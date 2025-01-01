import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int n, k;
    static final String number = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static List<ArrayList<BigInteger>> list_contribution;
    static List<String> dic;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        list_contribution = new ArrayList<>();
        dic = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list_contribution.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            dic.add(s);
            int length = s.length();
            for (char c : s.toCharArray()) {
                int index = number.indexOf(c);
                BigInteger contribution = BigInteger.valueOf(35 - index)
                        .multiply(BigInteger.valueOf(36).pow(length - 1));
                list_contribution.get(i).add(contribution);
                length--;
            }
        }
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        List<BigInteger> topContributions = sortContribution(list_contribution, k);
        System.out.println("Top contributions: " + topContributions);

        // Step 2: 대체할 문자 찾기
        Set<Character> toReplace = findCharToReplace(topContributions);
        System.out.println("Characters to replace with Z: " + toReplace);

        // Step 3: Z로 대체 후 총합 계산
        BigInteger total = calculateTotal(dic, toReplace);
        System.out.println("Total value in decimal: " + total);

        // Step 4: 36진법으로 변환 후 출력
        System.out.println("Total value in base 36: " + convertToBase36(total));
    }


    public static List<BigInteger> sortContribution(List<ArrayList<BigInteger>> c, int k) {
        List<BigInteger> allContributions = new ArrayList<>();
        for (ArrayList<BigInteger> contributions : c) {
            allContributions.addAll(contributions);
        }
        allContributions.sort(Collections.reverseOrder());
        return allContributions.subList(0, Math.min(k, allContributions.size()));
    }

    public static Set<Character> findCharToReplace(List<BigInteger> topContributions) {
        Set<Character> toReplace = new HashSet<>();
        for (BigInteger contribution : topContributions) {
            for (int i = 0; i < number.length(); i++) {
                BigInteger charValue = BigInteger.valueOf(35 - i); // 현재 문자 값
                for (ArrayList<BigInteger> contributions : list_contribution) {
                    if (contributions.contains(contribution)) {
                        if (contribution.equals(charValue.multiply(BigInteger.valueOf(36).pow(contributions.indexOf(contribution))))) {
                            toReplace.add(number.charAt(i));
                            break;
                        }
                    }
                }
            }
        }
        return toReplace;
    }




    public static BigInteger calculateTotal(List<String> dic, Set<Character> toReplace) {
        BigInteger total = BigInteger.ZERO;
        for (String s : dic) {
            BigInteger value = BigInteger.ZERO;
            int length = s.length();
            for (char c : s.toCharArray()) {
                int index = number.indexOf(c);
                int charValue = toReplace.contains(c) ? 35 : index;
                value = value.add(BigInteger.valueOf(charValue).multiply(BigInteger.valueOf(36).pow(length - 1)));
                length--;
            }
            total = total.add(value);
        }
        return total;
    }

    public static String convertToBase36(BigInteger value) {
        if (value.equals(BigInteger.ZERO)) return "0";
        StringBuilder sb = new StringBuilder();
        BigInteger base = BigInteger.valueOf(36);
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] result = value.divideAndRemainder(base);
            sb.append(number.charAt(result[1].intValue()));
            value = result[0];
        }
        return sb.reverse().toString();
    }

}
