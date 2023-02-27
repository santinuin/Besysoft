package com.besysoft.ejerciciounidad5.utilities;

import java.text.Collator;

public class Comparator {

    public static Collator comparador() {
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.PRIMARY);
        return comparador;
    }

}
