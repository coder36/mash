package coder36;

import java.util.Map;

public class Mash {

    private Map<String, Object> m;

    public Mash(Map<String, Object> m) {
        this.m = m;
    }

    public Object get(String path) {

        Map<String, Object> node = m;
        String [] nodes = path.split("\\.");

        for ( int i=0; i<nodes.length-1; i++ ) {
            if(node == null ) break;
            Object res = node.get(nodes[i]);
            node = res instanceof Map ? (Map<String,Object>) res : null;
        }

        if( node == null ) return "";
        Object res = node.get(nodes[nodes.length-1]);
        if( res == null ) return "";
        if( res instanceof Map ) return new Mash( (Map) res );
        return res;
    }

    public String get_s(String path ) {
        return get(path).toString();
    }

    public Map<String,Object> map() {
        return m;
    }

    public String toString() {
        return m.toString();
    }

}
