package com.tinyshellzz.spawnpointprotect.utils;

import org.bukkit.Location;
import org.bukkit.World;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Tools {
    // check whether target is in area(a, b)
    public static boolean inArea(Location loc, Location a, Location b) {
        // a, b is in different world, impossible
        if (!a.getWorld().equals(b.getWorld())) return false;
        if (!a.getWorld().equals(loc.getWorld())) return false;

        World w = a.getWorld();
        Location min_loc = new Location(w,
                min(a.getX(), b.getX()),
                min(a.getY(), b.getY()),
                min(a.getZ(), b.getZ()));

        Location max_loc = new Location(w,
                max(a.getX(), b.getX()),
                max(a.getY(), b.getY()),
                max(a.getZ(), b.getZ()));

        return (min_loc.getX() <= loc.getX()
                && min_loc.getY() <= loc.getY()
                && min_loc.getZ() <= loc.getZ()
                && max_loc.getX() >= loc.getX()
                && max_loc.getY() >= loc.getY()
                && max_loc.getZ() >= loc.getZ());
    }

}
