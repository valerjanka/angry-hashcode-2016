package com.angry.hashcode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.angry.hashcode.io.FastReader;
import com.angry.hashcode.io.FastWriter;
import com.angry.hashcode.io.input.InputDataSet;
import com.angry.hashcode.model.Drone;
import com.angry.hashcode.model.command.Command;
import com.angry.hashcode.model.command.DeliverCommand;
import com.angry.hashcode.model.command.LoadCommand;
import com.angry.hashcode.model.command.UnloadCommand;
import com.angry.hashcode.model.command.WaitCommand;
import com.google.common.collect.Lists;

/**
 * Created by valerii.ryzhuk on 2/11/2016.
 */
public class Solution {
    private static final Logger LOGGER = LoggerFactory.getLogger(Solution.class);

    private static FastReader in;

    private static FastWriter out;

    public static void main(String[] args) throws Exception {
        try {
            startIO(args);
            solve();
        } finally {
            closeIO();
        }
    }

    private static void solve() throws IOException {
        //        out.print(in.nextLine());
        //        LOGGER.info("I read line '{}' from '{}' and print to '{}'", in.curLine(), in.getFileName(), out.getFileName());
    }

    private static void closeIO() {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }

    private static void startIO(String[] args) throws FileNotFoundException, IOException {
        String inputFileName;
        if (args.length == 0) {
            inputFileName = "input_files/busy_day.in";
        } else {
            inputFileName = args[0];
        }

        InputDataSet inputDataSet = new InputDataSet(inputFileName);
        System.out.println(inputDataSet.getNumRows());
        System.out.println(inputDataSet.getDeadline());
        System.out.println(inputDataSet.getNumProducts());
        System.out.println(inputDataSet.getWarehouse(2).getRow());
        System.out.println(inputDataSet.getWarehouse(2).getAvailableProductIds());
        System.out.println(inputDataSet.getWarehouse(2).getProductAmount(3));
        System.out.println(inputDataSet.getNumOrders());
        System.out.println(inputDataSet.getOrder(3).getNumProductsOrdered());
        System.out.println(inputDataSet.getOrder(3).getOrderedProductIds());
        System.out.println(inputDataSet.getOrder(3).getOrderedProductAmount(inputDataSet.getProduct(377)));
        System.out.println(inputDataSet.getOrder(3).getColumn());

        System.out.println("-----------------");

        Drone drone = new Drone(1);
        List<Command> commands = Lists.newArrayList();
        commands.add(new LoadCommand(drone, inputDataSet.getWarehouse(2), inputDataSet.getProduct(13), 4));
        commands.add(new UnloadCommand(drone, inputDataSet.getWarehouse(6), inputDataSet.getProduct(5), 7));
        commands.add(new DeliverCommand(drone, inputDataSet.getOrder(3), inputDataSet.getProduct(5), 7));
        commands.add(new WaitCommand(drone, 42));
        for (Command c : commands) {
            System.out.println(c.stringRepr());
        }

        //        in = new FastReader(inputFileName);
        //        out = new FastWriter("out.txt");
    }
}
