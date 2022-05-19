public class Taxi extends Car
        implements Startable, OilCheckable, OilChangeable, StatusChangeable, SpeedChangeable, IncomeChangeable, GuestChangeable {

    private static int number;
    private final int MAX_GUEST = 4;
    private int taxiNum;
    private int feeDistance;
    private int defaultDistance;
    private int distanceToDestination;
    private int sum;


    static {
        number = 0;
    }

    {
        number++;
        feeDistance = 100;
        defaultDistance = 20;
    }

    public Taxi() {
        super();
        taxiNum = number;
        status = false;
        sum=0;
    }

    @Override
    public void startDrive() {
        checkOil();
        if (oil >= OIL_REQUIRED) {
            changeSpeed(50);
        }
    }

    @Override
    public void changeStatus(boolean status) {
        if (status == true) {
            System.out.printf("%d번 택시: 상태가 '운행'으로 변경됩니다.%n", taxiNum);
        } else {
            System.out.printf("%d번 택시: 상태가 '일반'으로 변경됩니다.%n", taxiNum);
        }
        this.status = status;
    }

    @Override
    public void changeSpeed(int speed) {
        System.out.printf("%d번 택시: 속도가 %d에서 %d로 변경됩니다.%n", taxiNum, this.speed, speed);
        this.speed = speed;
    }

    @Override
    public void checkOil() {
        if (oil < OIL_REQUIRED) {
            System.out.printf("%d번 택시: 주유량이 10 미만으로, 일반 상태로 전환합니다. 손님을 태울 수 없습니다.%n", taxiNum);
            this.guest = 0;
            changeStatus(false);
            System.exit(0);
        } else {
            System.out.printf("- %d번 택시: 현재 주유량은 %d L 있습니다.%n", taxiNum, oil);
        }
    }

    @Override
    public void changeOil(int oil) {
        this.oil += oil;
    }

    @Override
    public void changeGuest(boolean addDel, int guest) {
        if(distanceToDestination > defaultDistance){
            sum = (defaultDistance*fee) + ((distanceToDestination-defaultDistance)*feeDistance);
        } else {
            sum = defaultDistance*fee;
        }
        System.out.printf("%d번 택시: 손님이 하차합니다. %d km 달렸습니다. 요금은 %d원입니다.%n", taxiNum, distanceToDestination, sum);
        income += sum;
        changeOil(SPENT_OIL);
        changeStatus(false);
        checkOil();
    }

    @Override
    public void changeGuest(boolean addDel, int guest, int distanceToDestination) {
        if (addDel == true) {
            if (guest > MAX_GUEST) {
                System.out.printf("%d번 택시: 최대 탑승인원 %d명을 초과하여 탑승을 거부합니다.%n", taxiNum, MAX_GUEST);
            } else {
                this.guest += guest;
                this.distanceToDestination = distanceToDestination;
                changeStatus(true);
                System.out.printf("%d번 택시: %d명의 손님이 탑승 중입니다. 목적지까지 %d km 남았습니다.%n", taxiNum, guest, distanceToDestination);
            }
        } else {
            changeGuest(addDel, guest);
        }
    }

    @Override
    public void changeIncome(int guest){
        income += fee * guest;
        System.out.printf("- %d번 택시: 수입 %d원(손님 %d명), 총 수입 %d원%n", taxiNum, fee*guest, guest, income);
    }



}
