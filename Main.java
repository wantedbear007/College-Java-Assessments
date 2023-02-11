import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("helllom");

        ArrayList<String> numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("hello");
            String str = sc.nextLine();
            numbers.add(str);

        }

        Iterator<String> it = numbers.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}