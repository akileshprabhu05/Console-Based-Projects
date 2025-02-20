import java.util.*;

class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "User ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}

class UserManager {
    private static UserManager instance;
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;
    private UserManager() {}

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User registerUser(String name, String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered! Try logging in.");
                return null;
            }
        }
        User newUser = new User(users.size() + 1, name, email, password);
        users.add(newUser);
        loggedInUser = newUser;
        System.out.println("User registered successfully! Your User ID: " + newUser.getId());
        return newUser;
    }

    public boolean loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome, " + user.getEmail());
                return true;
            }
        }
        System.out.println("Invalid email or password. Try again.");
        return false;
    }

    public void logoutUser() {
        loggedInUser = null;
        System.out.println("Logged out successfully!");
    }
    
    public User getLoggedInUser() {
        return loggedInUser;
    }
}

class ServicePlan {
    private int id;
    private String name;
    private String speed;
    private double price;

    public ServicePlan(int id, String name, String speed, double price) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Plan ID: " + id + " | " + name + " - Speed: " + speed + " - Price: ₹" + price;
    }
}

class Subscription {
    private int userId;
    private int planId;
    private String status;

    public Subscription(int userId, int planId) {
        this.userId = userId;
        this.planId = planId;
        this.status = "Active";
    }

    public String toString() {
        return "User ID: " + userId + " subscribed to Plan ID: " + planId + " - Status: " + status;
    }
}

class Feedback {
    private int userId;
    private String comments;
    private int rating;

    public Feedback(int userId, String comments, int rating) {
        this.userId = userId;
        this.comments = comments;
        this.rating = rating;
    }

    public String toString() {
        return "User ID: " + userId + " | Rating: " + rating + " | Comments: " + comments;
    }
}

public class BroadbandApp {
    private static final List<ServicePlan> servicePlans = new ArrayList<>();
    private static final List<Subscription> subscriptions = new ArrayList<>();
    private static final List<Feedback> feedbackList = new ArrayList<>();
    private static final UserManager userManager = UserManager.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        servicePlans.add(new ServicePlan(1, "Basic", "50Mbps", 499.99));
        servicePlans.add(new ServicePlan(2, "Premium", "100Mbps", 799.99));
        servicePlans.add(new ServicePlan(3, "Ultra", "1Gbps", 1499.99));

        boolean flag = true;

        while (flag) {
            System.out.println("\n--- Broadband Service App ---");

            if (userManager.getLoggedInUser() == null) {
                System.out.println("1. Register User");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        userManager.registerUser(name, email, password);
                        break;

                    case 2:
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                        userManager.loginUser(email, password);
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        flag = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Welcome, " + userManager.getLoggedInUser().getEmail());
                System.out.println("1. View Service Plans");
                System.out.println("2. Subscribe to Plan");
                System.out.println("3. Provide Feedback");
                System.out.println("4. View Subscriptions");
                System.out.println("5. View Feedbacks");
                System.out.println("6. Logout");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nAvailable Service Plans:");
                        for (ServicePlan plan : servicePlans) {
                            System.out.println(plan);
                        }
                        break;

                    case 2:
                        System.out.print("Enter Plan ID to subscribe: ");
                        int planId = scanner.nextInt();
                        scanner.nextLine();
                        subscriptions.add(new Subscription(userManager.getLoggedInUser().getId(), planId));
                        System.out.println("Subscription successful!");
                        break;

                    case 3:
                        System.out.print("Enter comments: ");
                        String comments = scanner.nextLine();
                        System.out.print("Enter rating (1-5): ");
                        int rating = scanner.nextInt();
                        scanner.nextLine();
                        feedbackList.add(new Feedback(userManager.getLoggedInUser().getId(), comments, rating));
                        System.out.println("Feedback submitted successfully!");
                        break;

                    case 4:
                        for (Subscription sub : subscriptions) {
                            System.out.println(sub);
                        }
                        break;

                    case 5:
                        for (Feedback fb : feedbackList) {
                            System.out.println(fb);
                        }
                        break;

                    case 6:
                        userManager.logoutUser();
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}
