// Peterson's Algorithm
public class Peterson implements lock {
    volatile int turn = 1;
    volatile boolean wantCS[] = {false, false};
    @Override
    public void requestCS(int i) {
        int j = 1 - i;
        wantCS[i] = true;
        turn = j;
        while (wantCS[j] && turn == j); // wait
    }
    @Override
    public void releaseCS(int i) {
        wantCS[i] = false;
    }
}