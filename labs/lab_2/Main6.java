import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Main6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cruiseNum = sc.nextInt();

        List<Loader> loaderList = new LinkedList<Loader>();
        sc.nextLine();

        for (int i = 0; i < cruiseNum; i++) {
            String line = sc.nextLine();
            String[] splitLine = line.split(" ");
            Cruise cruise;

            if (splitLine.length == 4) {
                cruise = new BigCruise(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
            } else {
                cruise = new SmallCruise(splitLine[0], Integer.parseInt(splitLine[1]));
            }

            int loaderNum = cruise.getNumOfLoadersRequired();

            for (int j = 0; j < loaderList.size(); j++) {
                if (loaderNum == 0) {
                    break;
                }

                Loader curLoader = loaderList.get(j);
                Loader newLoader = curLoader.serve(cruise);

                if (newLoader != null) {
                    loaderList.set(j, newLoader);
                    loaderNum -= 1;
                    System.out.println(newLoader);
                }
            }

            while (loaderNum > 0) {
                Loader newLoader;

                if ((loaderList.size() + 1) % 3 == 0) {
                    newLoader = new RecycledLoader(loaderList.size() + 1, cruise);
                } else {
                    newLoader = new Loader(loaderList.size() + 1, cruise);
                }

                loaderList.add(newLoader);
                System.out.println(newLoader);
                loaderNum -= 1;
            }
        }
    }
}
