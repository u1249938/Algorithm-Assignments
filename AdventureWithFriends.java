import java.util.*;

public class AdventureWithFriends {

    enum States {
        NEW, ACTIVE, FINISHED
    }

    static String[] sorted;
    public static class Vertex {
        
        String name;
        HashSet<String> neighbors;
        States status;

        public Vertex(String v) {
            name = v;
            neighbors = new HashSet<String>();
        }
        
        public HashSet<String> getNeighbors() {
            return neighbors;
        }

        public void addNeighbor(String w) {
            neighbors.add(w);
        }
    }

    public static String[] TopologicalSort(HashMap<String, Vertex> graph) {
        Set<String> allVerticies = graph.keySet();
        for (String v : allVerticies) {
            graph.get(v).status = States.NEW;
        }
        int clock = allVerticies.size() - 1;
        for (String v : allVerticies) {
            if (graph.get(v).status == States.NEW) {
                clock = TopSortDFS(v, clock, graph);
            }
        }
        return sorted;
    }

    public static int TopSortDFS(String v, int clock, HashMap<String, Vertex> graph) {
        graph.get(v).status = States.ACTIVE;
        for (String w : graph.get(v).getNeighbors()) {
            if (graph.get(w).status == States.NEW) {
                clock = TopSortDFS(w, clock, graph);
            } else if (graph.get(w).status == States.ACTIVE) {
                System.out.println("Unsolvable");
                System.exit(1);
            }
        }
        graph.get(v).status = States.FINISHED;
        sorted[clock] = v;
        clock--;
        return clock; 
    }

    public static void main(String[] args) {
        HashMap<String, Vertex> graph = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int playerOneQuestNum = Integer.parseInt(scan.nextLine());
        String[][] p1Quests = new String[playerOneQuestNum][2];
        for (int i = 0; i < playerOneQuestNum; i++) {
            String[] inputInfo = scan.nextLine().split(" ");
            p1Quests[i] = inputInfo;
        }
        int playerTwoQuestNum = Integer.parseInt(scan.nextLine());
        String[][] p2Quests = new String[playerTwoQuestNum][2];
        for (int i = 0; i < playerTwoQuestNum; i++) {
            String[] inputInfo = scan.nextLine().split(" ");
            p2Quests[i] = inputInfo;
        }
        int coopQuestNum = Integer.parseInt(scan.nextLine());
        Hashtable<String, String> coopQuests = new Hashtable<>();
        for (int i = 0; i < coopQuestNum; i++) {
            String temp = scan.nextLine();
            coopQuests.put(temp, "");
        }

        for (int i = 0; i < playerOneQuestNum; i++) {
            if (coopQuests.containsKey(p1Quests[i][0])) {
                if (graph.containsKey(p1Quests[i][0])) {
                    graph.get(p1Quests[i][0]).addNeighbor(p1Quests[i][1] + "-1");
                } else {
                    Vertex temp = new Vertex(p1Quests[i][0]);
                    temp.addNeighbor(p1Quests[i][1] + "-1");
                    graph.put(p1Quests[i][0], temp);
                }
            } else if (coopQuests.containsKey(p1Quests[i][1])) {
                if (graph.containsKey(p1Quests[i][0] + "-1")) {
                    graph.get(p1Quests[i][0] + "-1").addNeighbor(p1Quests[i][1]);
                } else {
                    Vertex temp = new Vertex(p1Quests[i][0] + "-1");
                    temp.addNeighbor(p1Quests[i][1]);
                    graph.put(p1Quests[i][0] + "-1", temp);
                }
            } else {
                if (graph.containsKey(p1Quests[i][0] + "-1")) {
                    graph.get(p1Quests[i][0] + "-1").addNeighbor(p1Quests[i][1] + "-1");
                } else {
                    Vertex temp = new Vertex(p1Quests[i][0] + "-1");
                    temp.addNeighbor(p1Quests[i][1] + "-1");
                    graph.put(p1Quests[i][0] + "-1", temp);
                }
            }
            if (coopQuests.containsKey(p1Quests[i][1])) {
                if (!graph.containsKey(p1Quests[i][1])) {
                    Vertex temp = new Vertex(p1Quests[i][1]);
                    graph.put(p1Quests[i][1], temp);
                }
            } else {
                if (!graph.containsKey(p1Quests[i][1] + "-1")) {
                    Vertex temp = new Vertex(p1Quests[i][1] + "-1");
                    graph.put(p1Quests[i][1] + "-1", temp);
                }
            }
        }
        for (int i = 0; i < playerTwoQuestNum; i++) {
            if (coopQuests.containsKey(p2Quests[i][0])) {
                if (graph.containsKey(p2Quests[i][0])) {
                    graph.get(p2Quests[i][0]).addNeighbor(p2Quests[i][1] + "-2");
                } else {
                    Vertex temp = new Vertex(p2Quests[i][0]);
                    temp.addNeighbor(p2Quests[i][1] + "-2");
                    graph.put(p2Quests[i][0], temp);
                }
            } else if (coopQuests.containsKey(p2Quests[i][1])) {
                if (graph.containsKey(p2Quests[i][0] + "-2")) {
                    graph.get(p2Quests[i][0] + "-2").addNeighbor(p2Quests[i][1]);
                } else {
                    Vertex temp = new Vertex(p2Quests[i][0] + "-2");
                    temp.addNeighbor(p2Quests[i][1]);
                    graph.put(p2Quests[i][0] + "-2", temp);
                }
            } else {
                if (graph.containsKey(p2Quests[i][0] + "-2")) {
                    graph.get(p2Quests[i][0] + "-2").addNeighbor(p2Quests[i][1] + "-2");
                } else {
                    Vertex temp = new Vertex(p2Quests[i][0] + "-2");
                    temp.addNeighbor(p2Quests[i][1] + "-2");
                    graph.put(p2Quests[i][0] + "-2", temp);
                }
            }
            if (coopQuests.containsKey(p2Quests[i][1])) {
                if (!graph.containsKey(p2Quests[i][1])) {
                    Vertex temp = new Vertex(p2Quests[i][1]);
                    graph.put(p2Quests[i][1], temp);
                }
            } else {
                if (!graph.containsKey(p2Quests[i][1] + "-2")) {
                    Vertex temp = new Vertex(p2Quests[i][1] + "-2");
                    graph.put(p2Quests[i][1] + "-2", temp);
                }
            }
        }
        scan.close();
        sorted = new String[graph.size()];
        
        TopologicalSort(graph);
        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }
    }

}