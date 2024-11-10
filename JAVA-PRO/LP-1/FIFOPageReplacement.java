import java.util.*;

public class FIFOPageReplacement {
    
    // Function to simulate FIFO Page Replacement
    public static void fifoPageReplacement(int[] pages, int numFrames) {
        // Initialize a queue to store pages in memory (FIFO order)
        Queue<Integer> memory = new LinkedList<>();
        // Set to keep track of pages currently in memory
        Set<Integer> pageSet = new HashSet<>();
        // Counter for page faults
        int pageFaults = 0;

        // Iterate over all page requests
        for (int page : pages) {
            // If the page is not in memory, it's a page fault
            if (!pageSet.contains(page)) {
                // If there is space in memory, just add the page
                if (memory.size() < numFrames) {
                    memory.add(page);
                    pageSet.add(page);
                } else {
                    // Remove the page that has been in memory the longest (FIFO)
                    int oldestPage = memory.poll();
                    pageSet.remove(oldestPage);

                    // Add the new page to the memory
                    memory.add(page);
                    pageSet.add(page);
                }
                // Increment page faults
                pageFaults++;
                System.out.println("Page fault: " + page + " -> Memory: " + memory);
            } else {
                System.out.println("Page " + page + " is already in memory.");
            }
        }
        // Display the total number of page faults
        System.out.println("\nTotal page faults: " + pageFaults);
    }

    // Main method to run the simulation
    public static void main(String[] args) {
        // Sample page reference string
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
        
        // Number of frames in memory
        int numFrames = 3;
        
        // Call FIFO Page Replacement algorithm
        fifoPageReplacement(pages, numFrames);
    }
}
