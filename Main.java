public class Main {
    public static void main(String[] args) {
        InventorySystem sistemInventaris = new InventorySystem();

        sistemInventaris.mainProcess();

        if (sistemInventaris.isMainProcess()) {
            int menuOption;
            do {
                sistemInventaris.menu();
                menuOption = sistemInventaris.getMenuOption();

                switch (menuOption) {
                    case 1:
                        sistemInventaris.showInventory();
                        break;
                    case 2:
                        sistemInventaris.addItem();
                        break;
                    case 3:
                        sistemInventaris.updateStock();
                        break;
                    case 4:
                        sistemInventaris.useItem();
                        break;
                    case 5:
                        sistemInventaris.searchItem();
                        break;
                    case 6:
                        sistemInventaris.generateReport();
                        break;
                    case 7:
                        sistemInventaris.deleteItem();
                        break;
                    case 8:
                        sistemInventaris.displayItemsToRestock();
                        break;
                    case 9:
                        System.out.println("Keluar dari program...");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            } while (menuOption != 8);
        }
    }
}
