public class StockDemo {
    public static void main(String[] args) {
        StockManager sm = new StockManager();
        sm.addProduct(1, "a", 10);
        sm.addProduct(2, "b", 20);
        sm.addProduct(2, "b", 50);
        System.out.println("=====================");
        sm.printProductDetails();
        System.out.println("=====================");
        printResult(sm.findProduct(2));
        System.out.println(sm.numberInStock(1));
        sm.delivery(2, 5);
        sm.printLowStockProducts(21);
        printResult(sm.findProductByName("b"));
    }

    static void printResult(Product pd) {
        if (pd == null) {
            System.out.println("无法找到该产品！");
        } else {
            System.out.println("=====================");
            System.out.println("产品信息：");
            System.out.println("id：" + pd.getId());
            System.out.println("name：" + pd.getName());
            System.out.println("number：" + pd.getNumber());
            System.out.println("=====================");
        }
    }
}

class Product {
    private int id;
    private String name;
    private int number;

    Product(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    void increaseQuantity(int increaseNumber) {
        number += increaseNumber;
    }

    void sellOne() {
        number--;
    }

    void getProductDetails() {
        System.out.println(id + "     " + name + "       " + number);
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getNumber() {
        return number;
    }

}

class StockManager {
    private int index;

    Product[] products = new Product[100];

    StockManager() {
        index = 0;
    }

    void addProduct(int id, String name, int number) {
        boolean add = true;
        Product pd = new Product(id, name, number);
        if (index < 10) {
            for (int i = 0; i < index; i++) {
                if (products[i].getId() == id) {
                    add = false;
                    System.out.println("库中已有id为" + id + "的产品，请勿重复添加！");
                    break;
                }
            }
            if (add) {
                products[index] = pd;
                System.out.println("id为" + id + "的产品入库成功！数量：" + number + "件");
                index++;
            }
        }
    }

    void printProductDetails() {
        System.out.println("开始打印库中所有产品信息...");
        System.out.println("id    name    number");
        for (int i = 0; i < index; i++) {
            products[i].getProductDetails();
        }
        System.out.println("打印完毕！");
    }

    Product findProduct(int id) {
        boolean temp = false;
        int i;
        for (i = 0; i < index; i++) {
            if (products[i].getId() == id) {
                temp = true;
                break;
            }
        }
        if (temp) {
            return products[i];
        } else {
            return null;
        }
    }

    int numberInStock(int id) {
        boolean temp = false;
        int i;
        for (i = 0; i < index; i++) {
            if (products[i].getId() == id) {
                temp = true;
                break;
            }
        }
        if (temp) {
            return products[i].getNumber();
        } else {
            return 0;
        }
    }

    void delivery(int id, int increaseNumber) {
        for (int i = 0; i < index; i++) {
            if (products[i].getId() == id) {
                products[i].increaseQuantity(increaseNumber);
            }
        }
    }

    void printLowStockProducts(int kucun) {
        for (int i = 0; i < index; i++) {
            if (products[i].getNumber() < kucun) {
                System.out.println(products[i].getId() + "     " + products[i].getName() + "       " + products[i].getNumber());
            }
        }
    }

    Product findProductByName(String name) {
        boolean temp = false;
        int i;
        for (i = 0; i < index; i++) {
            if (products[i].getName() == name) {
                temp = true;
                break;
            }
        }
        if (temp) {
            return products[i];
        } else {
            return null;
        }
    }

}