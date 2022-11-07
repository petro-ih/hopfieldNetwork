public class Main {
    public static void main(String[] args) {
        int x1[][] = {
                {-1, 1, -1, -1},
                {-1, 1, -1, -1},
                {-1, 1, -1, -1},
                {-1, 1, 1, -1}
        };
        int x2[][] = {
                {1, 1, 1, 1},
                {1, -1, -1, 1},
                {1, -1, -1, 1},
                {1, 1, 1, 1}
        };
        int y[][] = {
                {1, 1, 1, 1},
                {1, 1, -1, 1},
                {1, -1, 1, 1},
                {1, 1, 1, 1}
        };
        HopfieldNetwork network = new HopfieldNetwork(x1, x2);
        int k = network.find(y);
        System.out.println(k+1);
    }
}

