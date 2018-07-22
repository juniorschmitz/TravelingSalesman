import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int bettercost = 100000;
		Scanner reader = new Scanner(System.in);
		int number_of_cities, cost;
		System.out.println("Type number of cities entry: ");
		number_of_cities = reader.nextInt();
		Salesman salesman = new Salesman(number_of_cities);
		System.out.println("Mounting structure...\n");
		salesman.mountStructure();
		System.out.println("Print graph: ");
		salesman.printGraph();
		System.out.println();
		System.out.println("Searching better route ");
		salesman.searchGreedyRoute();
		System.out.println("Print of the route: ");
		salesman.printBetterRoute();
	}
}
