import java.util.*;

public class ticket_booking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Movie movie = new Movie("Inception", "Sci-Fi", 148);
        Theatre theatre = new Theatre("IMAX", "Downtown", 5, 10);

        while (true){
            System.out.println("\n1. Show Available Seats");
            System.out.println("2. Book a Seat");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1 :
                    theatre.showSeats();
                    break;
                case 2:
                    System.out.print("Enter seat number to book: ");
                    int seatNumber = scanner.nextInt();
                    Seat seat = theatre.getSeat(seatNumber);
                    if(seat != null){
                        Booking booking = new Booking(movie,theatre,seat);
                        booking.confirmBooking();
                    }else {
                        System.out.println("Invalid seat number.");
                    }
                    break;
                case 3:
                    System.out.println("Enter seat number to cancel: ");
                    int cancelSeatnumber = scanner.nextInt();
                    Seat cancelSeat = theatre.getSeat(cancelSeatnumber);
                    if (cancelSeat != null){
                        Booking cancelBooking = new Booking(movie,theatre,cancelSeat);
                        cancelBooking.cancelBooking();
                    }else {
                        System.out.println("Invalid seat number.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the Movie Ticket Booking System! 🎥");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}

class Movie{

    private String name;
    private String genre;
    private int duration;

    public Movie(String name, String genre, int duration){
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public void getDetails(){
        System.out.println("Movie: " + name + "|" + "Genre: " + genre + "|" + "Duration: " + duration );
    }

    public String getName(){
        return name;
    }
}

class Seat{

    protected int seatNumber;
    protected boolean isBooked;

    public Seat(int seatNumber){
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public boolean isBooked(){
        return isBooked;
    }

    public void bookSeat(){
        if(!isBooked){
            isBooked = true;
            System.out.println("Seat " + seatNumber + " booked sucessfully.");
        }else {
            System.out.println("Seat " + seatNumber + " is already booked.");
        }
    }

    public void cancelSeat(){
        if(isBooked){
            isBooked = false;
            System.out.println("Seat " + seatNumber + " canceled successfully.");
        }else{
            System.out.println("Seat " + seatNumber + " is not booked.");
        }
    }

    public double getPrice(){
        return 0.0;
    }

}

class VIPSeat extends Seat{
    private double price;

    public VIPSeat(int seatNumber) {
        super(seatNumber);
        this.price = 15;
    }

    @Override
    public double getPrice(){
        return price;
    }
}

class RegularSeat extends Seat{
    private double price;

    public RegularSeat(int seatNumber) {
        super(seatNumber);
        this.price = 10;
    }

    @Override
    public double getPrice(){
        return price;
    }
}

class Theatre{
    private String name;
    private String location;
    private List<Seat> seats;

    public Theatre(String name, String location, int vipSeats, int regularseats){
        this.name = name;
        this.location = location;
        seats = new ArrayList<>();

        for(int i = 1; i <= vipSeats; i++){
            seats.add(new VIPSeat(i));
        }
        for (int i = vipSeats; i <= vipSeats + regularseats; i++){
            seats.add(new RegularSeat(i));
        }
    }

    public void showSeats(){
        System.out.println("Available seats: ");
        for(Seat seat : seats){
            System.out.println("Seat " + seat.seatNumber + " | Type: " +
                    (seat instanceof VIPSeat ? "VIP" : "Regular") +
                    " | Price: $" + seat.getPrice() + " | " + (seat.isBooked() ? "Booked" : "Available"));
        }
    }

    public Seat getSeat(int seatNumber){
        for(Seat seat : seats){
            if(seat.seatNumber == seatNumber){
                return seat;
            }
        }
        return null;
    }

    public String getName(){
        return name;
    }
}

class Booking{
    private Movie movie;
    private Theatre theatre;
    private Seat seat;
    private double price;

    public Booking(Movie movie, Theatre theater, Seat seat){
        this.movie = movie;
        this.theatre = theater;
        this.seat = seat;
        this.price = seat.getPrice();
    }

    public void confirmBooking(){
        if(!seat.isBooked()){
            seat.bookSeat();
            System.out.println("Booking confirmed for " + movie.getName() +
                    " at " + theatre.getName() + ". Seat: " + seat.seatNumber +
                    " | Price: $" + price);
        }else {
            System.out.println("Seat already booked.");
        }
    }

    public void cancelBooking(){
        seat.cancelSeat();
        System.out.println("Booking canceled for " + movie.getName() + ".");
    }
}