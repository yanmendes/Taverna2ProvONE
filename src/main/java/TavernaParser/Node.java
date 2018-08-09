package TavernaParser;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class Node extends Neo4jInterface {
    private HashMap<String, String> attributes;
    private String type;

    public Node(String id) {
        this.attributes = new HashMap<String, String>();
        this.addAtribute("id", id);
    }

    public Node(String id, String type) {
        this(id);
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }

    public Node addAtribute(String key, String value) {
        this.attributes.put(key, value);
        return this;
    }

    public Node save() {
        Neo4jInterface.initializeSession();

        String cypher = String.format("MERGE (n:%s %s)", this.type, (new JSONObject(attributes)).toString());
        Neo4jInterface.run(sanitize(cypher));

        if (Neo4jInterface.logging())
            System.out.println(sanitize(cypher));

        Neo4jInterface.closeSession();
        return this;
    }
}
