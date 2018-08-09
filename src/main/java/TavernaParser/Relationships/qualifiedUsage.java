package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.Prov;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class qualifiedUsage extends TemplateParser {
    public qualifiedUsage(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), ProvONE.EXECUTION);
        Node n2 = new Node(this.statement.getObject().toString(), Prov.USAGE);
        Edge e = new Edge(Prov.QUALIFIEDUSAGE, n1, n2);
        e.save();
    }
}
