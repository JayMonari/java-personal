import java.util.List;

public class BinarySearch {
    private List<Integer> sortedNumbers;

    public BinarySearch(List<Integer> sortedNumbers) {
        this.sortedNumbers = sortedNumbers;
    }

    public int indexOf(int target) throws ValueNotFoundException {
        int left = 0;
        int right = sortedNumbers.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int candidate = sortedNumbers.get(mid);
            if (candidate < target) {
                left = mid + 1;
            } else if (candidate > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        throw new ValueNotFoundException("Value not in array");
    }
}
