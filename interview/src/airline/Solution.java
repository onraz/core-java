package airline;
/*
 * Click `Run` to execute the snippet below!
 */

import java.util.List;

/*
You're building a tool to estimate the cost of various airplane tickets based on the airline, distance and seating class. Your tool must take in this information as a series of inputs (one ticket calculation per line of input) and produce a list of output costs.

Each airline contains its own cost requirements. Ultimately, the airline is only interested in two major components: the space (seating class) you take on the plane, and the distance you fly. You must generate ticket costs using this gathered data:

Airlines: United, Delta, Southwest, LuigiAir

Operating Costs:

 - Economy:  No charge
 - Premium:  $25
 - Business: $50 + $0.25/mile

Per-Airline Prices:

 - Delta charges $0.50/mile
   + OperatingCost

 - United charges $0.75/mile
   + OperatingCost
   + $0.10/mile for Premium seats

 - Southwest charges $1.00/mile

 - LuigiAir charges $100 or 2 * OperatingCost, whichever is higher

Keep in mind that, while there are only four airlines listed above, your solution should be able to expand to dozens of individual airlines,  whose ticket cost can be based on arbitrary functions of "Operating Costs", miles, and/or seating class.

You can assume that the input will be provided as a list of strings and that there could be millions of lines of input. Each string will provide the Airline, Distance and Seating Class. Please review the examples below:

Example Input:
-------------------------------------------
United 150.0 Premium
Delta 60.0 Business
Southwest 1000.0 Economy
LuigiAir 50.0 Business
-------------------------------------------

Example Output:
-------------------------------------------
152.50
95.00
1000.00
125.00
-------------------------------------------

Explanation of Output:
-------------------------------------------
152.50      (150.0 * (0.75 + 0.10) + 25)
95.00       (60.0 * (0.50 + 0.25) + 50)
1000.00     (1000.0 * 1.00)
125.00      (100 <= 2 * (50 + 50 * 0.25))
-------------------------------------------
*/
enum SeatingClass {
    ECONOMY, BUSINESS, PREMIUM;
}

interface Airlines {
    double calculateCost(double miles, SeatingClass seatingClass);
}

class OperatingCost {
    public static double operatingCost(SeatingClass seatingClass, double miles) {
        return switch (seatingClass) {
            case ECONOMY -> 0;
            case PREMIUM -> 25;
            case BUSINESS -> 50 + 0.25 * miles;
        };
    }
}

class DeltaAirlines implements Airlines {
    /*
     - Delta charges $0.50/mile
     + OperatingCost
     */
    @Override
    public double calculateCost(double miles, SeatingClass seatingClass) {
        return 0.5 * miles + OperatingCost.operatingCost(seatingClass, miles);
    }
}

class UnitedAirlines implements Airlines {
    /*
     - United charges $0.75/mile
     + OperatingCost
     + $0.10/mile for Premium seats
     */
    @Override
    public double calculateCost(double miles, SeatingClass seatingClass) {
        return 0.75 * miles + OperatingCost.operatingCost(seatingClass, miles)
                + (seatingClass == SeatingClass.PREMIUM ? 0.10 * miles : 0);
    }
}

class SouthwestAirlines implements Airlines {
    /*
      - Southwest charges $1.00/mile
      - LuigiAir charges $100 or 2 * OperatingCost, whichever is higher
     */
    @Override
    public double calculateCost(double miles, SeatingClass seatingClass) {
        return 1.0 * miles + OperatingCost.operatingCost(seatingClass, miles);
    }
}

class LuigiAirlines implements Airlines {
    /*
      - LuigiAir charges $100 or 2 * OperatingCost, whichever is higher
     */
    @Override
    public double calculateCost(double miles, SeatingClass seatingClass) {
        return Math.max(100 , 2 * OperatingCost.operatingCost(seatingClass, miles));
    }
}

@FunctionalInterface
interface CostFunction {
    double apply(double miles, SeatingClass seatingClass);
}

record StdCostFunction(double milesMulti, double opertingMulti, double premiumMulti) implements CostFunction {
    StdCostFunction(double milesMulti) {
        this(milesMulti, 1.0, 0.0);
    }

    StdCostFunction(double milesMulti, double opertingMulti) {
        this(milesMulti, opertingMulti, 0.0);
    }

    @Override
    public double apply(double miles, SeatingClass seatingClass) {
        return milesMulti * miles + opertingMulti * OperatingCost.operatingCost(seatingClass, miles)
                + (seatingClass == SeatingClass.PREMIUM ? premiumMulti * miles : 0);
    }
}

/**
 Per-Airline Prices:

 - Delta charges $0.50/mile
 + OperatingCost

 - United charges $0.75/mile
 + OperatingCost
 + $0.10/mile for Premium seats

 - Southwest charges $1.00/mile

 - LuigiAir charges $100 or 2 * OperatingCost, whichever is higher
 */
enum Airline {
    DELTA(new StdCostFunction(0.5)),
    UNITED(new StdCostFunction(0.75, 1.0, 0.1)),
    SW(new StdCostFunction(1.0)),
    LUIGI((m, s) -> Math.max(100 , 2 * OperatingCost.operatingCost(s, m)))
    ;

    private final CostFunction costFun;

    Airline(CostFunction costFun) {
        this.costFun = costFun;
    }

    CostFunction costFun() {
        return costFun;
    }
}

record Ticket(Airline airline, double miles, SeatingClass seatingClass) {
    public double calcCost() {
        return airline.costFun().apply(miles, seatingClass);
    }
}

interface Solution {
    static void main(String[] args) {
    /*
    Example Input:
      -------------------------------------------
      United 150.0 Premium
      Delta 60.0 Business
      Southwest 1000.0 Economy
      LuigiAir 50.0 Business
      -------------------------------------------
     */
        System.out.println(new UnitedAirlines().calculateCost(150, SeatingClass.PREMIUM));
        System.out.println(Airline.UNITED.costFun().apply(150, SeatingClass.PREMIUM));
        System.out.println(new DeltaAirlines().calculateCost(60, SeatingClass.BUSINESS));
        System.out.println(Airline.DELTA.costFun().apply(60, SeatingClass.BUSINESS));
        System.out.println(new SouthwestAirlines().calculateCost(1000, SeatingClass.ECONOMY));
        System.out.println(Airline.SW.costFun().apply(1000, SeatingClass.ECONOMY));
        System.out.println(new LuigiAirlines().calculateCost(50, SeatingClass.BUSINESS));
        System.out.println(Airline.LUIGI.costFun().apply(50, SeatingClass.BUSINESS));


        List<Ticket> tickets = List.of(
                new Ticket(Airline.UNITED, 150, SeatingClass.PREMIUM),
                new Ticket(Airline.DELTA, 60, SeatingClass.BUSINESS),
                new Ticket(Airline.SW, 1000, SeatingClass.ECONOMY),
                new Ticket(Airline.LUIGI, 50, SeatingClass.BUSINESS)
        );

        tickets.stream().map(Ticket::calcCost).forEachOrdered(System.out::println);
    }
}

