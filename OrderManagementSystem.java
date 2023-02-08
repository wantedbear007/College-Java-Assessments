// package OMS;
import java.util.*;

public class OrderManagementSystem {

    String product;
    int quantity;
    float price;
    
    // boolean postStatus = false;
    // product status is PENDING by default

    // register product
    public OrderManagement registerProduct() {
        int loop = 1;
        System.out.println("Welcome to OrderManagement System");
        OrderManagement postOrder = new OrderManagement();
        while (loop != 0) {

            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Register new product: ");   
                System.out.println("Product name: ");
                product = sc.nextLine();    
                System.out.println("Enter product quantity: ");
                quantity = sc.nextInt();
                System.out.println("Price of the product: ");
                price = sc.nextFloat();
                Order storeOrder = new Order(product, quantity, price);
                postOrder.addOrder(storeOrder);
                System.out.println("Data Entered successfully !");
                System.out.println("Enter 2 to enter more 0 to exit");
                loop = sc.nextInt();
                if (loop == 0) {
                    break;
                } 
            }

     catch (Exception e) { 

        System.out.println("Error occurred" + e.getMessage());
        System.exit(-1);
     }
    };
    return postOrder;
    }

    // to update order
    void updateOrder(OrderManagement operations) {

    }

    // provide all order list
    void allOrders(OrderManagement operations) {

    }

    // to get rejected order
    void getRejectedOrder(OrderManagement operations) {

    }

    // get order by category
    void getOrderByCategory(OrderManagement operations) {

    }

    // get specific order details
    void getSpecificDetail(OrderManagement operations) {

    }

    public static void main(String[] args) {

        OrderManagementSystem orderManagementSystem = new OrderManagementSystem();
        OrderManagement operations = orderManagementSystem.registerProduct();
        
        int userInput;
        System.out.println("Check from the following");
        System.out.println("\n1. To update Order status. \n2. To get all orders list.\n 3. To get rejected orders.\n 4. To get order by category.\n 5. To get specific order details.");
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextInt();
        switch (userInput) {
            case 1: {
                orderManagementSystem.updateOrder(operations);
            }
            case 2: {
                orderManagementSystem.allOrders(operations);
            }
            case 3: {
                orderManagementSystem.getRejectedOrder(operations);
            }
            case 4: {
                orderManagementSystem.getOrderByCategory(operations);
            }
            case 5: {
                orderManagementSystem.getSpecificDetail(operations);
            }
            default: {
                System.out.println("Wrong Input bye.");
            }
        }
    }
}


// Order management
class OrderManagement  {

    // custom list
    List<Order> orders;

    public OrderManagement () {
        this.orders = new ArrayList<>();
    }

    // order item
    public boolean addOrder(Order order) {
        try {
            this.orders.add(order);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    // checking order status
    public OrderStatus orderStatus(Order order) {
        final int orderPosition = this.orders.indexOf(order);

        if (orderPosition != -1) {
            return order.status;
        }
        else return null;
    }

    // updating order status
    public boolean updateOrderStatus(Order order, OrderStatus orderStatus) {
        final int orderPosition = this.orders.indexOf(order);
        try {
            if (orderPosition != -1) {
                this.orders.get(orderPosition).status = orderStatus;
                return true;
            } else return false;
        } catch (Exception e) {

            return false;
            // do something
        }
    }

    // get all orders
    public List<Order> getAllOrders() {
        return orders;
    }
    
    // get rejected orders
    public List<Order> getRejectedOrders() {
        try {
            List<Order> rejectedOrders = new ArrayList<>();
            for (Order order : this.orders) {
                if (order.status == OrderStatus.REJECTED) {
                    rejectedOrders.add(order);
                }
            }
            return rejectedOrders;
            
        } catch (Exception e) {
            // do something
            return null;
        }
    }

    // get entire description of the product
    public String getOrderDetails(Order order) {
        final int orderIndex = this.orders.indexOf(order);
        Order selectOrder = this.orders.get(orderIndex);
        return selectOrder.toString();
    }
    
    // get order by status category
    public List<Order> getOrderByCategory(OrderStatus orderStatus) {
            List<Order> categoryOrder = new ArrayList<>();
        
            if (this.orders.size() <= -1) {
                return null;
            } else {
                for (Order order : this.orders) {
                    if (order.status == orderStatus) {
                        categoryOrder.add(order);
                    } else {};
                }
            }
            return categoryOrder;
    }

    // update price of an order 
    public boolean updateOrderPrice(Order order, float updatedPrice) {
        if (this.orders.size() <= -1) { 
            return false;
        } else {
            for (Order odr : this.orders) {
                if (odr == order) {
                    odr.price = updatedPrice;
                    return true;
                }
            }
            return false;
        }

    }

}


enum OrderStatus {
    COMPLETED,
    PENDING,
    REJECTED,
    PARTIAL,
}

class Order {
    String product;
    int quantity;
    // private boolean status;
    OrderStatus status;
    float price;

    public Order(String product, int quantity, float price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.PENDING;
    }

    @Override
    public String toString() {
        String productDescription = "Product Name: " + this.product + " Quantity is: " + this.quantity + " Status is: " + this.status + " Price is: " + this.price;
        return productDescription;
    }
}