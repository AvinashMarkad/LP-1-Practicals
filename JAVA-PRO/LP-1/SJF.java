import java.util.*;

public class SJF {
    
    // Method to implement SJF Scheduling
    public static void sjfScheduling(int[] arrivalTimes, int[] burstTimes) {
        int n = arrivalTimes.length;

        // Arrays to store waiting times, turnaround times, and completion times
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];
        int[] completionTimes = new int[n];

        // Variable to calculate the total waiting time and turnaround time
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // List to store process indices for sorting
        Integer[] processOrder = new Integer[n];
        for (int i = 0; i < n; i++) {
            processOrder[i] = i;
        }

        // Sort processes based on burst time (Shortest Job First)
        Arrays.sort(processOrder, new Comparator<Integer>() {
            @Override
            public int compare(Integer p1, Integer p2) {
                return Integer.compare(burstTimes[p1], burstTimes[p2]);
            }
        });

        // First process completes after its burst time
        completionTimes[processOrder[0]] = arrivalTimes[processOrder[0]] + burstTimes[processOrder[0]];

        // Calculate completion times for all processes based on their arrival and burst times
        for (int i = 1; i < n; i++) {
            int currentProcess = processOrder[i];
            // Process starts after the previous process finishes
            completionTimes[currentProcess] = Math.max(arrivalTimes[currentProcess], completionTimes[processOrder[i - 1]]) + burstTimes[currentProcess];
        }

        // Calculate turnaround time (Completion time - Arrival time)
        for (int i = 0; i < n; i++) {
            turnaroundTimes[i] = completionTimes[i] - arrivalTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
        }

        // Calculate waiting time (Turnaround time - Burst time)
        for (int i = 0; i < n; i++) {
            waitingTimes[i] = turnaroundTimes[i] - burstTimes[i];
            totalWaitingTime += waitingTimes[i];
        }

        // Output the results
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + arrivalTimes[i] + "\t\t" + burstTimes[i] + "\t\t" + completionTimes[i] + "\t\t" + turnaroundTimes[i] + "\t\t" + waitingTimes[i]);
        }
        
        // Calculate and display average waiting time and average turnaround time
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;
        System.out.println("\nTotal Waiting Time: " + totalWaitingTime);
        System.out.println("Total Turnaround Time: " + totalTurnaroundTime);
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Sample data: Arrival Times and Burst Times
        int[] arrivalTimes = {0, 1, 2, 3};
        int[] burstTimes = {5, 3, 8, 6};
        
        // Call SJF Scheduling method
        sjfScheduling(arrivalTimes, burstTimes);
    }
}
