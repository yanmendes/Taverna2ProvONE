# Taverna2ProvONE

Taverna2ProvONE parses Taverna WfMSs turtle files to the ProvONE data model, utiling Neo4j as storage

### Running Prov2ONE

Taverna2ProvONE was developed using [Java](https://www.java.com/) v.1.8.0_121 and [Maven](https://maven.apache.org/) v.3.5.0. The compiled jar is available at the project's target directory and can be run using

```sh
$ java -jar TavernaParser.jar neo4jUri neo4jUsername neo4jPassword turtleFile
```

Since Apache Jena expects an URL as parameter, if you're using a local turtle file, remember to use file:// in the beginning of the file path.

Alternatively, you can build the project from scratch: 

```sh
$ mvn package
```


**Free Software, Hell Yeah!**
