package com.company;

import java.util.*;

public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
        HeavenlyBody temp = new HeavenlyBody("Mercury", 88);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Venus", 225);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Earth", 365);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        HeavenlyBody tempMoon = new HeavenlyBody("Moon", 27);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        System.out.println("Planets");
        for(HeavenlyBody planet : planets){
            System.out.println("\t" + planet.getName());
        }

        HeavenlyBody body = solarSystem.get("Earth");
        System.out.println("Moons of " + body.getName());
        for(HeavenlyBody earthMoon : body.getSatellites()){
            System.out.println("\t" + earthMoon.getName());
        }

        Set<HeavenlyBody> moons = new HashSet<>();
        for(HeavenlyBody planet : planets){
            moons.addAll(planet.getSatellites());
        }

        System.out.println("All Moons");
        for(HeavenlyBody moon : moons){
            System.out.println("\t" + moon.getName());
        }

        HeavenlyBody venus = new HeavenlyBody("Venus", 231);
        planets.add(venus);

        for(HeavenlyBody planet : planets){
            System.out.println(planet.getName() + ": " + planet.getOrbitalPeriod());
        }

//        Object o = new Object();
//        o.equals(o);
        //"venus".equals("");
    }
}
