public class DSUByRank {

    private static class DSU {
        int[] rank;
        int[] parent;

        public DSU(int n) {
            rank = new int[n + 1];
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

            int rpu = rank[pu];
            int rpv = rank[pv];

            if (rpu < rpv) {
                parent[pu] = pv;
            } else if (rpu > rpv) {
                parent[pv] = pu;
            } else {
                parent[pu] = pv;
                rank[pv]++;
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