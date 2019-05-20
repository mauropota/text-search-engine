package com.company.searcheng.text.cl;

import com.company.searcheng.text.cl.repository.FileRepository;
import com.company.searcheng.text.cl.repository.resource.Resource;
import com.company.searcheng.text.cl.search.SearchByWordStrategy;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {

        if (args.length == 0) {
            throw new IllegalArgumentException("No dir given to index");
        }

        Scanner keyboard = new Scanner(System.in);
        List<Resource> resources = new FileRepository(args[0]).getResource(".txt");
        System.out.println(resources.size() + " loaded from " + args[0]);
        SearchByWordStrategy searchStrategy = new SearchByWordStrategy();
        TextSearchEngine textSearchEngine = new TextSearchEngine(resources, searchStrategy);

        String input;
        while (true) {
            System.out.print("search> ");
            input = keyboard.nextLine();
            if (input.trim().equals(":quit")) {
                System.out.println("Bye!");
                System.exit(0);
            }
            Map<String, Integer> results = textSearchEngine.getResults(input);
            System.out.println("Found results:");
            results.keySet()
                    .stream()
                    .forEachOrdered(result -> {
                        System.out.println(result + " " + results.get(result));
                    });
        }
    }
}
