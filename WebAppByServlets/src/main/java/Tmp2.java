import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tmp2 {
    public static void main(String[] args) {
        List<String> listLite = new ArrayList<>();
        Collections.addAll(listLite, "1", "2", "3");

        List<String> listBigger = new ArrayList<>();
        Collections.addAll(listBigger, "1", "1", "2", "2", "3", "3", "4", "4", "5", "5", "6", "6");

        // ...
        List<String> equalsList = createEqualsList(listBigger, listLite);
        System.out.println("equals: " + equalsList);

        // ...
        List<String> differencesList = createDifferencesList_useStreamApiFilter(listBigger, listLite);
        System.out.println("differences: " + differencesList);

        // ...
        List<String> differencesList2 = getDifferencesList_useFor(listLite, listBigger);
        System.out.println("differences for..: " + differencesList2);

        // ...
        List<String> diff3 = getDiffByTwoLists_useStreamApiRemoveAll(listBigger, listLite);
        System.out.println("diff3: " + diff3);

        // ...

    }

    public static List<String> getDiffByTwoLists_useStreamApiRemoveAll(List<String> listBigger, List<String> listLite) {
        List<String> list = new ArrayList<>(listBigger);
        list.removeAll(listLite);
        return list;
    }

    public static List<String> createEqualsList(List<String> listBigger, List<String> listLite) {
        // We create a stream of elements from the first list.
        List<String> listOneList = listBigger.stream()
                // We select any elements such that in the stream of elements from the second list
                .filter(two -> listLite.stream()
                        // there is an element that has the same name and school as this element,
                        .anyMatch(one -> one.equals(two)))
                // and collect all matching elements from the first list into a new list.
                .collect(Collectors.toList());
        // We return the collected list.
        return listOneList;
    }

    public static List<String> createDifferencesList_useStreamApiFilter(List<String> listBigger, List<String> listLite) {
        List<String> differences = listBigger.stream()
                .filter(element -> !listLite.contains(element))
                .collect(Collectors.toList());
        return differences;
    }

    public static List<String> getDifferencesList_useFor(List<String> listLite, List<String> listBigger) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < listBigger.size(); i++) {
            String currentId = listBigger.get(i);
            boolean contains = listLite.contains(currentId);

            if (!contains) list.add(currentId);
        }
        return list;
    }

    public static List<String> getDifferencesList_useFor2(List<String> listLite, List<String> listBigger) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < listBigger.size(); i++) {
            String currentId = listBigger.get(i);

            boolean contains = listLite.contains(currentId);

            if (!contains) list.add(currentId);
        }
        return list;
    }
}
