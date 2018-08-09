package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.Prov;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class hadPlan extends TemplateParser {
    public hadPlan(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), Prov.ASSOCIATION);
        Node n2 = new Node(this.statement.getObject().toString(), ProvONE.PROGRAM);
        Edge e = new Edge(Prov.HADPLAN, n1, n2);
        e.save();
    }

    public void execute(String associationHash) {
        Node n1 = new Node(associationHash, Prov.ASSOCIATION);
        Node n2 = new Node(this.statement.getObject().toString(), ProvONE.PROGRAM);
        Edge e = new Edge(Prov.HADPLAN, n1, n2);
        e.save();
    }
}
