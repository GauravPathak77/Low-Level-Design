public class FacadePattern {
    // Subsystems
    static class PowerSupply {
        public void providePower() {
            System.out.println("Power Supply: Providing power...");
        }
    }

    static class CoolingSystem {
        public void startFans() {
            System.out.println("Cooling System: Fans started...");
        }
    }

    static class CPU {
        public void initialize() {
            System.out.println("CPU: Initialization started...");
        }
    }

    static class Memory {
        public void selfTest() {
            System.out.println("Memory: Self-test passed...");
        }
    }

    static class HardDrive {
        public void spinUp() {
            System.out.println("Hard Drive: Spinning up...");
        }
    }

    static class BIOS {
        public void boot(CPU cpu, Memory memory) {
            System.out.println("BIOS: Booting CPU and Memory checks...");
            cpu.initialize();
            memory.selfTest();
        }
    }

    static class OperatingSystem {
        public void load() {
            System.out.println("Operating System: Loading into memory...");
        }
    }

    public static class ComputerFacade {
        private PowerSupply powerSupply = new PowerSupply();
        private CoolingSystem coolingSystem = new CoolingSystem();
        private CPU cpu = new CPU();
        private Memory memory = new Memory();
        private HardDrive hardDrive = new HardDrive();
        private BIOS bios = new BIOS();
        private OperatingSystem operatingSystem = new OperatingSystem();

        public void startComputer() {
            System.out.println("------Starting Computer------");
            powerSupply.providePower();
            coolingSystem.startFans();
            cpu.initialize();
            memory.selfTest();
            hardDrive.spinUp();
            bios.boot(cpu, memory);
            operatingSystem.load();
            System.out.println("Computer booted Successfully");
        }
    }

    public static void main(String[] args) {
        ComputerFacade cf = new ComputerFacade();
        cf.startComputer();
    }
}
