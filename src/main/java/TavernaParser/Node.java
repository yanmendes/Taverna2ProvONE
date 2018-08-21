package TavernaParser;

import org.json.simple.JSONObject;
import org.neo4j.driver.v1.exceptions.NoSuchRecordException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node extends Neo4jInterface {
    private HashMap<String, String> attributes;
    private String type;

    public Node(String id) {
        this.attributes = new HashMap<String, String>();
        this.addAtribute("id", id).addAtribute("workflow", Neo4jInterface.workflowIdentifier);
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

        if (this.needsUpdate())
            this.update();

        String cypher = String.format("MERGE (n:%s %s)", this.type, (new JSONObject(attributes)).toString());
        Neo4jInterface.run(sanitize(cypher));

        if (Neo4jInterface.logging())
            System.out.println(sanitize(cypher));

        Neo4jInterface.closeSession();
        return this;
    }

    private static Node load(String id, String type) {
        Node node = new Node(id, type);

        String cypher = String.format("MATCH (n:%s {id: '%s'}) RETURN n LIMIT 1", type, id);

        try {
            Map<String, Object> properties = Neo4jInterface.run(sanitize(cypher)).single().get(0).asMap();

            for (Map.Entry<String, Object> entry : properties.entrySet())
                node.addAtribute(entry.getKey(), entry.getValue().toString());

        } catch (NoSuchRecordException e) {

        } finally {
            if (Neo4jInterface.logging())
                System.out.println(sanitize(cypher));

            return node;
        }
    }

    private boolean needsUpdate() {
        Node node = load(this.attributes.get("id"), this.type);

        for (Map.Entry<String, String> entry : node.attributes.entrySet())
            if (!this.attributes.containsKey(entry.getKey())) {
                this.attributes.putAll(node.attributes);
                return true;
            }

        return false;
    }

    private Node update() {
        ArrayList<String> updates = new ArrayList<>();
        for (Map.Entry<String, String> entry : this.attributes.entrySet())
            updates.add("n." + entry.getKey() + "='" + entry.getValue() + "'");

        String cypher = String.format("MATCH (n:%s {id: '%s'}) \nSET %s", this.type, this.attributes.get("id"), String.join(", ", updates));
        Neo4jInterface.run(sanitize(cypher));

        if (Neo4jInterface.logging())
            System.out.println(sanitize(cypher));

        return this;
    }
}
