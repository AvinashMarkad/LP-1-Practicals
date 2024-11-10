package FCFS;

import java.util.*;

public class FCFS {
    
    // Method to implement FCFS Scheduling
    public static void fcfsScheduling(int[] arrivalTimes, int[] burstTimes) {
        int n = arrivalTimes.length;
        
        // Calculate waiting time and turnaround time
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];
        
        int[] completionTimes = new int[n];
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // Completion time for the first process is its burst time + arrival time
        completionTimes[0] = arrivalTimes[0] + burstTimes[0];
        
        // Calculate completion time for each process
        for (int i = 1; i < n; i++) {
            completionTimes[i] = Math.max(arrivalTimes[i], completionTimes[i - 1]) + burstTimes[i];
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
        
        // Call FCFS Scheduling method
        fcfsScheduling(arrivalTimes, burstTimes);
    }
}

