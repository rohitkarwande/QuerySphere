import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates an inverted index from web page content.
 * The inverted index maps keywords (tokens) to a list of documents (URLs)
 * where the keyword appears. This is the core data structure for efficient searching.
 */
public class Indexer {

    // The inverted index data structure.
    // Key: A word (String)
    // Value: A list of documents (URLs) containing that word.
    private Map<String, List<String>> invertedIndex;

    public Indexer() {
        invertedIndex = new HashMap<>();
    }

    /**
     * Adds a document and its content to the index.
     * @param url The URL of the document.
     * @param content The text content of the document.
     */
    public void indexPage(String url, String content) {
        // Simple tokenization: split by spaces and convert to lower case.
        String[] tokens = content.toLowerCase().split("\\s+");

        for (String token : tokens) {
            // If the token is not in the index, add it with a new list.
            invertedIndex.putIfAbsent(token, new ArrayList<>());
            
            // Add the current URL to the list for this token, if not already present.
            if (!invertedIndex.get(token).contains(url)) {
                invertedIndex.get(token).add(url);
            }
        }
    }

    /**
     * Retrieves the list of documents that contain a specific keyword.
     * @param keyword The word to search for.
     * @return A list of URLs, or an empty list if the keyword is not found.
     */
    public List<String> getDocumentsForKeyword(String keyword) {
        return invertedIndex.getOrDefault(keyword.toLowerCase(), new ArrayList<>());
    }

    public void printIndex() {
        System.out.println("--- Inverted Index ---");
        for (Map.Entry<String, List<String>> entry : invertedIndex.entrySet()) {
            System.out.println("'" + entry.getKey() + "' -> " + entry.getValue());
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        Indexer indexer = new Indexer();

        // Simulate indexing a few pages.
        indexer.indexPage("[http://example.com/page1](http://example.com/page1)", "Java is a powerful programming language for distributed systems.");
        indexer.indexPage("[http://example.com/page2](http://example.com/page2)", "Python is great for data science and machine learning.");
        indexer.indexPage("[http://example.com/page3](http://example.com/page3)", "Large scale systems often use Java or C++.");

        indexer.printIndex();
    }
}
