import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Salesman {
	int bettercost;
	int graph[][];
	int permutation[];
	List<Route> better_route;
	int number_of_cities;

	public Salesman(int number_of_cities){
		this.bettercost = 10000000;
		this.graph = new int[number_of_cities][number_of_cities];
		this.permutation = new int[number_of_cities];
		this.better_route = new ArrayList<Route>();
		this.number_of_cities = number_of_cities;
	}

	public void mountStructure(){
		int cost;
		Random random = new Random();
		for(int i = 0; i < number_of_cities; i++){
			for(int j = 0; j < number_of_cities; j++){
				cost = random.nextInt(30);
				graph[i][j] = (i < j) ? cost : (i == j) ? 0 : graph[j][i];
			}
		}
	}

	public void printGraph() {
		for(int i = 0; i < number_of_cities; i++){
			System.out.println();
			for(int j = 0; j < number_of_cities; j++){
				System.out.print(graph[i][j] + " ");
			}
		}
	}

	public void printBetterRoute() {
		for(int i = 0; i < better_route.size(); i ++) {
			String var = (i < (better_route.size() -1)) ? " - " : " ";
			System.out.print(better_route.get(i).fcity+1 + var);
		}
	}

	public void searchGreedyRoute(){
		int firstcity = 0, secondcity = 0, tcost = 0, lowercost, costTotal = 0;
		int n_cities = number_of_cities;
		List<Integer> already_added = new ArrayList<Integer>();
		already_added.add(0);
		Route route = new Route();
		tcost = 0;
		for(int i = 0; i < n_cities; i++ ){
			lowercost = 30;
			route.fcity = firstcity;
			if(i == better_route.size() - 1){
				lowercost = graph[firstcity][0];
				secondcity = 0;
			}
			else{
				for(int j = 0; j < n_cities; j++) {
					if(j != firstcity && ! already_added.contains(j)){
						int g_help = graph[firstcity][j];
						if(g_help != 0 && g_help < lowercost){
							lowercost = graph[firstcity][j];
							secondcity = j;
						}
					}
				}
			}
			route.scity = secondcity;
			route.cost = lowercost;
			already_added.add(secondcity);
			costTotal += lowercost;
			better_route.add(route);
			route = new Route();
			firstcity = secondcity;
		}
		route = new Route();
		int number_last = better_route.get(number_of_cities-1).fcity;
		route.fcity = 0;
		route.scity = number_last;
		route.cost = graph[0][number_last];
		better_route.add(route);
	}
}
