package no.uib.inf101.view;

import java.util.Map;

/**
 * The MenuWithFields interface represents a menu that contains fields.
 * It provides a method to retrieve the fields as a map of key-value pairs.
 */
public interface MenuWithFields {
    /**
     * Retrieves the fields of the menu as a map of key-value pairs.
     *
     * @return a map of key-value pairs representing the fields of the menu
     */
    Map<String, String> getFields();
}
