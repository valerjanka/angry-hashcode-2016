package deadbrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    for (Warehouse w : p.warehouses) {
     for (int product = 0; product < w.products.size(); product++) {
        if (w.products.get(product) > 0) {
        if (shit.containsKey(product)) {
          shit.get(product).put(new GenericPoint<>(w.x, w.y), product);
        } else {
          KDTree<Integer, GenericPoint<Integer>, Integer> subshit = 
              new KDTree<Integer, GenericPoint<Integer>, Integer>();
          shit.put(product, subshit);
          subshit.put(new GenericPoint<>(w.x, w.y), product);
        }
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
      d.id = i;
      d.x = p.warehouses.get(0).x;
      d.y = p.warehouses.get(0).y;
      d.turnsLeft = 0;
      freeDrones.add(d);
    }
    
    List<Command> commands = new ArrayList<Command>();
    
    List<Shit> shits = new ArrayList<>();
    
    List<Shit> warehouseShit = new ArrayList<>();
    
    for (int orderId = 0; orderId < p.orders.size(); orderId++) {
      Order order = p.orders.get(orderId);
      for (int product : order.orderedItems) {
         Shit shit = new Shit();
         shit.id = orderId;
         shit.x = order.x;
         shit.y = order.y;
         shit.p = product;
         warehouseShit.add(shit);
      }
    }
    
   

    
    for (int i = 0; i < p.deadline; i++) {
      if (shits.size() > 0 && buzyDrones.size() > 0) {
        if (freeDrones.size() > 0) {
          Shit shit = shits.remove(0);
          Drone drone = freeDrones.remove(0);
          drone.targetX = shit.x;
          drone.targetY = shit.y;
          
          // we need to find shit to deliver:
          Shit toBeDelivered = null;
          for (int j = 0; j < warehouseShit.size(); i++) {
            if (warehouseShit.get(j).p == shit.p) {
               toBeDelivered = warehouseShit.remove(j);
              drone.warehouseX = toBeDelivered.x;
              drone.warehouseY = toBeDelivered.y;
              drone.turnsLeft = dist(drone.x, shit.x, drone.y, shit.y);
              break;
            }
          }
          
          
          
         // drone.turnsLeft = 1 + dist(drone.x, shit.x, drone.y, shit.y); // wrong find closes warehouseid, that has item;
          drone.isLoad = true;
          drone.p = shit.p;
          buzyDrones.add(drone);
          Load load = new Load();
          load.droneId = drone.id;
          load.nItems = 1;
          load.productType = shit.p;
          load.warehouseId = toBeDelivered.id;
          drone.p = shit.p;
        // FIXME:  load.warehouseId = shit
        }
        HashSet<Integer> toBeFreed = new HashSet<>();
        for (Drone d : buzyDrones) {
          if (d.isLoad) {
            if (d.turnsLeft == 0) {
              Unload unload = new Unload();
              unload.droneId = d.id;
              unload.nItems = 1;
              unload.productType = d.p;
              commands.add(unload);
              d.isLoad = false;
              d.turnsLeft = 1 + dist(d.x, d.targetX, d.y, d.targetY);
            } else {
              d.turnsLeft -=1;
            }
          } else {
            if (d.turnsLeft == 0) {
              freeDrones.add(0, d);
              toBeFreed.add(d.id);
            } else {
              d.turnsLeft -= 1;
            }
          }
        }
        // fuck
        buzyDrones = buzyDrones.stream().filter(d -> !toBeFreed.contains(d.id)).collect(Collectors.toList());
      }
    }
    
    return commands;
  }
  
  public int dist(int x1, int x2, int y1, int y2) {
    return (int) Math.sqrt(  (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }
  

  public static void main(String[] args) {
  
   
    
    // 1. find the customer with order with minumum total units, and minimum distance to unit
    // 2. send (closest) drone to pick up his shit to warehouse and deliver that shit
    // 3. cleanse, repeat

  }

}

class Shit {
  public int id;
  int x;
  int y;
  int p;
}

interface Command  {
  
}

class Load implements Command {
  int droneId;
  int warehouseId;
  int productType;
  int nItems;
}

class Unload implements Command {
  int droneId;
  int orderId;
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
  int id;
  int x;
  int y;
  int turnsLeft;
  int targetX; // target is order
  int targetY;
  int warehouseX;
  int warehouseY;
  int p;
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
