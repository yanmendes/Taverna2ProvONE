package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.Prov;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class entity extends TemplateParser {
    public entity(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), Prov.USAGE);
        Node n2 = new Node(this.statement.getObject().toString(), Prov.ENTITY);
        Edge e = new Edge(Prov.ENTITY_RELATIONSHIP, n1, n2);
        e.save();
    }
}
