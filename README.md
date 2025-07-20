# QuerySphere: A Conceptual Distributed Search Engine

QuerySphere is a Java-based project that demonstrates the fundamental components of a modern search engine. It was created to showcase an understanding of data structures, algorithms, and the architectural principles behind large-scale, distributed systems like Google Search.

This project is not a fully functional search engine but a simplified model of its core parts:
1.  **Web Crawler:** A component responsible for discovering and fetching web pages.
2.  **Indexer:** A component that processes the content of web pages and builds an efficient data structure (an inverted index) for fast lookups.
3.  **Search & Ranker:** A component that takes a user query, finds relevant documents using the index, and ranks them according to importance.

## Core Concepts Demonstrated

* **Data Structures:** The project heavily utilizes fundamental data structures.
    * `HashMap` is used to implement the **inverted index**, which is the backbone of the search functionality.
    * `Queue` (as a `LinkedList`) is used in the web crawler to manage the list of URLs to visit (a Breadth-First Search approach).
    * `HashSet` is used to keep track of visited URLs efficiently, preventing the crawler from getting into infinite loops.

* **Algorithms:**
    * **Web Crawling:** The crawler implements a simplified version of a graph traversal algorithm to discover new pages.
    * **PageRank (Simulated):** The search engine includes a simulated PageRank score for each page. This demonstrates an understanding that not all pages are equally important and that link analysis is a core part of ranking search results. The results are sorted based on this rank.

* **System Design:**
    * **Modularity:** The project is broken down into distinct classes (`WebCrawler`, `Indexer`, `SearchEngine`), each with a clear responsibility. This mirrors the microservices architecture used in many large-scale systems.
    * **Scalability:** The comments and design allude to how this system would be scaled in the real world (e.g., using multi-threading, distributing the index, and running crawlers on multiple machines).

## Tech Stack
* **Core Language:** Java
* **Key Libraries:** Standard Java Collections Framework (HashMap, HashSet, Queue). No external libraries are needed to keep the focus on the core logic.

## How to Run
This project is designed to be run from the command line to demonstrate the logic of each component.

1.  Clone the repository and navigate into the `src` directory.
2.  Compile the Java files:
    ```bash
    javac *.java
    ```
3.  Run each component to see its output:
    ```bash
    # Run the web crawler simulation
    java WebCrawler

    # Run the indexer simulation
    java Indexer

    # Run the search engine simulation
    java SearchEngine
    ```
