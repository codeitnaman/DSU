import java.util.Arrays;

public class DSUBySize {
    private static class DSU {
        int[] size;
        int[] parent;

        public DSU(int n) {
            size = new int[n + 1];
            Arrays.fill(size, 1);

            parent = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                parent[i] = i;
            }
        }

        public int getParent(int u) {
            if (u == parent[u]) {
                return u;
            }

            return parent[u] = getParent(parent[u]);
        }

        public void makeUnion(int u, int v) {
            int pu = getParent(u);
            int pv = getParent(v);
            if (pu == pv) {
                return;
            }

            int size_u = size[pu];
            int size_v = size[pv];

            if (size_u < size_v) {
                parent[pu] = pv;
                size[pv] += size_u;
            } else {
                parent[pv] = pu;
                size[pu] += size_v;
            }
        }

        public boolean isPresentInSameComponent(int u, int v) {
            return getParent(u) == getParent(v);
        }
    }

    public static void main(String[] args) {
        DSU dsu = new DSU(7);
        dsu.makeUnion(1, 2);
        dsu.makeUnion(2, 3);

        System.out.println(dsu.isPresentInSameComponent(3, 4));

        dsu.makeUnion(1, 4);
        System.out.println(dsu.isPresentInSameComponent(3, 4));
    }
}
