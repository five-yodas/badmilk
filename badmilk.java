import java.util.*;
import java.io.*;

public class badmilk {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("badmilk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int numPeople = Integer.parseInt(st.nextToken());
		int numMilk = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		Set<ArrayList<Integer>> personMilk = new HashSet<>();
		List<int[]> data = new ArrayList<>();
		List<int[]> sick = new ArrayList<>();
		
		for (int i = 0; i < D; i++) {
			st = new StringTokenizer(f.readLine());
			int[] current = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			searchForRepeat(current, personMilk, data);
		}
		
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(f.readLine());
			sick.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		
		int minimum = 0;
		
		for (int milkType = 1; milkType < numMilk+1; milkType++) { //milk type
			boolean milkPossible = true;
			for (int sickness = 0; sickness < S; sickness++) { //which sickness
				boolean possible = false;
				int[] currentSickness = sick.get(sickness);
				for (int index = 0; index < data.size(); index++) { //cycles through data, sees if time is possible
					if (data.get(index)[0] == currentSickness[0] && data.get(index)[1] == milkType) {
						if (data.get(index)[2] < currentSickness[1]) {
							possible = true;
							break;
						}
						break;
					}
				}
				if (!possible) {
					milkPossible = false;
					break;
				}
			}
			if (milkPossible) {
				int totalSick = 0;
				for (int[] current : data) {
					if (current[1] == milkType) {
						totalSick++;
					}
				}
				if (totalSick > minimum) {
					minimum = totalSick;
				}
			}
		}
		out.println(minimum);
		out.close();

	}
	
	public static void searchForRepeat(int[] person, Set<ArrayList<Integer>> personMilk, List<int[]> data) {
		ArrayList<Integer> listPerson = new ArrayList<Integer>(Arrays.asList(person[0], person[1]));
		if (personMilk.contains(listPerson)) {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i)[0] == person[0] && data.get(i)[1] == person[1]) {
					if (data.get(i)[2] > person[2]) {
						data.set(i, person);
					}
				}
			}
		} else {
			data.add(person);
			personMilk.add(listPerson);
		}
	}

}
