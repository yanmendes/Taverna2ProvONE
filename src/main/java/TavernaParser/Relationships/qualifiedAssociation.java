package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.Prov;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class qualifiedAssociation extends TemplateParser {
    public qualifiedAssociation(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), ProvONE.EXECUTION);
        Node n2 = new Node(this.statement.getObject().toString(), Prov.ASSOCIATION);
        Edge e = new Edge(Prov.QUALIFIEDASSOCIATION, n1, n2);
        e.save();
    }

    public String execute(boolean generateAssociation) {
        String associationHash = sha256Hex(this.statement.getSubject().toString());

        Node n1 = new Node(this.statement.getSubject().toString(), ProvONE.EXECUTION);
        Node n2 = new Node(associationHash, Prov.ASSOCIATION);
        Edge e = new Edge(Prov.QUALIFIEDASSOCIATION, n1, n2);
        e.save();

        return associationHash;
    }
}
