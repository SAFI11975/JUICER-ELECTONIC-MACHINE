

public class Main {
    public static void main(String[] args) {
        // Create juicers
        Juicer j1 = new Juicer("Philips");
        Juicer j2 = new Juicer("Panasonic");
        Fruit f1=new Fruit("Apple");
        Fruit f2=new Fruit("Carrot");
        // Create motor and lid instances


        // Turn on the juicers


        // Add motors and lids using composition
        j1.addMotor(new Motor());
        j1.addLid(new Lid());
        j2.addMotor(new Motor());
        j2.addLid(new Lid());



        // Close the lids
        j1.lid.close();
        j2.lid.close();

        // Add fruits to juicers
        j1.addFruit(f1);
        j2.addFruit(f2);

        // Create JuicerRunner threads for both juicers
        JuicerRunner runner1 = new JuicerRunner(j1);
        JuicerRunner runner2 = new JuicerRunner(j2);
        j1.turnOn();
        j2.turnOn();

        // Start juicing with delay and sequential execution
        runner1.start();
        try {
            runner1.join();  // Ensure runner1 finishes before starting runner2
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        runner2.start();  // Start juicer 2 after runner 1 finishes
    }
}