package TiaLU.API;

import TiaLU.Component.Node.Node;

public class NoAPI {

    public static void addNode(Node node) {
        TiaAPI.SceneMana.GetScene().SceNode.AddNode(node);
    }

    public static void addNode(int Scene, Node node) {
        TiaAPI.SceneMana.GetScene(Scene).SceNode.AddNode(node);
    }

    public static void addNode(String Scene, Node node) {
        TiaAPI.SceneMana.GetScene(Scene).SceNode.AddNode(node);
    }

}
