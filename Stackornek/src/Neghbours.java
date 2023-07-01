import java.util.Random;

public class Neghbours {
    public int [] singleSwapMove(int solution[])
    {
        int[] sira=createRandom(solution.length);
        int temp=solution[sira[0]];
        solution[sira[0]]=solution[sira[1]];
        solution[sira[1]]=temp;
        return solution;
    }
    public int[] createRandom(int n) {
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int randomNum = rand.nextInt(n);
            boolean contains = false;

            for (int j = 0; j < i; j++) {
                if (arr[j] == randomNum) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                arr[i] = randomNum;
            } else {
                i--;
            }
        }

        return arr;
    }
}
