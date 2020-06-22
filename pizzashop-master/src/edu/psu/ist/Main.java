package edu.psu.ist;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: Meetkumar Patel
Date Developed: 3/14/19
Last Date Changed: 6/21/20
Rev: 4
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private int cCount;
    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {

        Main main = new Main();
        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Order order1 = new Order(1);
        Transaction trans1 = new Transaction(1, order1, PaymentType.cash);
        Transaction trans2 = new Transaction(2, order1, PaymentType.credit); //Second Payment type (Credit)

        Menu menu1 = new Menu(1, "Plain: $5.99");
        Menu menu2 = new Menu(2, "Meat: $8.99");
        Menu menu3 = new Menu(3, "Extra: $7.99");
        Menu menu4 = new Menu(4, "Veg: $6.99");
        Menu menu5 = new Menu(5, "Pepperoni: $7.50"); //New to Menu

        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);
        mList.add(menu5);

        oList.add(order1);
        tList.add(trans1);
        tList.add(trans2);

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch (userAction) {
                case CUST_CODE:
                    cList.add(main.addCustomer());
                    break;
                case CUST_PRNT:
                    Customer.printCustomer(cList);
                    break;
                case MENU_CODE:
                    Menu.listMenu(mList);
                    break;
                case ORDE_CODE:
                    System.out.print("Enter customer ID: ");
                    int cID = scnr.nextInt();
                    ArrayList<Menu> cMenu = selectMenu(mList);
                    Order.addOrders(order1, cList.get(cID), cMenu);
                    Order order = Order.addOrders(mList, cList);
                    PaymentType paymentType = Transaction.getTransactType();
                    oList.add(order);
                    Transaction transaction = new Transaction(paymentType, order);
                    tList.add(transaction);
                    break;
                case TRAN_CODE:
                    Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    public static ArrayList<Menu> selectMenu(ArrayList<Menu> menu1) {
        System.out.println("Select menu by selecting ID, next press 10 for finalizing the order");
        for (Menu menu : menu1)
            System.out.println("'" + menu.getmenuId() + "' for " + menu.getmenuItem());
        int flag;
        ArrayList<Menu> menu2 = new ArrayList<>();
        while (true) {
            flag = scnr.nextInt();
            if (flag == 10)
                break;
            menu2.add(menu1.get(flag - 1));
        }
        return menu2;
    }

    public static char getAction(String prompt) {
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer() {
        Customer cust = new Customer(cCount++);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }
}