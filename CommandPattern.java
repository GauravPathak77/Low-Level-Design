import java.util.*;

public class CommandPattern {    

    // Command Interface
    public interface Command {
        public void execute();
        public void undo();
    }

    // Receivers
    public static class Light {
        public void on() {
            System.out.println("Light is ON");
        }
        public void off() {
            System.out.println("Light is OFF");
        }
    }

    public static class Fan {
        public void on() {
            System.out.println("Fan is ON");
        }
        public void off() {
            System.out.println("Fan is OFF");
        }
    }

    // Concrete command for light
    public static class LightCommand implements Command {
        private Light l;
        
        public LightCommand(Light l) {
            this.l = l;
        }

        public void execute() {
            l.on();
        }

        public void undo() {
            l.off();
        }
    }

    // Concrete command for Fan
    public static class FanCommand implements Command {
        private Fan f;
        
        public FanCommand(Fan f) {
            this.f = f;
        }

        public void execute() {
            f.on();
        }

        public void undo() {
            f.off();
        }
    }

    public static class RemoteController {
        int numButtons = 4;
        Command[] buttons = new Command[numButtons];
        boolean[] buttonPressed = new boolean[numButtons];

        public RemoteController() {
            for(int i = 0; i < numButtons; i++) {
                buttons[i] = null;
                buttonPressed[i] = false;
            }
        }

        public void setCommand(int idx, Command cmd) {
            if(idx >= 0 && idx < numButtons) {
                buttons[idx] = cmd;
                buttonPressed[idx] = false;
            }
        }

        public void pressButton(int idx) {
            if(idx >= 0 && idx < numButtons && buttons[idx] != null) {
                if(!buttonPressed[idx]) {
                    buttons[idx].execute();
                }
                else {
                    buttons[idx].undo();
                }

                buttonPressed[idx] = !buttonPressed[idx];
            }
            else {
                System.out.println("No command assigned at button " + idx);
            }
        }
    }

    // Main Application
    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();
        RemoteController remote = new RemoteController();

        remote.setCommand(0, new LightCommand(light));
        remote.setCommand(1, new FanCommand(fan));

        // Simulate button presses
        System.out.println("--Toggling light button--");
        remote.pressButton(0);      //ON
        remote.pressButton(0);      //OFF

        System.out.println("--Toggling Fan button--");
        remote.pressButton(1);     //ON
        remote.pressButton(1);     //OFF

        // Press Unassigned button to show default message
        System.out.println("--Pressing Unassigned Button 2--");
        remote.pressButton(2);
    }
}