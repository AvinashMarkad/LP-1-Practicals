import java.util.*;

public class LRUPageReplacement {
    
    // Function to simulate Least Recently Used (LRU) Page Replacement
    public static void lruPageReplacement(int[] pages, int numFrames) {
        // A LinkedHashMap is used because it maintains the order of access
        LinkedHashMap<Integer, Integer> memory = new LinkedHashMap<>(numFrames, 0.75f, true);
        int pageFaults = 0;

        // Iterate over all page requests
        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];

            // If the page is already in memory, no page fault occurs
            if (!memory.containsKey(currentPage)) {
                // If there is space in memory, just add the page
                if (memory.size() < numFrames) {
                    memory.put(currentPage, i);
                } else {
                    // If memory is full, remove the least recently used page
                    Iterator<Map.Entry<Integer, Integer>> iterator = memory.entrySet().iterator();
                    iterator.next(); // Move to the first entry (least recently used)
                    iterator.remove(); // Remove the LRU page
                    memory.put(currentPage, i); // Add the new page
                }
                // Increment page faults count
                pageFaults++;
                System.out.println("Page fault: " + currentPage + " -> Memory: " + memory.keySet());
            } else {
                System.out.println("Page " + currentPage + " is already in memory.");
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

        // Call LRU Page Replacement algorithm
        lruPageReplacement(pages, numFrames);
    }
}
