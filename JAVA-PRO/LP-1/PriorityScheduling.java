import java.util.*;

class Process {
    int id;
    int burstTime;
    int waitingTime;
    int turnaroundTime;
    int priority;

    // Constructor to initialize the Process
    public Process(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0; // default waiting time
        this.turnaroundTime = 0; // default turnaround time
    }
}

public class PriorityScheduling {

    // Method to sort the processes based on priority (ascending order)
    public static void sortProcessesByPriority(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.priority));
    }

    // Method to calculate waiting time and turnaround time
    public static void calculateTimes(List<Process> processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int currentTime = 0;

        // Calculate Waiting Time and Turnaround Time for each process
        for (Process p : processes) {
            p.waitingTime = currentTime; // Waiting time is the time elapsed before process starts
            p.turnaroundTime = p.waitingTime + p.burstTime; // Turnaround time includes waiting time and burst time

            // Update the total waiting and turnaround time
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;

            // Update current time to the end of this process
            currentTime += p.burstTime;
        }

        // Print results
        System.out.println("Process ID | Burst Time | Priority | Waiting Time | Turnaround Time");
        System.out.println("---------------------------------------------------------------");
        for (Process p : processes) {
            System.out.printf("%-11d%-12d%-10d%-15d%-17d\n", p.id, p.burstTime, p.priority, p.waitingTime, p.turnaroundTime);
        }

        System.out.println("\nTotal Waiting Time: " + totalWaitingTime);
        System.out.println("Total Turnaround Time: " + totalTurnaroundTime);
        System.out.println("Average Waiting Time: " + (float) totalWaitingTime / processes.size());
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / processes.size());
    }

    public static void main(String[] args) {
        // List to store the processes
        List<Process> processes = new ArrayList<>();

        // Scanner to take input from the user
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        // Take input for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Burst Time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter Priority for Process " + (i + 1) + ": ");
            int priority = scanner.nextInt();
            processes.add(new Process(i + 1, burstTime, priority)); // Process ID is i + 1
        }

        // Sort processes by priority
        sortProcessesByPriority(processes);

        // Calculate and print waiting time, turnaround time, and averages
        calculateTimes(processes);

        scanner.close(); // Close the scanner
    }
}
