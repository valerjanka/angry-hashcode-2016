package com.angry.hashcode;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.angry.hashcode.io.FastReader;
import com.angry.hashcode.io.FastWriter;
import com.angry.hashcode.io.input.InputDataSet;

/**
 * Created by valerii.ryzhuk on 2/11/2016.
 */
public class Solution
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Solution.class);

    private static FastReader in;
    private static FastWriter out;

    public static void main(String[] args) throws Exception
    {
        try
        {
            startIO(args);
            solve();
        }
        finally
        {
            closeIO();
        }
    }

    private static void solve() throws IOException
    {
//        out.print(in.nextLine());
//        LOGGER.info("I read line '{}' from '{}' and print to '{}'", in.curLine(), in.getFileName(), out.getFileName());
    }

    private static void closeIO()
    {
        if ( in != null )
        {
            in.close();
        }
        if ( out != null )
        {
            out.close();
        }
    }

    private static void startIO(String[] args) throws FileNotFoundException, IOException
    {
        String inputFileName;
        if ( args.length == 0 )
        {
            inputFileName = "input_files/busy_day.in";
        }
        else
        {
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
        System.out.println(inputDataSet.getOrder(3).getOrderedProductAmount(377));
        System.out.println(inputDataSet.getOrder(3).getColumn());

//        in = new FastReader(inputFileName);
//        out = new FastWriter("out.txt");
    }
}
