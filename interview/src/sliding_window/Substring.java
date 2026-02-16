package sliding_window;

public class Substring {
    public static void main(String[] args) {
        String input = "abcd";
        System.out.println("Input String: " + input);
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j < input.length(); j++) {
                cnt++;
                System.out.println(cnt +": " + input.substring(i,j+1));
            }
        }
    }
}
