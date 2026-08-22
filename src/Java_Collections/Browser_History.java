package Java_Collections;

import java.util.LinkedList;

public class Browser_History {

    static class History {

        int currentIndex = -1;
        private LinkedList<String> history;

        public History() {
            history = new LinkedList<>();
        }

        public void visit(String url) {
            while (history.size() > currentIndex + 1) {
                history.removeLast();
            }
            history.addLast(url);
            currentIndex = history.size() - 1;

            System.out.println("visiting " + url);
        }

        public void back() {
            if (currentIndex > 0) {
                currentIndex--;
                System.out.println("backing to " + history.get(currentIndex));
            } else {
                System.out.println("Cannot go back. You are at the first page.");
            }
        }

        public void forward() {

            if (currentIndex < history.size() - 1) {
                currentIndex++;
                System.out.println("forwarding to " + history.get(currentIndex));
            } else {
                System.out.println("Cannot go forward. You are at the newest page.");
            }
        }
    }

    public static void main(String[] args) {
        History browserHistory = new History();

        browserHistory.visit("http://google.com");
        browserHistory.visit("http://google1.com");
        browserHistory.visit("http://google2.com");

        browserHistory.back();
        browserHistory.forward();

        browserHistory.back();
        browserHistory.visit("http://youtube.com");

        browserHistory.forward();
    }
}
