import java.util.*;

public class OrderManagementSystem {

    String product;
    int quantity;
    float price;

    // boolean postStatus = false;
    // product status is PENDING by default

    // to retrieve all orders 
    void allOrders(OrderManagement operations) {
        List <Order> allOrders = operations.getAllOrders();
        if (allOrders.size() <= 0) {
            System.out.println("No orders found.");
        }
        else {

            System.out.println("Orders are:\n");
            for (Order or : allOrders) {
                System.out.println("Order: " + or);
            }
        }

    }

     // to get rejected order
     void getRejectedOrder(OrderManagement operations) {
        List <Order> rejOrder = operations.getRejectedOrders();
        if (rejOrder == null) {
            System.out.println("Something went wrong! Try again later.");
        }
        else if (rejOrder.size() <= 0) {
            System.out.println("No rejected orders as of now.");
        }
        else {
            System.out.println("Rejected Orders Are as follows: ");
            for (Order order : rejOrder) {
                System.out.println("Order: " + order);
            }
        }

     }
 
     // get order by category
     void getOrderByCategory(OrderManagement operations) {
        System.out.println("Enter the category: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. COMPLETED\n2. PENDING\n3. REJECTED\n4. PARTIAL");
        int userInput = sc.nextInt();
        switch (userInput) {
            case 1: operations.getOrderByCategory(OrderStatus.COMPLETED);
            break;
            case 2: operations.getOrderByCategory(OrderStatus.PENDING);
            break;
            case 3: operations.getOrderByCategory(OrderStatus.REJECTED);
            break;
            case 4: operations.getOrderByCategory(OrderStatus.PARTIAL);
            break;
            default: {
                System.out.println("Invalid Input.");
            }
        }
 
     }

    //  to update order
    void updateOrder(OrderManagement operations) {
        List <Order> allOrders = operations.getAllOrders();
        int counter = 0;
        if (allOrders.size() <= 0) {
            System.out.println("No orders to update.");
        } else {
            System.out.println("Select Order to Update Status: \n");

            for (Order order : allOrders) {
                System.out.println(counter+ " " + order);
                counter++;
            }

            int userInput = sc.nextInt();
           Order order = allOrders.get(userInput);
           System.out.println("Select from the following status: ");
        System.out.println("1. COMPLETED\n2. PENDING\n3. REJECTED\n4. PARTIAL");
            int userInput2 = 9;

           switch (userInput2) {
            case 1: operations.updateOrderStatus(order, OrderStatus.COMPLETED);
            break;
            case 2: operations.updateOrderStatus(order, OrderStatus.PENDING);
            break;
            case 3: operations.updateOrderStatus(order, OrderStatus.REJECTED);
            break;
            case 4: operations.updateOrderStatus(order, OrderStatus.PARTIAL);
            break;
            default: {
                System.out.println("Invalid Input.");
            }
        }
 
            
        }
    }
 
     // get specific order details
     void getSpecificDetail(OrderManagement operations) {
 
     }

    void registerProduct(OrderManagement orderManagement) {
        Scanner sc = new Scanner(System.in);

                    System.out.println("Register new product: ");
                    System.out.println("Product name: ");
                    product = sc.nextLine();
                    System.out.println("Enter product quantity: ");
                    quantity = sc.nextInt();
                    System.out.println("Price of the product: ");
                    price = sc.nextFloat();
                    Order storeOrder = new Order(product, quantity, price);
                    orderManagement.addOrder(storeOrder);
                    System.out.println("Data Entered successfully !");
             
    }


    public static void main(String[] args) {

        OrderManagementSystem orderManagementSystem = new OrderManagementSystem();
        // OrderManagement operations = orderManagementSystem.registerProduct();
        OrderManagement orderManagement = new OrderManagement();

        int userInput = 9;
        System.out.println("\n\n\n--Welcome to Order Management System--\n");
        System.out.println("Check from the following");

        try (Scanner sc = new Scanner(System.in)) {
            while(userInput != 0) {
                System.out.println("\n 1. To update Order status. \n 2. To get all orders list.\n 3. To get rejected orders.\n 4. To get order by category.\n 5. To register a new product.\n 6. To exit");
                userInput = sc.nextInt();
                switch (userInput) {
                    case 1: orderManagementSystem.updateOrder(orderManagement);
                    break;
                    case 2: orderManagementSystem.allOrders(orderManagement);
                    break;
                    case 3: orderManagementSystem.getRejectedOrder(orderManagement);
                    break;
                    case 4: orderManagementSystem.getOrderByCategory(orderManagement);
                    break;
                    // case 5: orderManagementSystem.getSpecificDetail(orderManagement);
                    // break;
                    case 5: orderManagementSystem.registerProduct(orderManagement);
                    break;
                    case 6: {
                        System.out.println("Program is exiting !");
                        System.exit(0);
                    }
                    default: System.out.println("Invalid input! Try Again");
            }
            }
            
           
        }


    }


    Scanner sc = new Scanner(System.in);



    // provide all order list
   

   

    // void registerNewProduct(OrderManagementSystem orderManagementSystem) {
    //     orderManagementSystem.registerProduct();
    // }
}



// Order management
class OrderManagement {

    // custom list
    static List < Order > orders;

    public OrderManagement() {
        orders = new ArrayList < > ();
    }

    // order item
    public boolean addOrder(Order order) {
        try {
            this.orders.add(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // checking order status
    public OrderStatus orderStatus(Order order) {
        final int orderPosition = this.orders.indexOf(order);

        if (orderPosition != -1) {
            return order.status;
        } else return null;
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
    // public boolean updateOrderStatus(OrderStatus orderstatus) {
    //     this.orders.
    // }

    // get all orders
    public List < Order > getAllOrders() {
        return orders;
    }

    // get rejected orders
    public List < Order > getRejectedOrders() {
        try {
            List < Order > rejectedOrders = new ArrayList < > ();
            for (Order order: this.orders) {
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
    public void getOrderByCategory(OrderStatus orderStatus) {
        List < Order > categoryOrder = new ArrayList < > ();

        if (this.orders.size() <= -1) {
            System.out.println("No orders found.");
        } else {
            for (Order order: orders) {
                if (order.status == orderStatus) {
                    categoryOrder.add(order);
                } else {};
            }
        }

        if (categoryOrder.size() <= 0) {
            System.out.println("No orders found.");
        } else {
            
            System.out.println("Orders are:\n");
            for (Order order : categoryOrder) {
                System.out.println(order);
            }
        }

        // return categoryOrder;
    }

    // update price of an order 
    public boolean updateOrderPrice(Order order, float updatedPrice) {
        if (this.orders.size() <= -1) {
            return false;
        } else {
            for (Order odr: this.orders) {
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