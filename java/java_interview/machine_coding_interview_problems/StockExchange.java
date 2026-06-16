package java_interview.machine_coding_interview_problems;

/**
Question

---

**Implement an order matching system for a stock exchange.**

**Features:**

* Traders place Buy and Sell orders for a stock indicating the price and quantity.
* Each order gets entered into the exchange’s order-book and remains there until it is matched. Order matching is attempted whenever a new order is added.
* The exchange follows a FirstInFirstOut Price-Time order-matching rule, which states that: “The first order in the order-book at a price level is the first order matched. All orders at the same price level are filled according to time priority”.
* The exchange works like a market where lower selling prices and higher buying prices get priority.
* A trade is executed when a buy price is greater than or equal to a sell price. The trade is recorded at the price of the sell order regardless of the price of the buy order.

**Requirements:**

1. Add/Register users to the stock exchange system.
2. Add stock to the stock exchange system.
3. Each user should have a stock holding list(stock, price, qty) & balance.
4. Users should be able to add balance to their account.
5. Users should be able to buy/sell multiple stocks.
6. Users should be able to check past successful trading.

**Add-on Requirements:**

1. Users should be able to edit(change buying/selling price & qty)/cancel order.

**Test Cases:**
Write a program that accepts orders as below input and writes trades as below output.

e.g. The following input (format:<order-id> <time> <stock> <buy/sell> <price> <qty>):

#1 09:45 Meesho sell 240.12 100
#2 09:46 Meesho sell 237.45 90
#3 09:47 Meesho buy 238.10 110
#4 09:48 Meesho buy 237.80 10
#5 09:49 Meesho buy 237.80 40
#6 09:50 Meesho sell 236.00 50

Should produce the following output (format:<buy-order-id> <sell-price> <qty> <sell-order-id>):

#3 237.45 90 #2
#3 236.00 20 #6
#4 236.00 10 #6
#5 236.00 20 #6
 * 
 * 
 */

import java.time.LocalTime;
import java.util.*;

public class StockExchange {

    // --- ENUMS & ENTITIES ---

    enum OrderType { BUY, SELL }
    enum OrderStatus { OPEN, PARTIAL, CLOSED, CANCELED }

    static class Order {
        String id;
        LocalTime time;
        String stock;
        OrderType type;
        double price;
        int qty;
        OrderStatus status;
        String userId; // Tied to user requirements

        public Order(String id, LocalTime time, String stock, OrderType type, double price, int qty, String userId) {
            this.id = id;
            this.time = time;
            this.stock = stock;
            this.type = type;
            this.price = price;
            this.qty = qty;
            this.status = OrderStatus.OPEN;
            this.userId = userId;
        }
    }

    // --- THE ORDER BOOK ---

    static class OrderBook {
        String stock;
        // Max-Heap for Buys: Highest price first. If equal, oldest time first.
        PriorityQueue<Order> buyOrders;
        // Min-Heap for Sells: Lowest price first. If equal, oldest time first.
        PriorityQueue<Order> sellOrders;

        public OrderBook(String stock) {
            this.stock = stock;
            this.buyOrders = new PriorityQueue<>((o1, o2) -> {
                int priceCompare = Double.compare(o2.price, o1.price); // Descending
                return priceCompare != 0 ? priceCompare : o1.time.compareTo(o2.time);
            });
            this.sellOrders = new PriorityQueue<>((o1, o2) -> {
                int priceCompare = Double.compare(o1.price, o2.price); // Ascending
                return priceCompare != 0 ? priceCompare : o1.time.compareTo(o2.time);
            });
        }
    }

    // --- THE ENGINE ---

    static class MatchingEngine {
        Map<String, OrderBook> orderBooks = new HashMap<>();
        Map<String, Order> orderRegistry = new HashMap<>(); // For O(1) Edit/Cancel lookup

        public void processOrder(Order newOrder) {
            orderRegistry.put(newOrder.id, newOrder);
            orderBooks.putIfAbsent(newOrder.stock, new OrderBook(newOrder.stock));
            OrderBook book = orderBooks.get(newOrder.stock);

            if (newOrder.type == OrderType.BUY) {
                matchBuyOrder(newOrder, book);
            } else {
                matchSellOrder(newOrder, book);
            }
        }

        private void matchBuyOrder(Order buyOrder, OrderBook book) {
            PriorityQueue<Order> sells = book.sellOrders;

            while (buyOrder.qty > 0 && !sells.isEmpty()) {
                Order bestSell = sells.peek();

                // Lazy deletion check for canceled orders
                if (bestSell.status == OrderStatus.CANCELED) {
                    sells.poll();
                    continue;
                }

                if (buyOrder.price >= bestSell.price) {
                    executeTrade(buyOrder, bestSell);
                    if (bestSell.qty == 0) sells.poll(); // Remove fully matched sell order
                } else {
                    break; // Best sell price is too high, stop matching
                }
            }

            if (buyOrder.qty > 0) {
                book.buyOrders.add(buyOrder);
            }
        }

        private void matchSellOrder(Order sellOrder, OrderBook book) {
            PriorityQueue<Order> buys = book.buyOrders;

            while (sellOrder.qty > 0 && !buys.isEmpty()) {
                Order bestBuy = buys.peek();

                // Lazy deletion check for canceled orders
                if (bestBuy.status == OrderStatus.CANCELED) {
                    buys.poll();
                    continue;
                }

                if (bestBuy.price >= sellOrder.price) {
                    executeTrade(bestBuy, sellOrder);
                    if (bestBuy.qty == 0) buys.poll(); // Remove fully matched buy order
                } else {
                    break; // Best buy price is too low, stop matching
                }
            }

            if (sellOrder.qty > 0) {
                book.sellOrders.add(sellOrder);
            }
        }

        private void executeTrade(Order buy, Order sell) {
            int tradeQty = Math.min(buy.qty, sell.qty);
            
            // As per requirements: "Trade is recorded at the price of the sell order"
            double tradePrice = sell.price;

            buy.qty -= tradeQty;
            sell.qty -= tradeQty;

            buy.status = (buy.qty == 0) ? OrderStatus.CLOSED : OrderStatus.PARTIAL;
            sell.status = (sell.qty == 0) ? OrderStatus.CLOSED : OrderStatus.PARTIAL;

            // Output matching the exact test case format
            // Format: <buy-order-id> <sell-price> <qty> <sell-order-id>
            System.out.printf("%s %.2f %d %s\n", buy.id, tradePrice, tradeQty, sell.id);

            // Note: In a real system, you would update User balances and Holdings here.
        }

        // Add-on: Cancel Order functionality
        public void cancelOrder(String orderId) {
            Order order = orderRegistry.get(orderId);
            if (order != null && (order.status == OrderStatus.OPEN || order.status == OrderStatus.PARTIAL)) {
                order.status = OrderStatus.CANCELED;
            }
        }
    }

    // --- MAIN TEST HARNESS ---

    public static void main(String[] args) {
        MatchingEngine engine = new MatchingEngine();
        
        // Mock input parser for the test cases provided in the image
        String[] inputs = {
            "#1 09:45 Meesho sell 240.12 100",
            "#2 09:46 Meesho sell 237.45 90",
            "#3 09:47 Meesho buy 238.10 110",
            "#4 09:48 Meesho buy 237.80 10",
            "#5 09:49 Meesho buy 237.80 40",
            "#6 09:50 Meesho sell 236.00 50"
        };

        System.out.println("Executing Trades:");
        for (String line : inputs) {
            String[] parts = line.split(" ");
            String id = parts[0];
            LocalTime time = LocalTime.parse(parts[1]);
            String stock = parts[2];
            OrderType type = parts[3].equalsIgnoreCase("buy") ? OrderType.BUY : OrderType.SELL;
            double price = Double.parseDouble(parts[4]);
            int qty = Integer.parseInt(parts[5]);

            Order order = new Order(id, time, stock, type, price, qty, "user_1");
            engine.processOrder(order);
        }
    }
}