import java.util.*;

public class SetCoverApprox {
    public static void main(String[] args) {
        Set<Integer> universe = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Set<Integer>> subsets = List.of(
                new HashSet<>(List.of(1, 2, 3)),
                new HashSet<>(List.of(2, 4)),
                new HashSet<>(List.of(3, 4)),
                new HashSet<>(List.of(4, 5)));

        List<Set<Integer>> cover = new ArrayList<>();
        Set<Integer> covered = new HashSet<>();

        while (!universe.equals(covered)) {
            Set<Integer> bestSet = null;
            int maxCovered = 0;

            for (Set<Integer> s : subsets) {
                Set<Integer> temp = new HashSet<>(s);
                temp.removeAll(covered);
                if (temp.size() > maxCovered) {
                    maxCovered = temp.size();
                    bestSet = s;
                }
            }

            if (bestSet == null)
                break;
            cover.add(bestSet);
            covered.addAll(bestSet);
        }

        System.out.println("Approximate Set Cover:");
        for (Set<Integer> s : cover) {
            System.out.println(s);
        }
    }
}
