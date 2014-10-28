/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.sisapus.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Edison
 */
public class Util {

    public static String baseurl() {
        return "http://localhost:8080/sisapus/";
    }

    public static String basepathurl() {
        return "/sisapus/";
    }

    public static String basepath() {
        return "/Pantallas/";
    }
}
