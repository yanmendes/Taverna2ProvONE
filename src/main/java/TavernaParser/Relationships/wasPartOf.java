package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class wasPartOf extends TemplateParser {
    public wasPartOf(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), ProvONE.EXECUTION);
        Node n2 = new Node(this.statement.getObject().toString(), ProvONE.EXECUTION);
        Edge e = new Edge(ProvONE.WASPARTOF, n1, n2);
        e.save();
    }
}
