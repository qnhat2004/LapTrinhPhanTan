package Peterson;

public interface lock {
    public void requestCS(int pid);
    public void releaseCS(int pid);
}
