/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createservicedocument;

/**
 *
 * @author pc
 */
public class FormatDesc {
    String key,value;

    public FormatDesc() {
    }

    @Override
    public String toString() {
        return "FormatDesc{" + "key=" + key + ", value=" + value + '}';
    }

    public FormatDesc(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}