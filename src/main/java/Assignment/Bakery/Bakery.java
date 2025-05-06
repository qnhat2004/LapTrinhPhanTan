//package Assignment.Bakery;

public class Bakery implements lock {
    private int[] number;
    private boolean[] choosing;
    private int n;

    public Bakery(int n) {
        this.n = n;
        number = new int[n];
        choosing = new boolean[n];
        for (int j = 0; j < n; j++) {
            choosing[j] = false;
            number[j] = 0;
        }
    }

    @Override
    public void requestCS(int i) {
        choosing[i] = true;
        for (int j = 0; j < n; j++) {   // find the min number
            if (number[j] > number[i]) {
                number[i] = number[j];
            }
        }
        choosing[i] = false;
        number[i]++;
        for (int j = 0; j < n; j++) {   // wait until all processes have chosen their number
            while (choosing[j]) {
                // busy wait
            }
            while (number[j] != 0 &&
                    (number[j] < number[i] ||
                            (number[j] == number[i] && j < i))) {
                // busy wait
            }
        }
    }

    @Override
    public void releaseCS(int i) {
        number[i] = 0;
    }
}
