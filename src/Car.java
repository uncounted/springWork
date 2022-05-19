public class Car {

    static final int OIL_REQUIRED = 10;
    final int SPENT_OIL = -50;
    int oil;
    int speed;
    int fee;
    int income;
    boolean status; //버스: true(운행중), false(차고지) / 택시: true(운행중), false(일반)
    int guest; //버스: 승객 수 / 택시: 1이상이면 승객 탑승

    {
        speed = 0;
        status = false;
        guest = 0;
        income = 0;
        oil = 100;
    }


}