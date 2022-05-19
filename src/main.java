public class main {
    public static void main(String[] args){
//        Bus bus = new Bus();
//        bus.startDrive();
//        bus.changeSpeed(80);
//        Bus bus2 = new Bus();
//        bus2.startDrive();
//        bus.changeGuest(true, 5);
//        bus.changeGuest(true, 10);
//        bus.changeGuest(true, 29);
//        bus.changeGuest(true, 5);
//        bus.changeGuest(true, 6);
//        bus.changeGuest(false, 7);
//        bus.changeGuest(true, 2);

        Taxi taxi = new Taxi();
        taxi.startDrive();
        taxi.changeSpeed(80);
        Taxi taxi2 = new Taxi();
        taxi2.startDrive();
        taxi2.changeGuest(true, 5, 10);
        taxi2.changeGuest(true, 3, 40);
        taxi2.changeGuest(false, 1);
        taxi2.changeGuest(false, 2);

    }
}
