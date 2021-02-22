import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tmp {
    public static void main(String[] args) throws IOException {

        System.out.println("8".matches("[0-8]")); // true
        System.out.println("3838".matches("[0-8]")); // false
        System.out.println("--------------------------------------------------");

        Pattern pattern = Pattern.compile("[0-6]");
//        Matcher matcher = pattern.matcher("6666666");
//        Matcher matcher = pattern.matcher("3838");
        Matcher matcher = pattern.matcher("9999");
        while (matcher.find()) {
            System.out.println(matcher.group() + " is.. " + matcher.start() +
                    "end at" + (matcher.end() - 1));
        }

//        oth2();
//        oth();
    }

    private static void oth2() {
        List<String> books = new ArrayList<>();
        Collections.addAll(books, "id, title, quantity, zzz");

        StringBuilder sb = new StringBuilder();
        for (String item : books) {
            sb.append(item).append(", ");
        }
        String s = sb.toString();
        int index = s.lastIndexOf(",");
        String substring = s.substring(0, index); // обрезаем в конце запятую
        System.out.println(substring);
    }

    private static void oth() throws IOException {
        Socket socket = new Socket("search.maven.org", 80);
        InputStream response = socket.getInputStream();
        OutputStream request = socket.getOutputStream();

        byte[] data = ("GET /solrsearch/select?q=quice&rows=22&wt=json HTTP/1.1\n" +
                "Host: search.maven.org\n\n").getBytes();
        request.write(data);

        for (int c = 0; c != -1; c = response.read())
            System.out.print((char) c);

        socket.close();
    }

    public int multiply(int a) {
        return a * 2;
    }
}
