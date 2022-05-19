public class Car {

    static final int OIL_REQUIRED = 10;
    final int SPENT_OIL = -50;
    int oil;
    int speed;
    int fee;
    int income;
    int status; //버스: 1(운행중), 0(차고지) / 택시: 1(운행중), 2(일반), 0(운행불가)
    int guest; //버스: 승객 수 / 택시: 1이상이면 승객 탑승

    {
        speed = 0;
        status = 0;
        guest = 0;
        income = 0;
        oil = 100;
    }


}