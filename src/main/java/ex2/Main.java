package ex2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ex1.Carte;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void scriere(Set<InstrumentMuzical> instrumentMuzical, File file) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(file,instrumentMuzical);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        /*1*/
        Set<InstrumentMuzical> instrumentMuzical=new HashSet<>();
        InstrumentMuzical i1=new Chitara("Fender", 1500.0, TipChitara.Electrica, 6);
        InstrumentMuzical i2=new Chitara("Gibson", 2000.0, TipChitara.Acustica, 12);
        InstrumentMuzical i3=new Chitara("Ibanez", 800.0, TipChitara.Clasica, 4);
        InstrumentMuzical i4=new SetTobe("Pearl", 2000.0, TipTobe.Acustice, 5, 3);
        InstrumentMuzical i5=new SetTobe("Mapex", 11800.0, TipTobe.Electronice, 4, 2);
        InstrumentMuzical i6=new SetTobe("DW_Drums", 2500.0, TipTobe.Acustice, 6, 4);
        instrumentMuzical.add(i1);
        instrumentMuzical.add(i2);
        instrumentMuzical.add(i3);
        instrumentMuzical.add(i4);
        instrumentMuzical.add(i5);
        instrumentMuzical.add(i6);
        /*2*/
        File file=new File("src/main/resources/instrumente.json");
        scriere(instrumentMuzical,file);

        /*3*/

        ObjectMapper mapper=new ObjectMapper();
        Set<InstrumentMuzical> instrumentMuzicalSet=new HashSet<>();
        instrumentMuzicalSet=mapper.readValue(file, HashSet.class);

        /*4*/

        /*5*/
        try {
            InstrumentMuzical i7=new SetTobe("DW_Drums", 2500.0, TipTobe.Acustice, 6, 4);
            instrumentMuzical.add(i7);
        }
        catch(Exception e){
            System.out.println("Nu poti adauga inca o data acest instrument");
        }
        /*6*/
        System.out.println("\n");
        instrumentMuzical.removeIf(instrumentMuzical1 -> instrumentMuzical1.getPret()>3000);
        instrumentMuzical.forEach(System.out::println);
        /*7*/
        System.out.println("\n");

        instrumentMuzical.stream()
                .filter((in)->in instanceof Chitara)
                .forEach(System.out::println);
        /*8*/
        instrumentMuzical.stream()
                .filter((in) -> in.getClass()== SetTobe.class)
                .forEach(System.out::println);
        /*9*/
        Optional<InstrumentMuzical> optionalChitara = instrumentMuzical
                .stream()
                .filter((p) -> p instanceof Chitara)
                .findAny();
        Optional<InstrumentMuzical> optional=optionalChitara.stream()
                .max((a,b) -> { Chitara c= (Chitara) a;
                    Chitara d= (Chitara) b;
                    return (Integer.compare(c.getNr_corzi(), d.getNr_corzi()));
                });
        System.out.println(optional);
        /*10*/
        instrumentMuzical.stream()
                .filter((in) -> in.getClass()== SetTobe.class)
                .sorted((a,b)->{
                    SetTobe c = (SetTobe) a;
                    SetTobe d = (SetTobe) b;
                   return(Integer.compare(c.getNr_tobe(),d.getNr_tobe()));
                })
                .forEach(System.out::println);
    }
}
