import java.io.*;
import java.util.Arrays;

public class Umut_Savk_2017510095 {
	private static int[] demand;
	private static int[] garageCost;
	private static int[][] investment;
	private static double[][] invested;

	
	public static int greedy(int x, int p,int d) throws Exception {

		readDemandFunc("month_demand.txt", x);
		readGarageFunc("garage_cost.txt", x);
		int now = 0;
		for (int i = 1; i <= x; i++) {

			if (demand[i] > p) {
				now += (demand[i]-p)*d;
			}
		}
		return now;
	}
	
	public static int DP(int x, int p,int d) throws Exception {

		readDemandFunc("month_demand.txt", x);
		readGarageFunc("garage_cost.txt", x);
		int hire = 0, garage = 0,now =0,lastMonthRemain=0;;
		for (int i = 1; i <= x; i++) {

			if (demand[i] > p) {
				if (i==1) {
					garage = garageCost[demand[i]-p];
					hire = (demand[i]-p)*d;
					if (garage<hire) {
						now = garage;
					}
					else 
						now = hire;
				}
				else {
					if (demand[i-1]<p) {
						lastMonthRemain= p-demand[i-1];
						if (demand[i]-p-lastMonthRemain<=0) {
							garage = garageCost[demand[i]-p]+now;
							hire = (demand[i]-p)*d+now;
						}
						else {
							garage = garageCost[demand[i]-p]+(demand[i]-p-lastMonthRemain)*d+now;
							hire = (demand[i]-p)*d+now;
						}
						
					}
					else {
						garage = garageCost[demand[i]-p]+(demand[i]-p)*d+now;
						hire = (demand[i]-p)*d+now;
					}
					if (garage<hire) {
						now = garage;
					}
					else 
						now = hire;
				}
			}
		}
		return now;

	}

	public static double greedy2(int x, int c, int t, int b) throws Exception {
		readDemandFunc("month_demand.txt", x);
		readInvestmentFunc("investment.txt", x, c);
		int now = 0;// company id
		int nowT = 0;
		double money = 0;
		double tempMoney = 0;
		double max = 0;
		double temp = 0;
		
		for (int j = 1; j <= c; j++) {
			if (max < investment[1][j]) {
				max = investment[1][j];
				now = j;
			
			}
		}
		money += incomeOfMonth(1, b)* (max+100)/100;
//		System.out.println("now="+ now + " money= "+ money);
		
		for (int i = 2; i <= x; i++) {
			
				max = 0;
			
			tempMoney= money;
//			income = incomeOfMonth(i, b);
			money += incomeOfMonth(i, b);
			for (int j = 1; j <= c; j++) {
				if (j == now) {
					temp = money * (investment[i][j] + 100) / 100;
					if (max < temp) {
						max = temp;
						nowT=j;
					}
				} else {
					money = (tempMoney * (100 - t) / 100) ;
					money += incomeOfMonth(i, b);
					temp = money * (investment[i][j] + 100) / 100;
					if (max < temp) {
						max = temp;
						nowT = j;
					}
				}
			}
			money = max;
			now = nowT;
//			System.out.println(money);
		}
//		System.out.println("remain="+ incomeOfMonth(x, b) / 2 + " money= "+ money);
		return money + demand[x]*b / 2;
	}

	public static double DPPart2(int x, int c, int t, int b) throws Exception {
		double max = 0;
		double temp = 0;
		invested = new double[x + 1][c + 1];
		readDemandFunc("month_demand.txt", x);
		readInvestmentFunc("investment.txt", x, c);
		for (int i = 1; i <= c; i++) {
			invested[0][i] = 0;
		}
		for (int i = 1; i <= x; i++) {
			for (int j = 1; j <= c; j++) {
				max = 0;
				for (int j2 = 1; j2 <= c; j2++) {
					if (j == j2) {
						temp = ((invested[i - 1][j2] + incomeOfMonth(i, b)) * (100 + investment[i][j])) / 100;
					} else {
						temp = (invested[i - 1][j2] - (invested[i - 1][j2] * t) / 100 + incomeOfMonth(i, b))
								* (100 + investment[i][j]) / 100;

					}

					if (max < temp) {
						max = temp;
					}
				}
				invested[i][j] = max;
			}
		}
		max = 0;
		for (int i = 1; i <= c; i++) {
			invested[x][i] = invested[x][i] + demand[x] * b / 2;
			if (max < invested[x][i]) {
				max = invested[x][i];
			}
		}
		return max;
	}

	public static void readDemandFunc(String name, int x) throws Exception {

		File file = new File(name);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		String[] arr;
		demand = new int[x + 1];
		int i = 0;
		while ((st = br.readLine()) != null && i <= x) {
			if (i != 0) {
				arr = st.split("\t");
				demand[Integer.parseInt(arr[0])] = Integer.parseInt(arr[1]);
			} else {
				demand[0] = 0;
			}
			i++;
		}

	}

	public static void readGarageFunc(String name, int x) throws Exception {

		File file = new File(name);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		String[] arr;
		garageCost = new int[x + 1];
		int i = 0;
		while ((st = br.readLine()) != null && i <= x) {
			if (i != 0) {
				arr = st.split("\t");
				garageCost[Integer.parseInt(arr[0])] = Integer.parseInt(arr[1]);
			} else {
				garageCost[0] = 0;
			}
			i++;
		}

	}

	public static void readInvestmentFunc(String name, int x, int c) throws Exception {

		File file = new File(name);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		String[] arr;
		investment = new int[x + 1][c + 1];
		int i = 0;
		while ((st = br.readLine()) != null && i <= x) {
			if (i != 0) {
				arr = st.split("\t");
				for (int j = 1; j < c + 1; j++) {
					investment[Integer.parseInt(arr[0])][j] = Integer.parseInt(arr[j]);

				}
			} else {
				for (int j = 0; j < c + 1; j++) {

					investment[0][j] = 0;
				}
			}
			i++;
		}
	}

	static double incomeOfMonth(int month, int b) {
		return (demand[month] * b) / 2 + (demand[month - 1] * b) / 2;
	}

	public static void main(String[] args) throws Exception {
//		int x = 3, p = 5, d = 5, B = 100, c = 3, t = 2;
//		int p = 6, d = 6, x = 5, t = 2, B = 100, c = 6;
//		int p = 7, d=5, x= 20, t = 1, B=50, c = 4;
//		int p = 6, d=5, x= 45, t = 1, B=75, c = 6;
//		int p = 5, d=5, x= 30, t = 3, B=150, c = 5;
		int p = 2, d=2, x= 25, t = 2, B=100, c = 6;
		// read files...
		System.out.println("DP Results --> Profit : "+ DPPart2(x, c, t, B) + " Cost : " + DP( x,  p, d) );
		System.out.println("Greedy Results --> Profit : "+ greedy2(x, c,t,B) + " Cost : " + greedy( x,  p, d) );
		
//		System.out.println(0/2);
//		readDemandFunc("month_demand.txt", x);
//		readGarageFunc("garage_cost.txt", x);
//		readInvestmentFunc("investment.txt", x, c);
//		System.out.println("DP Results:" + ( Profit - Cost ) );
//		System.out.println("Greedy Results:" + ( Profit - Cost ) );
		// If you can’t find one of Profit or Cost, print it like below:
		// System.out.println(“DP Results-Profit: ” + ( Profit) );
		// System.out.println(“Greedy Results-Cost:” + ( Cost ) );
	}
}
