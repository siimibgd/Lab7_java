package ex1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ex1.Carte;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void scriere(Map<Integer, Carte> carteMap) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/carte.json");
            mapper.writeValue(file,carteMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<Integer, Carte> citire() {
        try {
            File file=new File("src/main/resources/carti.json");
            ObjectMapper mapper=new ObjectMapper();
            Map<Integer, Carte> carteMap = mapper
                    .readValue(file, new TypeReference<Map<Integer, Carte>>(){});
            return carteMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Map<Integer, Carte> carteMap = new HashMap<>();
        carteMap=citire();

        /*1*/
        for(var o:carteMap.entrySet()){
            System.out.println(o);
        }

        /*2*/
        System.out.println("\nCe carte vrei sa stergi?");
        Scanner scanner=new Scanner(System.in);
        String carteDeSters= scanner.nextLine();
        var Iterator = carteMap.entrySet().iterator();
        while (Iterator.hasNext()){
            var next=Iterator.next();
            String k=next.getValue().titlul();
            if(k.equals(carteDeSters)){
                Iterator.remove();
            }
        }
        for(var o:carteMap.entrySet()){
            System.out.println(o);
        }

        /*3*/
        System.out.println("\nAdaugare carte:");
        System.out.println("\nTitlul:");
        String titlul =scanner.nextLine();
        System.out.println("\nAutorul:");
        String autorul =scanner.nextLine();
        System.out.println("\nAnul:");
        int anul =scanner.nextInt();
        int maximKey= Collections.max(carteMap.keySet());
        carteMap.putIfAbsent(maximKey+1,new Carte(titlul,autorul,anul));

        /*4*/
        scriere(carteMap);

        /*5*/
        Set<Carte> carteSet = carteMap.values().stream()
                .filter(carte -> "Yuval Noah Harari".equals(carte.autorul()))
                .collect(Collectors.toSet());
        carteSet.forEach(System.out::println);
        System.out.println("\n");
        /*6*/
        carteSet.stream()
                .sorted((a,b) ->a.titlul().compareToIgnoreCase(b.titlul()))
                .forEach(System.out::println);

        /*7*/
        Optional<Carte> optional= carteSet.stream()
                .min((a, b) -> a.anul() - b.anul());
        System.out.println(optional);
    }
}