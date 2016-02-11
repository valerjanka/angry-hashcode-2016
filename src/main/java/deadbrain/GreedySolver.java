package deadbrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import deadbrain.spatial.GenericPoint;
import deadbrain.spatial.KDTree;
import deadbrain.spatial.NearestNeighbors;
import deadbrain.spatial.NearestNeighbors.Entry;
import deadbrain.spatial.RangeSearchTree;

public class GreedySolver {
  
  
  void computeClientScore(
      int P,
      List<Order> clients,
      Map<Integer, KDTree<Integer, GenericPoint<Integer>, Integer>> shit) {
    
    NearestNeighbors<Integer, GenericPoint<Integer>, Integer>
    nearestNeighbours = new NearestNeighbors<>();
  

    // fuck, I don't even consider N items! // TODO: it's ok to be gay
    for (Order client : clients) {
      for (int product : client.orderedItems) {
        Entry<Integer, GenericPoint<Integer>, Integer> nearest =
            nearestNeighbours.get(shit.get(product), new GenericPoint<Integer>(client.x, client.y), 1, true)[0];
        client.score +=  nearest.getDistance2();
      }
    }
  }
  
  public void solve(Problem p ) {
    Map<Integer, KDTree<Integer, GenericPoint<Integer>, Integer>> shit = new HashMap<>();
   
    // init our shit
    for (Order order : p.orders) {
      for (int product : order.orderedItems) {
        if (shit.containsKey(product)) {
          shit.get(product).put(new GenericPoint<>(order.x, order.y), product);
        } else {
          KDTree<Integer, GenericPoint<Integer>, Integer> subshit = 
              new KDTree<Integer, GenericPoint<Integer>, Integer>();
          shit.put(product, subshit);
          subshit.put(new GenericPoint<>(order.x, order.y), product);
        }
        
      }
      
    }
    
    computeClientScore(p.P, p.orders, shit);
    
    p.orders.sort((a,b) -> {
      return (int)Math.signum(a.score - b.score);
    });
    
    

  }
  
  public List<Command> generateCommands(Problem p, List<Order> orders) {
    
    List<Drone> freeDrones = new ArrayList<Drone>();
    List<Drone> buzyDrones = new ArrayList<Drone>();
    // generate drones
    for (int i = 0; i < p.D; i++ ) {
      Drone d = new Drone();
      d.x = p.warehouses.get(0).x;
      d.y = p.warehouses.get(0).y;
      d.turnsLeft = 0;
      freeDrones.add(d);
    }
    
    List<Command> commands = new ArrayList<Command>();
    
    for (int i = 0; i < p.deadline; i++) {
      if (orders.size() > 0 && buzyDrones.size() > 0) {
        if (freeDrones.size() > 0) {
          
        }
      }
    }
    
    return commands;
  }
  
  

  public static void main(String[] args) {
  
   
    
    // 1. find the customer with order with minumum total units, and minimum distance to unit
    // 2. send (closest) drone to pick up his shit to warehouse and deliver that shit
    // 3. cleanse, repeat

  }

}

interface Command  {
  
}

class Load implements Command {
  int droneId;
  int warehouseId;
  int productType;
  int nItems;
}

class Unoad implements Command {
  int droneId;
  int warehouseId;
  int productType;
  int nItems;
}

class Deliver implements Command {
  int droneId;
  int warehouseId;
  int productType;
  int nItems;
}

class Wait implements Command {
  int droneId;
  int turns;
}

class Drone {
  int x;
  int y;
  int turnsLeft;
  int targetX;
  int targetY;
  boolean isLoad;
  
}


class Problem {
  int nRows;
  int nCols;
  int D; // number of drones available
  int deadline; 
  int maxload;
 // int Q; // number of drone commands
  
  
  int P; // # different product types;
  ArrayList<Integer> productWeights = new ArrayList<>();
  
  int W; // number of warehouses
  List<Warehouse> warehouses = new ArrayList<>();
  
  int C; // number of customer orders
  List<Order> orders = new ArrayList<>();
}

class Order {
  int x;
  int y;
  int L; // #of total product items
  ArrayList<Integer> orderedItems = new ArrayList<>();
  double score;
}

class Warehouse {
  int x;
  int y;
  ArrayList<Integer> products = new ArrayList<>();
}
