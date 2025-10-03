import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

abstract class MyList {
    
    abstract void addItem(String item);

    abstract void removeItem(String item);
}

public class ShoppingList extends MyList {

    Map<String, Integer> shopList; 
    Scanner sc = new Scanner(System.in);

    public ShoppingList() {
        this.shopList = new TreeMap<>();
    }

    void menu() {
        System.out.print("""
            Welcome to Supermarket Sweep:
            1. Add Item
            2. Remove Item
            3. View List
            4. Exit
                            """
        );
    }

    void selectOption() {

        menu();
        int option = validInput();

        switch (option) {
            case 1 -> addItemMenu();

            case 2 -> removeItemMenu();
            case 3 -> {
                System.out.println(this.toString());
                selectOption();
            }
            case 4 -> {
                System.out.println("Goodbye, your shopping list is:\n");
                System.out.println(this.toString());
            }
        }
    }

    int validInput() {

        String inputString;

        do { 
            System.out.print("Please select an option number: ");
            inputString = sc.nextLine();

        } while (!inputString.equals("1") && !inputString.equals("2") && !inputString.equals("3") && !inputString.equals("4"));

        return Integer.parseInt(inputString);
    }

    void addItemMenu() {

        String item;

        while (true) { 
            System.out.println("Please enter items one by one, or -1 to stop entering: ");
            item = sc.nextLine();

            if (!item.equals("-1")) {
                addItem(item);
            } else {
                break;
            }
        }

        selectOption();
    }
    @Override
    void addItem(String item) {
        if (!item.equals("")) {

            if (shopList.keySet().contains(item)) {
                shopList.put(item, shopList.get(item) + 1);
                System.out.printf("There are now %d %s's in the cart.\n", shopList.get(item), item);
            } else {
                this.shopList.put(item, 1);
                System.out.println("1x " + item + " has been added to the cart.");
            }
        }
    }


    void removeItemMenu() {
        System.out.print("Please enter the item you wish to remove: ");
        String item = sc.nextLine();
        removeItem(item);
        System.out.println(item + " has been removed.");

        selectOption();
    }
    @Override
    void removeItem(String item) {
        try {
            this.shopList.remove(item);
        } catch (Exception e) {
            System.out.println("There was an error");
        }
    }


    @Override
    public String toString() {

        String myString = "Item: Quantity\n";

        for (Map.Entry<String, Integer> entry : shopList.entrySet()) {
            myString += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return myString;
    }

}
