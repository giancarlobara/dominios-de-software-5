package com.dominios.vestib.utils;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class StringUtil {

    private StringUtil() {}


    private static final Set<String> LISTA_IGNORADOS = new HashSet<>(Arrays.asList("__macosx",".ds_store","thumbs.db"));

    public static boolean isValidFilename(String nome) { return nome.matches("[a-zA-Z0-9\\.\\-_\\/]+"); }

    public static boolean isNullOrEmpty(Object object) {
        return object == null
                || (object.getClass() == String.class && object.toString().isEmpty())
                || (object instanceof Collection && ((Collection) object).isEmpty());
    }

    public  static boolean isIgnoredFile(String name){
        name = name.toLowerCase();
        return LISTA_IGNORADOS.contains(name);
    }
    public static Long convertBytesToMb(long bytes){
        return bytes/(1024*1024);
    }

    public static String formatText(String text){
        return text.replaceAll("[ãâàáä]", "a")
                .replaceAll("[êèéë&]", "e")
                .replaceAll("[îìíï]", "i")
                .replaceAll("[õôòóö]", "o")
                .replaceAll("[ûúùü]", "u")
                .replaceAll("[ÃÂÀÁÄ]", "A")
                .replaceAll("[ÊÈÉË]", "E")
                .replaceAll("[ÎÌÍÏ]", "I")
                .replaceAll("[ÕÔÒÓÖ]", "O")
                .replaceAll("[ÛÙÚÜ]", "U")
                .replace('ç', 'c')
                .replace('Ç', 'C')
                .replace('ñ', 'n')
                .replace('Ñ', 'N')
                .replaceAll("[^a-zA-Z0-9.]", " ")
                .replace(" ", "_").toLowerCase();

    }

}
