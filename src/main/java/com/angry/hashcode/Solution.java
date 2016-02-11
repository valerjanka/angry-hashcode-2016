package com.angry.hashcode;

import com.angry.hashcode.io.FastReader;
import com.angry.hashcode.io.FastWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        out.print(in.nextLine());
        LOGGER.info("I read line '{}' from '{}' and print to '{}'", in.curLine(), in.getFileName(), out.getFileName());
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

    private static void startIO(String[] args) throws FileNotFoundException
    {
        String inputFileName;
        if ( args.length == 0 )
        {
            inputFileName = "in.txt";
        }
        else
        {
            inputFileName = args[0];
        }
        in = new FastReader(inputFileName);
        out = new FastWriter("out.txt");
    }
}
