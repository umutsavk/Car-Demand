import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Umut_Savk_2017510095 {

	public static final String[][] blosum = new String[][]
			{
		{" ", "A",  "R",  "N",  "D",  "C",  "Q",  "E",  "G",  "H",  "I",  "L",  "K",  "M",  "F",  "P",  "S",  "T",  "W",  "Y",  "V",  "B",  "Z",  "X",  "*"}, 
		{"A", "4", "-1", "-2", "-2",  "0", "-1", "-1",  "0", "-2", "-1", "-1", "-1", "-1", "-2", "-1",  "1",  "0", "-3", "-2",  "0", "-2", "-1", "0", "-4"}, 
		{"R",  "-1",  "5", "0", "-2", "-3",  "1",  "0", "-2",  "0", "-3", "-2",  "2", "-1", "-3", "-2", "-1", "-1", "-3", "-2", "-3", "-1",  "0", "-1", "-4"}, 
		{"N",  "-2",  "0",  "6",  "1", "-3",  "0",  "0",  "0",  "1", "-3", "-3",  "0", "-2", "-3", "-2",  "1",  "0", "-4", "-2", "-3",  "3",  "0", "-1", "-4"}, 
		{"D","-2", "-2",  "1",  "6", "-3",  "0",  "2", "-1", "-1", "-3", "-4", "-1", "-3", "-3", "-1",  "0", "-1", "-4", "-3", "-3",  "4",  "1", "-1", "-4"}, 
		{ "C",  "0", "-3", "-3", "-3",  "9", "-3", "-4", "-3", "-3", "-1", "-1", "-3", "-1", "-2", "-3", "-1", "-1", "-2", "-2", "-1", "-3", "-3", "-2", "-4"}, 
		{"Q", "-1",  "1",  "0",  "0", "-3",  "5",  "2", "-2",  "0", "-3", "-2",  "1",  "0", "-3", "-1",  "0", "-1", "-2", "-1", "-2",  "0",  "3", "-1", "-4"}, 
		{"E",  "-1",  "0",  "0",  "2", "-4",  "2",  "5", "-2",  "0", "-3", "-3",  "1", "-2", "-3", "-1",  "0", "-1", "-3", "-2", "-2",  "1",  "4", "-1", "-4"}, 
		{"G", "0", "-2",  "0", "-1", "-3", "-2", "-2",  "6", "-2", "-4", "-4", "-2", "-3", "-3", "-2",  "0", "-2", "-2", "-3", "-3", "-1", "-2", "-1", "-4"}, 
		{"H",   "-2",  "0",  "1", "-1", "-3",  "0",  "0", "-2",  "8", "-3", "-3", "-1", "-2", "-1", "-2", "-1", "-2", "-2",  "2", "-3",  "0",  "0", "-1", "-4"}, 
		{"I",   "-1", "-3", "-3", "-3", "-1", "-3", "-3", "-4", "-3",  "4",  "2", "-3",  "1",  "0", "-3", "-2", "-1", "-3", "-1",  "3", "-3", "-3", "-1", "-4"}, 
		{ "L", "-1", "-2", "-3", "-4", "-1", "-2", "-3", "-4", "-3",  "2",  "4", "-2",  "2",  "0", "-3", "-2", "-1", "-2", "-1",  "1", "-4", "-3", "-1", "-4"}, 
		{"K", "-1",  "2",  "0", "-1", "-3",  "1",  "1", "-2", "-1", "-3", "-2",  "5", "-1", "-3", "-1",  "0", "-1", "-3", "-2", "-2",  "0",  "1", "-1", "-4"}, 
		{ "M",  "-1", "-1", "-2", "-3", "-1",  "0", "-2", "-3", "-2",  "1",  "2", "-1",  "5",  "0", "-2", "-1", "-1", "-1", "-1",  "1", "-3", "-1", "-1", "-4"}, 
		{ "F",   "-2", "-3", "-3", "-3", "-2", "-3", "-3", "-3", "-1",  "0",  "0", "-3",  "0",  "6", "-4", "-2", "-2",  "1",  "3", "-1", "-3", "-3", "-1", "-4"}, 
		{ "P","-1", "-2", "-2", "-1", "-3", "-1", "-1", "-2", "-2", "-3", "-3", "-1", "-2", "-4",  "7", "-1", "-1", "-4", "-3", "-2", "-2", "-1", "-2", "-4"}, 
		{ "S",  "1", "-1",  "1",  "0", "-1",  "0",  "0",  "0", "-1", "-2", "-2",  "0", "-1", "-2", "-1",  "4",  "1", "-3", "-2", "-2",  "0",  "0",  "0", "-4"}, 
		{ "T",   "0", "-1",  "0", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-1", "-1", "-2", "-1",  "1",  "5", "-2", "-2",  "0", "-1", "-1",  "0", "-4"}, 
		{"W",   "-3", "-3", "-4", "-4", "-2", "-2", "-3", "-2", "-2", "-3", "-2", "-3", "-1",  "1", "-4", "-3", "-2", "11",  "2", "-3", "-4", "-3", "-2", "-4"}, 
		{ "Y", "-2", "-2", "-2", "-3", "-2", "-1", "-2", "-3",  "2", "-1", "-1", "-2", "-1",  "3", "-3", "-2", "-2",  "2",  "7", "-1", "-3", "-2", "-1", "-4"}, 
		{ "V",  "0", "-3", "-3", "-3", "-1", "-2", "-2", "-3", "-3",  "3",  "1", "-2",  "1", "-1", "-2", "-2",  "0", "-3", "-1",  "4", "-3", "-2", "-1", "-4"},
		{"B", "-2", "-1",  "3",  "4", "-3",  "0",  "1", "-1",  "0", "-3", "-4",  "0", "-3", "-3", "-2",  "0", "-1", "-4", "-3", "-3",  "4",  "1", "-1", "-4"}, 
		{ "Z", "-1",  "0",  "0",  "1", "-3",  "3",  "4", "-2",  "0", "-3", "-3",  "1", "-1", "-3", "-1",  "0", "-1", "-3", "-2", "-2",  "1",  "4", "-1", "-4"}, 
		{ "X", "0", "-1", "-1", "-1", "-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2",  "0",  "0", "-2", "-1", "-1", "-1", "-1", "-1", "-4"}, 
		{"*","-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4",  "1"}};	private static int[][] compMtrx;
	private static String[][] compMtrxDirections;

	static void getOutput(String input1, String input2) {
		System.out.println("Score: " + compMtrx[compMtrx.length - 1][compMtrx[compMtrx.length - 1].length - 1]);
		Stack s1 = new Stack();
		Stack s2 = new Stack();
		int rowNow = compMtrxDirections.length - 1;
		int collumnNow = compMtrxDirections[compMtrxDirections.length - 1].length - 1;

		while (rowNow != 0 && collumnNow != 0) {
			if (compMtrxDirections[rowNow][collumnNow] == "diagonal") {
				s1.add(input1.charAt(rowNow - 1));
				s2.add(input2.charAt(collumnNow - 1));
				rowNow--;
				collumnNow--;

			} else if (compMtrxDirections[rowNow][collumnNow] == "up") {
				s1.add(input1.charAt(rowNow - 1));
				s2.add("-");
				rowNow--;
			} else if (compMtrxDirections[rowNow][collumnNow] == "left") {
				s2.add(input2.charAt(collumnNow - 1));
				s1.add("-");
				collumnNow--;
			}

		}
		if (rowNow != 0) {
			for (int i = rowNow; i >0; i--,rowNow--) {
				
				s1.add(input1.charAt(rowNow - 1));
				s2.add("-");
			}
		} else {
			for (int i = collumnNow; i >0; i--,collumnNow--) {
				s2.add(input2.charAt(collumnNow - 1));
				s1.add("-");
				
			}
		}
		while (!s1.empty()) {

			System.out.print(s1.pop());
		}
		System.out.println();
		while (!s2.empty()) {

			System.out.print(s2.pop());
		}
//		for (int i = 0; i < compMtrxDirections.length; i++) {
//			for (int j = 0; j < compMtrxDirections[i].length; j++) {
//				System.out.print(compMtrxDirections[i][j]+"-");
//			}
//			System.out.println();
//		}

	}

	static void compare(String input1, String input2) {
		compMtrx = new int[input1.length() + 1][input2.length() + 1];
		compMtrxDirections = new String[input1.length() + 1][input2.length() + 1];
		for (int i = 0; i < compMtrx.length; i++) {
			compMtrx[i][0] = i * -5;

		}
		for (int i = 0; i < compMtrx[0].length; i++) {
			compMtrx[0][i] = i * -5;

		}
		for (int i = 1; i <= input1.length(); i++) {

			for (int j = 1; j <= input2.length(); j++) {

				int max = Math.max(
						compMtrx[i - 1][j - 1] + getDiagonalScore(input1.charAt(i - 1), input2.charAt(j - 1)),
						Math.max(compMtrx[i - 1][j] - 5, compMtrx[i][j - 1] - 5));
				compMtrx[i][j] = max;
				if (compMtrx[i - 1][j - 1] + getDiagonalScore(input1.charAt(i - 1), input2.charAt(j - 1)) == max) {
					compMtrxDirections[i][j] = "diagonal";
				} else if (compMtrx[i - 1][j] - 5 == max) {
					compMtrxDirections[i][j] = "up";
				} else if (compMtrx[i][j - 1] - 5 == max) {
					compMtrxDirections[i][j] = "left";
				}

			}
		}
		System.out.println(Arrays.deepToString(compMtrx));
	}

	static int getDiagonalScore(char first, char second) {
		int i = 0, j = 0;
		for (i = 0; i < blosum.length; i++) {
			if (blosum[i][0].equalsIgnoreCase(String.valueOf(first))) {
				break;
			}
		}
		for (j = 0; j < blosum[i].length; j++) {
			if (blosum[0][j].equalsIgnoreCase(String.valueOf(second))) {
				break;
			}
		}
		return Integer.parseInt(blosum[i][j]);
	}

//	static void readBlosum() throws IOException {
//		File file = new File("Blosum62.txt");
//
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader cr = new BufferedReader(new FileReader(file));
//		String st;
//		String[] arr;
//
//		int i = 0;
//		while ((st = br.readLine()) != null) {
//			i++;
//		}
//		blosum = new String[i][i];
//		i = 0;
//		while ((st = cr.readLine()) != null) {
//			arr = st.split("  |\\ ");
//			for (int j = 0; j < arr.length; j++) {
//				blosum[i][j] = arr[j];
//			}
//			i++;
//		}
//
//	}

	public static void main(String[] args) throws Exception {
		String input1 = "MKTERPRPNTFIIRCLQWTTVIERTFHVETPEEREEWTTAIQTVADGLKKQEEEE", // you can change first input here 
				input2 = "CQLMKTERPRPNTFVIRCLQWTTVIERTFHVDSPDEREEWMRAIQMVANSLKQRGPGEDA";// you can change second input here
		//readBlosum();
		compare(input1, input2);
		getOutput(input1, input2);
	}
}
