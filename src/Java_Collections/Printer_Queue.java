package Java_Collections;

import java.util.LinkedList;
import java.util.Queue;

public class Printer_Queue {

    public static class Document {
        private String name;
        private int pages;

        public Document(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }

        public String getName() {
            return name;
        }

        public int getPages() {
            return pages;
        }

        @Override
        public String toString() {
            return "'" + name + "' (" + pages + " pages)";
        }
    }

    public static class PrintQueue {
        private Queue<Document> queue;

        public PrintQueue() {
            this.queue = new LinkedList<Document>();
        }

        public void addDocument(Document document) {
            queue.add(document);
            System.out.println("Added to queue: " + document);
        }

        public void printNext() {
            if (queue.isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            Document nextDoc = queue.poll();
            System.out.println("Printing: " + nextDoc);
        }

        public void showQueue() {
            if (queue.isEmpty()) {
                System.out.println("queue is empty");
                return;
            }
            System.out.println("Current queue:");
            int position = 1;
            for (Document doc : queue) {
                System.out.println("  " + position + ". " + doc);
                position++;
            }
        }
    }

    public static void main(String[] args) {
        PrintQueue printer = new PrintQueue();

        printer.addDocument(new Document("File.pdf", 15));
        printer.addDocument(new Document("Presentation.pptx", 5));
        printer.addDocument(new Document("Project.docx", 124));

        System.out.println();
        printer.showQueue();

        System.out.println();
        printer.printNext();

        System.out.println();
        printer.showQueue();

        System.out.println();
        printer.printNext();
        printer.printNext();
        printer.printNext();
    }
}
