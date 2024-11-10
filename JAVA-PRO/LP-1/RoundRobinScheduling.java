import java.util.*;

class Process {
    int id;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class RoundRobinScheduling {

    // Method to calculate waiting time and turnaround time using Round Robin
    public static void calculateTimes(List<Process> processes, int quantum) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int currentTime = 0;

        // Round Robin scheduling
        Queue<Process> queue = new LinkedList<>();
        queue.addAll(processes);

        while (!queue.isEmpty()) {
            Process current = queue.poll();

            // If the remaining time is less than or equal to quantum, the process finishes
            if (current.remainingTime <= quantum) {
                currentTime += current.remainingTime;
                current.waitingTime = currentTime - current.burstTime;
                current.turnaroundTime = current.waitingTime + current.burstTime;
                totalWaitingTime += current.waitingTime;
                totalTurnaroundTime += current.turnaroundTime;
                current.remainingTime = 0;
            } else {
                // If the process needs more time, it goes back in the queue
                currentTime += quantum;
                current.remainingTime -= quantum;
                queue.add(current);
            }
        }

        // Print the results for each process
        System.out.println("Process ID | Burst Time | Waiting Time | Turnaround Time");
        System.out.println("-----------------------------------------------------------");
        for (Process p : processes) {
            System.out.printf("%-11d%-12d%-15d%-17d\n", p.id, p.burstTime, p.waitingTime, p.turnaroundTime);
        }

        // Print total waiting and turnaround times
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
            processes.add(new Process(i + 1, burstTime)); // Process ID is i + 1
        }

        System.out.print("Enter Time Quantum: ");
        int quantum = scanner.nextInt();

        // Calculate and print waiting time, turnaround time, and averages
        calculateTimes(processes, quantum);

        scanner.close(); // Close the scanner
    }
}
