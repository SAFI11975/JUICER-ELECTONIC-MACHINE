public class Appliance {
    String brand;
    boolean isOn = false;

    Appliance(String brand) {
        this.brand = brand;
    }

    void turnOn() {
        isOn = true;
        System.out.println(brand + " is ON.");
    }

    void turnOff() {
        isOn = false;
        System.out.println(brand + " is OFF.");
    }
}

// Motor class (Handles motor functionality with on/off status)
class Motor {
    boolean isRunning = false;

    void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Motor started.");
        } else {
            System.out.println("Motor is already running.");
        }
    }

    void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Motor stopped.");
        } else {
            System.out.println("Motor is already stopped.");
        }
    }

    boolean isRunning() {
        return isRunning;
    }
}

// Lid class (For closing the juicer)
class Lid {
    boolean closed = false;

    void close() {
        closed = true;
        System.out.println("Lid closed.");
    }

    boolean isClosed() {
        return closed;
    }
}

// Fruit class (Represents the fruit to be juiced)
class Fruit {
    String name;

    Fruit(String name) {
        this.name = name;

        }
            
    }


// Juicer class (Extends Appliance and uses Motor, Lid through composition)
class Juicer extends Appliance {
    Motor motor;  // Composition: motor is added to juicer
    Lid lid;      // Composition: lid is added to juicer
    Fruit fruit;

    Juicer(String brand) {
        super(brand);  // Call Appliance constructor
    }

    void addMotor(Motor motor) {
        this.motor = motor;
    }

    void addLid(Lid lid) {
        this.lid = lid;
    }

    void addFruit(Fruit fruit) {
        this.fruit = fruit;
    }
}

// JuicerRunner class (Extends Thread to handle multiple juicers with delay)
class JuicerRunner extends Thread {
    Juicer juicer;

    JuicerRunner(Juicer juicer) {
        this.juicer = juicer;
    }

    @Override
    public void run() {
        try {
            if (!juicer.isOn) {
                System.out.println(juicer.brand + ": Juicer is OFF.");
                return;
            }
            if (!juicer.lid.isClosed()) {
                System.out.println(juicer.brand + ": Lid not closed.");
                return;
            }
            if (juicer.fruit == null) {
                System.out.println(juicer.brand + ": No fruit added.");
                return;
            }

            // Juicing process with delay
            System.out.println(juicer.brand + ": Juicing " + juicer.fruit.name + "...");
            juicer.motor.start();

            // Adding delay between juicing messages to simulate a process
            Thread.sleep(2000);  // Simulate juicing time (2 seconds)

            System.out.println(juicer.brand + ": Juice ready!");
            juicer.motor.stop();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());;
        }
    }
}