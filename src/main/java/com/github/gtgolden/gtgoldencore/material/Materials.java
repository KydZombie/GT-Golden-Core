package com.github.gtgolden.gtgoldencore.material;

import net.modificationstation.stationapi.api.registry.ModID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Materials {

    private static final HashMap<String, GTMaterial> materials = new HashMap<>();
    private static final HashMap<String, String> formulas = new HashMap<>();
    protected static List<ModID> modIDs = new ArrayList<>();
    protected static int modID;
    public static void put(String name, GTMaterial material) {
        materials.put(name, material);
        if (material.getFormula() != null)
            formulas.put(material.getFormula(), material.getName());
    }

    /**
     * Don't forget to supply MOD_ID of your mod before registering any materials
     */
    public static void useModID(@Nullable ModID modID) {
        Materials.modIDs.add(modID);
        Materials.modID = Materials.modIDs.size() - 1;
    }
    public static String name(String formula) {
        return formulas.getOrDefault(formula, "H");
    }
    @NotNull
    public static GTMaterial get(@NotNull String name) {
        return materials.getOrDefault(name, materials.get("missing"));
    }
    public static String @NotNull [] allNames() {
        return materials.keySet().toArray(new String[0]);
    }
    public static GTMaterial @NotNull [] allMaterials() {
        return materials.values().toArray(new GTMaterial[0]);
    }
}
