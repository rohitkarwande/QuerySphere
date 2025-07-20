import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simplified search engine that uses the Indexer and a basic ranking algorithm.
 */
public class SearchEngine {

    private Indexer indexer;
    // A simple map to store the "importance" or PageRank of a URL.
    private Map<String, Double> pageRanks;

    public SearchEngine(Indexer indexer) {
        this.indexer = indexer;
        this.pageRanks = new HashMap<>();
        // Simulate pre-calculated PageRank scores.
        simulatePageRanks();
    }
    
    private void simulatePageRanks() {
        // In a real system, these scores would be the output of a complex PageRank algorithm.
        pageRanks.put("http://example.com/page1", 0.85);
        pageRanks.put("http://example.com/page2", 0.70);
        pageRanks.put("http://example.com/page3", 0.95); // page3 is most "important"
    }

    /**
     * Performs a search for a query and returns ranked results.
     * @param query The search term.
     */
    public void search(String query) {
        System.out.println("\nSearching for: \"" + query + "\"");
        List<String> results = indexer.getDocumentsForKeyword(query);

        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        // --- Ranking ---
        // A simple ranking algorithm: sort results by their pre-calculated PageRank score.
        // A real system would use a much more complex ranking function (e.g., TF-IDF, BM25).
        results.sort((url1, url2) -> {
            double rank1 = pageRanks.getOrDefault(url1, 0.0);
            double rank2 = pageRanks.getOrDefault(url2, 0.0);
            return Double.compare(rank2, rank1); // Sort in descending order of rank
        });

        System.out.println("--- Search Results ---");
        for (String url : results) {
            System.out.printf(" - %s (Rank: %.2f)\n", url, pageRanks.getOrDefault(url, 0.0));
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        // 1. Create an indexer and index some documents.
        Indexer myIndexer = new Indexer();
        myIndexer.indexPage("http://example.com/page1", "Java is a powerful programming language for distributed systems.");
        myIndexer.indexPage("http://example.com/page2", "Python is great for data science and machine learning.");
        myIndexer.indexPage("http://example.com/page3", "Large scale systems often use Java or C++.");
        
        // 2. Create the search engine with the populated index.
        SearchEngine engine = new SearchEngine(myIndexer);

        // 3. Perform searches.
        engine.search("java");
        engine.search("systems");
        engine.search("python");
        engine.search("golang"); // A keyword not in the index
    }
}
