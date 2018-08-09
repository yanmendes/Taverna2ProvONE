package TavernaParser;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class Edge extends Neo4jInterface {
    private HashMap<String, String> attributes;
    private String type;
    private Node subject;
    private Node object;

    public Edge(String type, Node n1, Node n2) {
        this.attributes = new HashMap<String, String>();
        this.type = type;
        this.subject = n1;
        this.object = n2;
    }

    public Edge(String id, String type, Node n1, Node n2) {
        this(type, n1, n2);
        this.addAtribute("id", id);
    }

    public void addAtribute(String key, String value) {
        this.attributes.put(key, value);
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }

    public Node getSubject() {
        return subject;
    }

    public Node getObject() {
        return object;
    }

    public void save() {
        Neo4jInterface.initializeSession();

        String cypher = String.format(
                "MERGE (n:%s %s)\n" +
                        "MERGE (m:%s %s)\n" +
                        "MERGE (n)-[r:%s %s]->(m)\n",
                this.subject.getType(), (new JSONObject(this.subject.getAttributes())).toString(),
                this.object.getType(), (new JSONObject(this.object.getAttributes())).toString(),
                this.type, (new JSONObject(this.attributes)).toString());

        Neo4jInterface.run(sanitize(cypher));

        if(Neo4jInterface.logging())
            System.out.println(sanitize(cypher));

        Neo4jInterface.closeSession();
    }
}
