import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A simplified web crawler.
 * In a real distributed system, this would be a multi-threaded application
 * running across many machines. This simulation demonstrates the core logic.
 */
public class WebCrawler {

    // A queue of URLs to visit.
    private Queue<String> urlQueue;
    // A set of URLs that have already been visited to avoid crawling the same page multiple times.
    private Set<String> visitedUrls;
    // The maximum number of pages to crawl.
    private static final int MAX_PAGES_TO_CRAWL = 100;

    public WebCrawler() {
        urlQueue = new LinkedList<>();
        visitedUrls = new HashSet<>();
    }

    /**
     * The main method to start the crawling process from a seed URL.
     * @param startUrl The initial URL to start crawling from.
     */
    public void crawl(String startUrl) {
        urlQueue.add(startUrl);
        visitedUrls.add(startUrl);

        System.out.println("Starting crawl from: " + startUrl);

        while (!urlQueue.isEmpty() && visitedUrls.size() < MAX_PAGES_TO_CRAWL) {
            String currentUrl = urlQueue.poll();
            System.out.println("Crawling: " + currentUrl);

            // In a real crawler, you would fetch the HTML of the page here.
            // For this simulation, we will generate some fake links.
            Set<String> newLinks = getLinksFromPage(currentUrl);

            for (String link : newLinks) {
                if (!visitedUrls.contains(link)) {
                    visitedUrls.add(link);
                    urlQueue.add(link);
                    System.out.println("  Found new link: " + link);
                }
            }
        }
        System.out.println("Crawl finished. Visited " + visitedUrls.size() + " unique pages.");
    }

    /**
     * Simulates fetching a web page and extracting all the links from it.
     * @param url The URL of the page to process.
     * @return A set of URLs found on the page.
     */
    private Set<String> getLinksFromPage(String url) {
        // This is a simulation. A real implementation would use a library like Jsoup to parse HTML.
        Set<String> links = new HashSet<>();
        links.add("http://example.com/page" + (int)(Math.random() * 100));
        links.add("http://example.com/page" + (int)(Math.random() * 100));
        links.add("http://example.com/another-site/page" + (int)(Math.random() * 50));
        // Add the original URL back to simulate internal links
        links.add(url); 
        return links;
    }

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        // We start with a "seed" URL.
        crawler.crawl("http://example.com/start");
    }
}
