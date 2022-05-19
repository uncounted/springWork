public class Bus extends Car
        implements Startable, StatusChangeable, SpeedChangeable, GuestChangeable, OilCheckable, OilChangeable, IncomeChangeable, StatusCheckable{

    private static int number;
    private final int MAX_GUEST= 45;
    private int busNum;

    // 버스 고유 번호를 생성하기 위한 static 변수와 static 초기화 블럭
    // (순서대로 넣고 싶어서 랜덤 함수를 쓰지 않았다. 순서대로 넣되, 중간에 삭제되더라도 고유 번호를 유지할 수 있다.)
    static {
        number = 0;
    }

    // number: 인스턴스가 생성될 때마다 증가
    // fee: 기본요금. final은 아니되 어느 정도 고정적인 값은 값은 초기화 블럭을 이용했다.
    {
        number++;
        fee = 500;
    }

    // 계속해서 변경되는 값은 생성자에 넣어두었다.
    Bus() {
        super();
        busNum = number;
        status = true;
    }

    // 운행 시작
    // 오일 상태를 체크하고, 필요 오일양보다 많으면 상태를 변경하고 속도를 높인다.
    @Override
    public void startDrive() {
        checkOil();
        if(oil >= OIL_REQUIRED){
            changeStatus(true);
            changeSpeed(50);
        }
    }

    // 상태 변경
    // true면 false로+속도를 0으로, false면 true로.
    @Override
    public void changeStatus(boolean status){
        if(status == true){
            System.out.printf("%d번 버스: 상태가 '운행'으로 변경됩니다.%n", busNum);
        } else {
            System.out.printf("%d번 버스: 상태가 '차고지행'으로 변경됩니다.%n", busNum);
            changeSpeed(0);
        }
        this.status = status;
    }

    // 현재 상태를 반환
    @Override
    public boolean checkStatus() {
        if (status == false) {
            System.out.printf("%d번 버스: 현재 차고지에 있습니다.", busNum);
            return false;
        } else {
            return true;
        }
    }

    // 속도 변경
    @Override
    public void changeSpeed(int speed){
        System.out.printf("%d번 버스: 속도가 %d에서 %d로 변경됩니다.%n", busNum, this.speed, speed);
        this.speed = speed;
    }

    // 오일양 체크
    // 오일양 기준치 미달 시 상태값 false로 변경
    @Override
    public void checkOil(){
        if (oil < OIL_REQUIRED){
            System.out.printf("%d번 버스: 주유량이 10 미만으로, 손님을 하차시키고 차고지로 돌아갑니다.%n", busNum);
            this.guest = 0;
            changeStatus(false);
        } else {
            System.out.printf("- %d번 버스: 현재 주유량은 %d L 있습니다.%n", busNum, oil);
        }
    }

    //오일 입력 받을 경우 오일값 변경
    @Override
    public void changeOil(int oil) {
        this.oil += oil;
    }

    // 손님 추가/삭제
    // 운행중인지 상태 먼저 확인 -> 태우는 것인지 확인 -> 최대 탑승인원에 맞는 숫자만 태우기 || 하차시키기 -> 오일 소모 -> 오일양 체크
    // 운행중이 아니면 프로그램 종료
    @Override
    public void changeGuest(boolean addDel, int guest){
        if (checkStatus()) {
            if (addDel == true) {
                if (MAX_GUEST - this.guest == 0) {
                    System.out.printf("%d번 버스: 최대 탑승인원 %d명을 초과하여 탑승하지 못했습니다.%n", busNum, MAX_GUEST);
                } else if (this.guest + guest > MAX_GUEST) {
                    System.out.printf("%d번 버스: 최대 탑승인원 %d명을 초과하여 %d명 중 %d명만 탑승합니다.%n", busNum, MAX_GUEST, guest, MAX_GUEST - this.guest);
                    this.guest += MAX_GUEST - this.guest;
                    changeIncome(guest);
                } else {
                    this.guest += guest;
                    System.out.printf("%d번 버스: 손님이 %d명 탑승하여 총 %d명의 손님이 탑승 중입니다.%n", busNum, guest, this.guest);
                    changeIncome(guest);
                }
            } else {
                if (this.guest <= 0) {
                    System.out.printf("%d번 버스: 현재 승객은 %d명입니다.%n", busNum, this.guest);
                } else {
                    this.guest -= guest;
                    System.out.printf("%d번 버스: 승객이 %d명 하차하여 총 %d명의 손님이 탑승 중입니다.%n", busNum, guest, this.guest);
                }
            }
            changeOil(SPENT_OIL);
            checkOil();
        } else {
            System.exit(0);
        }

    }

    @Override
    public void changeGuest(boolean addDel, int guest, int distacnceToDestination) {
        //빈 코드가 생겨버렸다. 인터페이스로 구현하지 말아야 하나?
    }

    // 손님 수에 따른 수입 계산
    @Override
    public void changeIncome(int guest){
        income += fee * guest;
        System.out.printf("- %d번 버스: 수입 %d원(손님 %d명), 총 수입 %d원%n", busNum, fee*guest, guest, income);
    }

}
