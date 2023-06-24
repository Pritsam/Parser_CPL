package de.hft_stuttgart.cpl;

import java.util.LinkedList;
import java.util.Queue;

public class ParseQueue {

    private Queue<String> queue = new LinkedList<>();

    public void add(String lineToParse) {
        queue.add(lineToParse);
    }

    public String next() {
        return queue.poll();
    }
}