package agentmain;

import model.Fruit;

public class TestFruitAgentMain {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            new Fruit().getFruit();
            Thread.sleep(2 * 1000);
        }
    }
}
