package bank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        int accountCounter = 0;

        while (true) {
            System.out.println("\n업무를 선택 해주세요.");
            System.out.println("계좌 개설은 1, 송금은 2, 입금은 3, 출금은 4, 잔액확인은 5, 은행 자본금확인은 6, 개설 계좌 갯수는 7, 계좌 해지는 8, 종료는 9를 입력해주세요.");
            
            int choice = sc.nextInt();
            // 통장 개설
            switch (choice) { 
                case 1: 
                    System.out.println("계좌 개설을 누르셨습니다. 마이너스 통장을 만드시겠습니까?");
                    System.out.println("마이너스 통장 개설: 1, 일반통장 개설: 2");
                    int type = sc.nextInt();
                    
                    String newAccountNo = String.valueOf(accountCounter);

                    if (type == 1) { 
                        System.out.println("마이너스 통장을 개설합니다. 신용점수를 입력해주세요.");
                        int score = sc.nextInt();
                        
                        double limit = 0; // limit = 한도
                        int rating = 0; // rating = 신용등급
                        boolean approved = false;

                        if (score >= 800) {
                            limit = 1500.0;
                            rating = 1;
                            approved = true;
                        } else if (score >= 600) { 
                            limit = 500.0;
                            rating = 2;
                            approved = true;
                        } else {
                            System.out.println("신용점수가 부족합니다. 일반 통장을 개설해주세요.");
                        }

                        if (approved) {
                            new MinusAccount(newAccountNo, bank, limit, rating);
                            System.out.println("계좌 개설이 완료되었습니다. 당신의 계좌번호는 " + newAccountNo + "입니다.");
                            System.out.println("신용등급: " + rating + "등급, 마이너스 한도: " + limit);
                            accountCounter++;
                        }
                    } else { 
                        System.out.println("일반 통장을 개설합니다.");
                        new Account(newAccountNo, bank);
                        System.out.println("계좌 개설이 완료되었습니다. 당신의 계좌번호는 " + newAccountNo + "입니다.");
                        accountCounter++;
                    }
                    break;

                case 2: //송금 기능
                    System.out.println("송금을 선택하셨습니다.");
                    System.out.println("자신의 계좌번호를 입력 해주세요.");
                    String myAccNo = sc.next();
                    System.out.println("상대방의 계좌번호를 입력 해주세요.");
                    String yourAccNo = sc.next();
                    System.out.println("송금할 금액을 입력 해주세요.");
                    double transferAmt = sc.nextDouble();

                    Account myAcc = bank.getAccount(myAccNo);
                    Account yourAcc = bank.getAccount(yourAccNo);

                    if (myAcc != null && yourAcc != null) {
                        myAcc.transfer(yourAcc, transferAmt);
                    } else {
                        System.out.println("계좌 번호를 확인해주세요.");
                    }
                    break;

                case 3: // 입금 기능
                    System.out.println("입금을 선택하셨습니다.");
                    System.out.println("계좌번호를 입력 해주세요.");
                    String depositAccNo = sc.next();
                    System.out.println("입금액을 입력 해주세요.");
                    double depositAmt = sc.nextDouble();
                    Account depositAcc = bank.getAccount(depositAccNo);
                    if (depositAcc != null) {
                        depositAcc.deposit(depositAmt);
                        System.out.println("입금 완료. 잔액: " + depositAcc.getBalance());
                    }
                    break;

                case 4: // 출금 기능 구현
                    System.out.println("출금을 선택하셨습니다.");
                    System.out.println("계좌번호를 입력 해주세요.");
                    String withAccNo = sc.next();
                    System.out.println("출금액을 입력 해주세요.");
                    double withAmt = sc.nextDouble();
                    Account withAcc = bank.getAccount(withAccNo);
                    if (withAcc != null) {
                        withAcc.withdraw(withAmt);
                        System.out.println("출금 후 잔액: " + withAcc.getBalance());
                    }
                    break;

                case 5: // 잔액 확인 기능
                    System.out.println("잔액확인을 선택 하셨습니다.");
                    System.out.println("자신의 계좌번호를 입력 해주세요.");
                    String balAccNo = sc.next();
                    Account balAcc = bank.getAccount(balAccNo);
                    
                    if (balAcc != null) {
                        System.out.println("잔액은 " + balAcc.getBalance() + "입니다.");
                        if (balAcc instanceof MinusAccount) {
                            MinusAccount ma = (MinusAccount) balAcc;
                            System.out.println("신용등급: " + ma.getRating() + "등급");
                            System.out.println("마이너스 한도: " + ma.getCreditLimit());
                            System.out.println("출금 가능액: " + (balAcc.getBalance() + ma.getCreditLimit()));
                        }
                    } else {
                        System.out.println("계좌 번호를 찾을 수 없습니다.");
                    }
                    break;

                case 6: // 은행 자본금 확인
                    System.out.println("은행 자본금: " + bank.geBalance());
                    break;

                case 7: // 생성된 계좌 갯수 확인
                    System.out.println("개설된 계좌 수: " + bank.getAccountCount());
                    break;
                
                case 8: // 계좌 해지 기능 구현
                    System.out.println("해지할 계좌번호를 입력 해주세요.");
                    String delAccNo = sc.next();
                    if (bank.deleteAccount(delAccNo)) {
                        System.out.println("계좌가 정상적으로 해지되었습니다.");
                    } else {
                        System.out.println("해당 계좌를 찾을 수 없습니다.");
                    }
                    break;

                case 9: 
                    System.out.println("프로그램을 종료합니다.");
                    return;

                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}