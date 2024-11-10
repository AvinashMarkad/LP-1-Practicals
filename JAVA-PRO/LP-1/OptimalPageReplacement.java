import java.util.*;

public class OptimalPageReplacement {
    
    // Function to simulate Optimal Page Replacement
    public static void optimalPageReplacement(int[] pages, int numFrames) {
        // Array to store pages in memory (up to numFrames)
        int[] memory = new int[numFrames];
        Arrays.fill(memory, -1); // Initialize all memory slots with -1 (empty)
        
        // Set to track pages currently in memory
        Set<Integer> pageSet = new HashSet<>();
        
        // Counter for page faults
        int pageFaults = 0;

        // Iterate over all page requests
        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];

            // If the page is already in memory, continue
            if (pageSet.contains(currentPage)) {
                System.out.println("Page " + currentPage + " is already in memory.");
                continue;
            }

            // If there's space in memory, add the page
            if (pageSet.size() < numFrames) {
                memory[pageSet.size()] = currentPage;
                pageSet.add(currentPage);
            } else {
                // If no space in memory, perform Optimal replacement
                // Find the page that will not be used for the longest time
                int farthest = -1, pageToReplace = -1;
                for (int j = 0; j < numFrames; j++) {
                    int page = memory[j];
                    int nextUse = -1;
                    
                    // Find the next use of the page
                    for (int k = i + 1; k < pages.length; k++) {
                        if (pages[k] == page) {
                            nextUse = k;
                            break;
                        }
                    }

                    // If the page is not used in the future, it should be replaced
                    if (nextUse == -1) {
                        pageToReplace = page;
                        break;
                    }

                    // Track the page that will be used farthest in the future
                    if (nextUse > farthest) {
                        farthest = nextUse;
                        pageToReplace = page;
                    }
                }

                // Replace the page in memory
                pageSet.remove(pageToReplace); // Remove the page to be replaced
                for (int j = 0; j < numFrames; j++) {
                    if (memory[j] == pageToReplace) {
                        memory[j] = currentPage;
                        break;
                    }
                }
                pageSet.add(currentPage); // Add the new page to memory
            }

            // Increment page faults
            pageFaults++;
            System.out.println("Page fault: " + currentPage + " -> Memory: " + Arrays.toString(memory));
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

        // Call Optimal Page Replacement algorithm
        optimalPageReplacement(pages, numFrames);
    }
}
